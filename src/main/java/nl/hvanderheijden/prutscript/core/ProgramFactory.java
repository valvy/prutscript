package nl.hvanderheijden.prutscript.core;


import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.core.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.core.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.ArrayList;
import java.util.List;

public class ProgramFactory {

    public static class Program{

        private static final String MAIN_METHOD = "@Main";
        private final List<Method> methods;
        private final List<PrutReference> instructions;
        private final List<Program> linked;

        public void checkValidity() throws PrutException{
            for(final Method m : methods){
                m.checkValidity(this);
            }
        }

        @Override
        public String toString(){
            String result = "";

            for(final Method method : methods){
               result += method.toString() + "\n";
            }
            return result;
        }

        private Program(){
            instructions = new ArrayList<>();
            methods = new ArrayList<>();
            this.linked = new ArrayList<>();
        }

        public void linkProgram(final Program pr, final Node node) throws PrutRedefinedException {
            /**
             * Check for duplicates
             */
            for(final Method my : this.methods){
                for(final Method other : pr.methods){
                    Assert.isAlreadydefined(
                            other.getName().equals(my.getName()),
                            String.format(" %s is in import statement already defined", other.getName()),
                            node
                    );
                }
            }


            this.linked.add(pr);
        }

        private boolean methodExist(final MethodCall call){
            for(final Method method : methods){
                if(method.getName().equals(call.getName())){
                    return true;
                }
            }
            return false;
        }

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

        public void execute(PrutContext context) throws PrutException {
            if(!this.instructions.isEmpty()) {
                final PrutContext ctx = new PrutContext(this);
                this.addMethod(new Method(MAIN_METHOD + "1", instructions, new ArrayList<>(), 0));
                PrutReference res = getMethod(new MethodCall(MAIN_METHOD +"1", new ArrayList<>(), 0)).executeMethod(ctx, new ArrayList<>());
                while (true) {
                    if (res instanceof MethodCall) {
                        res = res.getValue(ctx);
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


        public void execute() throws PrutException {
            if(!this.instructions.isEmpty()) {
                this.addMethod(new Method(MAIN_METHOD, instructions, new ArrayList<String>(), 0));
                final PrutContext context = new PrutContext(this);
                PrutReference res = getMethod(new MethodCall(MAIN_METHOD, new ArrayList<PrutReference>(), 0)).executeMethod(context, new ArrayList<PrutReference>());
                while (true) {

                    if (res instanceof MethodCall) {
                        res = res.getValue(context);
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


    public ProgramFactory(){
        this.program = new Program();
    }

    public void addToken(final Node node){
        if(node instanceof Method){
            program.addMethod((Method)node);
        }else{
            program.addInstruction((PrutReference)node);
        }
    }

    public Program getProgram(){
        return program;
    }
}
