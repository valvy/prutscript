package nl.hvanderheijden.prutscript.core;

import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.core.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.core.nodes.Method;
import nl.hvanderheijden.prutscript.core.nodes.MethodCall;
import nl.hvanderheijden.prutscript.core.nodes.Node;
import nl.hvanderheijden.prutscript.core.nodes.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handling the stack and the scopes of a program.
 * Also used as primarily interface of the Program methods.
 * @author Heiko van der Heijden
 */
public final class PrutContext extends PrutOutput{

    private final static class Scope{
        private final List<Variable> stack;

        private final Scope scope;

        private Scope(){
            this.stack = new ArrayList<>();
            this.scope = null;
        }

        private Scope(final Scope scope){
            this.scope = scope;
            this.stack = new ArrayList<>();
        }

        @Override
        public String toString(){
            if(scope == null){
                return stack.toString();
            } else {
                return String.format(" %s [ %s ]", stack.toString(), scope.toString());
            }
        }

        public boolean variableExists(final String name){
            final Optional<Variable> res = stack.stream().filter(var ->  var.getName().equals(name)).findFirst();
            if (res.isPresent()) {
                return true;
            } else
                return scope != null && scope.variableExists(name);
        }

        private void addToGlobalStack(final Variable var) throws PrutRedefinedException {

            if(scope == null){
                for(final Variable v : this.stack){
                    if(v.getName().equals(var.getName())){
                        throw new PrutRedefinedException(
                                var.getLineNr(),
                                String.format("variable %s is already defined",var.getName())
                        );
                    }
                }

                stack.add(var);
            } else {
                scope.addToGlobalStack(var);
            }
        }

        private Variable getVariable(final String name) throws ReferenceNotFoundException {
            final Optional<Variable> res = stack.stream().filter(var ->  var.getName().equals(name)).findFirst();
            if(res.isPresent()){
                return res.get();
            }
            if(scope != null){
                return scope.getVariable(name);
            }
            throw new ReferenceNotFoundException(1,String.format("Could not find : %s", name));
        }
    }

    private final Scope scope;

    private final ProgramFactory.Program program;

    private PrutContext(){
        throw new UnsupportedOperationException();
    }

    public PrutContext(final ProgramFactory.Program program){
        this.program = program;
        this.scope = new Scope();
    }

    public PrutContext(final PrutContext context){
        this.scope = new Scope(context.scope);
        this.program = context.getProgram();
    }

    /**
     * Links an other program to this program.
     * @param program
     * @param node
     * @throws PrutRedefinedException
     */
    public void linkProgramToContext(final ProgramFactory.Program program, final Node node) throws PrutRedefinedException {
        this.program.linkProgram(program,node);
    }

    public int getAmountOfPrograms(){
        return this.program.getAmountOfLinkedPrograms();
    }

    public ProgramFactory.Program getProgram() {
        return program;
    }

    /**
     * Adds a variable to the global stack.
     * @param var the variable.
     * @throws PrutRedefinedException a duplicate is already defined.
     */
    public void addToGlobalStack(final Variable var) throws PrutRedefinedException{
        this.scope.addToGlobalStack(var);
    }

    /**
     * Adds a variable to the local stack.
     * @param var
     * @throws PrutRedefinedException
     */
    public void addtoStack(final Variable var) throws PrutRedefinedException {

        for(final Variable v : this.scope.stack){
            if(v.getName().equals(var.getName())){
                throw new PrutRedefinedException(
                        var.getLineNr(),
                        String.format("variable %s is already defined",var.getName())
                );
            }
        }

        this.scope.stack.add(var);
    }

    /**
     * Places the entire global stack to the target context.
     * @param ctx
     * @throws PrutRedefinedException
     */
    public void surrenderGlobalStack(final PrutContext ctx) throws PrutRedefinedException {
        for(final Variable var : scope.stack){
            ctx.addToGlobalStack(var);
        }
    }

    @Override
    public String toString(){
        return scope.toString();
    }

    /**
     * Searches a variable from every scope.
     * @param name
     * @return
     * @throws PrutException
     */
    public Variable getVariable(final String name) throws PrutException {
        if(scope.variableExists(name)) {
            return this.scope.getVariable(name);
        } else {
            final Method m = program.getMethod(new MethodCall(name, new ArrayList<>(), 0));
            return new Variable(name, m.executeMethod(this,new ArrayList<>()), m.getLineNr());
        }
    }

    /**
     * Gets a method from the program.
     * @param call
     * @return
     * @throws ReferenceNotFoundException
     */
    public Method getMethod(final MethodCall call) throws ReferenceNotFoundException {
        return program.getMethod(call);
    }


}
