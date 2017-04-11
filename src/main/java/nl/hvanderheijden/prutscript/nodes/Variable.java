package nl.hvanderheijden.prutscript.nodes;


import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

public final class Variable extends PrutReference {

    private final PrutReference value;

    private final String name;

    public Variable(final String name, final int lineNr){
        super(lineNr);
        this.name = name;
        this.value = null;
    }

    public Variable(final String name,
                    final PrutReference value,
                    final int lineNr){
        super(lineNr);
        this.value = value;
        this.name = name;
    }

    public Variable(final PrutReference value,
                    final String name,
                    final int lineNr){
        super(lineNr);
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("%s : %s", name, value);
    }



    @Override
    public void checkValidity(ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(name == null);
    }

    public String getName() {
        return name;
    }


    @Override
    public String prutToString() {
        return value.prutToString();
    }

    @Override
    public String getName(String alt) {
        return this.name;
    }

    @Override
    public PrutReference getValue(PrutContext context) throws PrutException {
        if(this.value == null){
            return context.getVariable(name).getValue(context);//new Variable(this.name, context.getVariable(name).getValue(context));
        }
        return this.value.getValue(context);
    }
}
