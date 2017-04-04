package nl.hvanderheijden.prutscript;


import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.nodes.*;
import nl.hvanderheijden.prutscript.prutlib.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProgramFactory {

    public static class Program{

        private static final String MAIN_METHOD = "@Main";
        private final List<Method> methods;
        private final List<PrutReference> instructions;

        void checkValidity() throws PrutException{
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
        }


        public Method getMethod(final String methodname) throws ReferenceNotFoundException {
            for(final Method method : methods){
                if(methodname.equals(method.getName())){
                    return method;
                }
            }
            throw new ReferenceNotFoundException(null, String.format("Method: %s does not exist!", methodname));
        }

        public void execute() throws PrutException {
            this.addMethod(new Method(MAIN_METHOD, instructions, null));
            final PrutContext context = new PrutContext(this);
            PrutReference res = getMethod(MAIN_METHOD).executeMethod(context, new ArrayList<PrutReference>());
            while(true){

                if(res instanceof MethodCall){
                    res = res.getValue(context);
                } else{
                    break;
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
        program = new Program();
        for(final Method m : System.getIOMethods()){
            program.addMethod(m);
        }
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
