package nl.hvanderheijden.prutscript.exceptions;

import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;


public class PrutTypeException extends PrutException{


    public PrutTypeException(final int lineNr, final String msg) {
        super(lineNr, msg);
    }

    public PrutTypeException(final String msg, final int lineNr) {
        super(lineNr, msg);
    }

}
