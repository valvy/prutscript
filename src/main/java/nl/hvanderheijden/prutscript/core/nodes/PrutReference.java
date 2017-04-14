package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;

public abstract class PrutReference implements Node {

    private final int lineNr;

    private PrutReference(){
        throw new UnsupportedOperationException();
    }

    public PrutReference(final int lineNr){
        this.lineNr = lineNr;
    }

    public String getName(final String alt){
        return alt;
    }

    @Override
    public int getLineNr() {
        return this.lineNr;
    }

    public abstract PrutReference getValue(final PrutContext context) throws PrutException;
}
