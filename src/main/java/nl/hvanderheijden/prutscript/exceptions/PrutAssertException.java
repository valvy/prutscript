package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class PrutAssertException extends PrutException{

    public PrutAssertException(final int lineNr, final String msg) {
        super(lineNr,msg);
    }

    public PrutAssertException(final String msg, final int lineNr) {
        super(lineNr,msg);
    }


}
