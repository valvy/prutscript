package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.nodes.Method;
import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Handling the stack and the scopes of a program.
 */
public class PrutContext {

    private final static class Scope{
        private final List<Variable> stack;

        private final Scope scope;

        public Scope(){
            this.stack = new ArrayList<>();
            this.scope = null;
        }

        public  Scope(final Scope scope){
            this.scope = scope;
            this.stack = new ArrayList<>();
        }

        public Variable getVariable(final String name) throws ReferenceNotFoundException {
            for(final Variable var : stack){
                if(var.getName().equals(name)){
                    return var;
                }
            }
            if(scope != null){
                return scope.getVariable(name);
            }
            throw new ReferenceNotFoundException(null,String.format("Could not find : %s", name));
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

    public ProgramFactory.Program getProgram() {
        return program;
    }

    public void addtoStack(final Variable var){
        this.scope.stack.add(var);
    }

    public Variable getVariable(final String name) throws ReferenceNotFoundException {
        return this.scope.getVariable(name);
    }

    public Method getMethod(final String name) throws ReferenceNotFoundException {
        return program.getMethod(name);
    }


}
