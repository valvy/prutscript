package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.List;


public class PrutString extends Value{

    private final String value;

    public PrutString(String value){
     //   value = value.substring(0, value.length()-1);
       // value = value.substring(1);
        this.value = value;
        //this.value = value;

    }


    public String getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "String";
    }

    @Override
    public String prutToString() {
        return value;
    }

    @Override
    public String varGetName(final String alt) {
        return alt;
    }


    @Override
    public Value varGetValue(final List<Variable> stack, final ProgramFactory.Program pr) {
        return this;
    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(value == null);
    }
}
