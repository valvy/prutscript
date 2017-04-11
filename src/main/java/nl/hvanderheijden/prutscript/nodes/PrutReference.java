package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;

public abstract class PrutReference implements Node {

    private int lineNr;

    private PrutReference(){
        throw new UnsupportedOperationException();
    }

    public PrutReference(int lineNr){
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
