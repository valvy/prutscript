package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.List;
import java.util.Vector;

public class MethodCall extends Value {

    private final String name;

    private final List<Node> arguments;

    private List<Variable> tmpStack = null;



    public MethodCall(final String name, final List<Node> arguments){
        this.name = name;
        this.arguments = arguments;
    }

    public void preFillStack(final List<Variable> stack){
        this.tmpStack = stack;
    }

    @Override
    public String toString(){
        return String.format("method %s : %s", name, arguments.toString());
    }

    public String getName() {
        return name;
    }

    public List<Node> getArguments() {
        return arguments;
    }

    @Override
    public String prutToString() {
        return String.format("[Method] %s", name);
    }

    @Override
    public String varGetName(final String alt) {
        return name;
    }


    @Override
    public Value varGetValue(final List<Variable> stack, ProgramFactory.Program program) throws PrutException {
        final List<Node> args = new Vector<>();
        if(tmpStack == null) {
            for (final Node v : arguments) {
                args.add(v.varGetValue(stack, program));
            }
        }
        else{
            for (final Node v : tmpStack) {
                args.add(v.varGetValue(stack, program));
            }
        }

        return program.getMethod(this.name).executeMethod(program,args);
    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(name == null);
        pr.getMethod(this.name);

    }

    @Override
    public String getType() {
        return "Method";
    }
}
