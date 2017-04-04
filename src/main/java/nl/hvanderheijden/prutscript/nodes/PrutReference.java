package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;

public abstract class PrutReference implements Node {

    public String getName(final String alt){
        return alt;
    }

    public abstract PrutReference getValue(final PrutContext context) throws PrutException;
}
