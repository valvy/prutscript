package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

/**
 * A string.
 */
public final class PrutString extends PrutReference {

    private final String value;

    public PrutString(final String value, final int lineNr){
        super(lineNr);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return String.format("Prut String : %s", value);
    }


    @Override
    public PrutReference execute(PrutContext context) throws PrutException {
        return this;
    }


    @Override
    public String prutToString() {
        return value;
    }


    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(value == null, this);
    }
}
