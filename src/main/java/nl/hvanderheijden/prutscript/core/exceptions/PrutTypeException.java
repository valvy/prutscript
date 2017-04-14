package nl.hvanderheijden.prutscript.core.exceptions;


public class PrutTypeException extends PrutException{


    public PrutTypeException(final int lineNr, final String msg) {
        super(lineNr, msg);
    }

    public PrutTypeException(final String msg, final int lineNr) {
        super(lineNr, msg);
    }

}
