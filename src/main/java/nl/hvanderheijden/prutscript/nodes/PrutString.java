package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;


public final class PrutString extends PrutReference {

    private final String value;

    public PrutString(String value){
     //   value = value.substring(0, value.length()-1);
       // value = value.substring(1);
        this.value = value;
        //this.value = value;

    }

    private PrutString(){
        throw new UnsupportedOperationException();
    }

    public String getValue() {
        return value;
    }

    @Override
    public PrutReference getValue(PrutContext context) throws PrutException {
        return this;
    }

    @Override
    public String prutToString() {
        return value;
    }


    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(value == null);
    }
}
