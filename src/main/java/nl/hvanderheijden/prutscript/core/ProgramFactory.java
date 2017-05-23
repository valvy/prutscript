package nl.hvanderheijden.prutscript.core;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.core.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.core.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.core.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.*;

/**
 * Class that generates the program.
 * This accepts tokens from the visitor, when the program is taken it no longer accepts changes
 * @author Heiko van der Heijden.
 */
public class ProgramFactory {

    /**
     * The program containing all the methods and linked programs.
     * @author Heiko van der Heijden
     */
    public static class Program{

        private static final String MAIN_METHOD = "@Main";
        private final List<Method> methods;
        private final List<PrutReference> instructions;
        private final List<Program> linked;

        private Program(){
            this.instructions = new ArrayList<>();
            this.methods = new ArrayList<>();
            this.linked = new ArrayList<>();
        }

        /**
         * Checks if the programs are actually valid.
         * This is the first step of checking the data.
         * @throws PrutException
         */
        public void checkValidity() throws PrutException{
            for(final Method m : methods){
                m.checkValidity(this);
            }
        }

        /**
         * Gets a list of all the linked programs.
         * @return linked programs
         */
        public List<Program> getLinkedPrograms(){
            return this.linked;
        }

        @Override
        public String toString(){
            StringBuilder result = new StringBuilder();

            for(final Method method : methods){
               result.append(method.toString()).append("\n");
            }
            return result.toString();
        }

        /**
         * Links a program to this program.
         * Checks firstly if it's capable and doesn't have duplicate methods.
         * @param pr the target program
         * @param node the node requesting the import
         * @throws PrutRedefinedException When a method is both defined
         */
        public void linkProgram(final Program pr, final Node node) throws PrutRedefinedException {
            /**
             * Check for duplicates
             */
            for(final Method my : this.methods){
                for(final Method other : pr.methods){
                    if(other.getName().startsWith(MAIN_METHOD)){
                        continue;
                    }
                    Assert.isAlreadydefined(
                            other.getName().equals(my.getName()),
                            String.format(" %s is in import statement already defined", other.getName()),
                            node
                    );
                }
            }
            this.linked.add(pr);
        }

        /**
         * Checks if a method exists in the program.
         * @param call The call calling the method.
         * @return The name corresponds to the name of the call and the method.
         */
        private boolean methodExist(final MethodCall call){
            for(final Method method : methods){
                if(method.getName().equals(call.getName())){
                    return true;
                }
            }
            return false;
        }

        /**
         * Gets a method from the program.
         * @param methodCall The methodcall containing that wants to execute the method.
         * @return The method.
         * @throws ReferenceNotFoundException The Method doesn't exist.
         */
        public Method getMethod(final MethodCall methodCall) throws ReferenceNotFoundException {
            for(final Method method : methods){
                if(methodCall.getName().equals(method.getName())){
                    return method;
                }
            }

            for(final Program pr : linked){
                if(pr.methodExist(methodCall)){
                    return pr.getMethod(methodCall);
                }
            }

            throw new ReferenceNotFoundException(methodCall.getLineNr(),String.format("Method: %s does not exist!", methodCall.getName()));
        }

        public int getAmountOfLinkedPrograms(){
            return this.linked.size();
        }


        /**
         * Executes the program with an other programs context.
         * This will place their variables on the stack of the other program allowing to reuse it.
         * @param context The other program context
         * @throws PrutException
         */
        public void execute(final PrutContext context) throws PrutException {
            if(!this.instructions.isEmpty()) {
                final PrutContext ctx = new PrutContext(this);
                this.addMethod(new Method(MAIN_METHOD, instructions, new ArrayList<>(), 0));
                PrutReference res = getMethod(new MethodCall(MAIN_METHOD, new ArrayList<>(), 0)).executeMethod(ctx, new ArrayList<>());
                while (true) {
                    if (res instanceof MethodCall) {
                        res = res.execute(ctx);
                    } else {
                        if(res instanceof Variable){
                            ctx.addToGlobalStack(new Variable((Variable) res, ctx));
                        }
                        break;
                    }
                }
                //merge contexts;
                ctx.surrenderGlobalStack(context);

            }
        }

        /**
         * Execute the program with a new context.
         * Mainly used when the program is an executable.
         * @throws PrutException
         */
        public void execute() throws PrutException {
            if(!this.instructions.isEmpty()) {
                this.addMethod(new Method(MAIN_METHOD, instructions, new ArrayList<>(), 0));
                final PrutContext context = new PrutContext(this);
                PrutReference res = getMethod(new MethodCall(MAIN_METHOD, new ArrayList<>(), 0)).executeMethod(context, new ArrayList<>());
                while (true) {

                    if (res instanceof MethodCall) {
                        res = res.execute(context);
                    } else {
                        break;
                    }
                }
            }
        }

        private void addMethod(final Method method){
            this.methods.add(method);
        }

        private void addInstruction(final PrutReference node){
            this.instructions.add(node);
        }

    }

    private final Program program;
    private boolean done = false;

    public ProgramFactory(){
        this.program = new Program();
    }

    /**
     * Adds an instruction to the program.
     * Will throw an exception when called after execution.
     * @param node The instruction.
     * @throws UnableToLoadException the program was already called.
     */
    public void addToken(final Node node) throws UnableToLoadException {
        if(done){
            throw new UnableToLoadException("You can't add tokens after executions");
        }
        if(node instanceof Method){
            program.addMethod((Method)node);
        }else{
            program.addInstruction((PrutReference)node);
        }
    }

    /**
     * Gets the final result but after this call the program can't be altered anymore.
     * @return The Program
     */
    public Program getProgram(){
        done = true;
        return program;
    }
}
