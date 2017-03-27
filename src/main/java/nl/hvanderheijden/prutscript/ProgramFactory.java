package nl.hvanderheijden.prutscript;


import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.nodes.*;
import nl.hvanderheijden.prutscript.prutlib.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProgramFactory {

    public static class Program extends Code{

        private final List<Method> methods;

        private final List<Variable> stack;


        void checkValidity() throws PrutException{
            for(final Method m : methods){
                m.checkValidity(this);

            }

            for(final Node n : this.instructions){
                n.checkValidity(this);
            }
        }

        @Override
        protected void addVariable(final List<Variable> stack, final Node node, final ProgramFactory.Program program) throws PrutException {
            if(node instanceof Variable) {
                Variable variable = (Variable) node;

                variable = new Variable(variable.getName(), variable.varGetValue(this.stack,program));
                for(final Variable i : this.stack){
                    if(i.varGetName("none").equals(variable.varGetName(""))){
                        throw new PrutRedefinedException(this.stack, String.format("Variable %s has already been assigned", variable.varGetName("")));
                    }
                }

                this.stack.add(variable);
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
            methods = new Vector<Method>();
            stack = new Vector<Variable>();
        }

        public Variable getVariable(final String name) throws ReferenceNotFoundException {
            for(final Variable v : stack){
                if(v.getName().equals(name)){
                    return v;
                }
            }

            throw new ReferenceNotFoundException(null,String.format("Could not find %s", name));
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
            Value res = this.executeMethod(this, new Vector<Node>());
            while(true){

                if(res instanceof MethodCall){
                    res = res.varGetValue(new ArrayList<Variable>(),this);
                } else{
                    break;
                }
            }


        }

        private void addMethod(final Method method){
            this.methods.add(method);
        }

        private void addInstruction(final Node node){
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
            program.addInstruction(node);
        }

    }

    public Program getProgram(){
        return program;
    }
}
