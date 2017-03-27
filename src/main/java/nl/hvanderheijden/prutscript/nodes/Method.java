package nl.hvanderheijden.prutscript.nodes;


import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.List;

public class Method extends Code implements Node {

    private final String name;

    public Method(final String name,
                  final List<Node> nodelist,
                  final List<String> arguments){

        this.arguments.clear();
        if(arguments != null){

            for(final String arg : arguments){
                this.arguments.add(arg);
            }
        }

        this.instructions.clear();
        if(nodelist != null) {
            for (final Node n : nodelist) {
                this.instructions.add(n);
            }
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String prutToString() {
        return String.format("[Method] %s", name) ;
    }

    @Override
    public String varGetName(final String alt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        return this.prutToString();
    }

    @Override
    public Value varGetValue(final List<Variable> stack, final ProgramFactory.Program program) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(name == null);

        for(final Node n : this.instructions){
            n.checkValidity(pr);
        }
    }
}
