package nl.hvanderheijden.prutscript.core.exceptions;

/**
 * Exception thrown when the parameters are not equal.
 */
public class PrutAssertException extends PrutException{

    public PrutAssertException(final int lineNr, final String msg) {
        super(lineNr,msg);
    }

    public PrutAssertException(final String msg, final int lineNr) {
        super(lineNr,msg);
    }


}
