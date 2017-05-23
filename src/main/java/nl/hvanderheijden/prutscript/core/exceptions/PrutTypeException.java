package nl.hvanderheijden.prutscript.core.exceptions;


/**
 * Thrown when types do not match.
 * @author Heiko van der Heijden.
 */
public class PrutTypeException extends PrutException{

    public PrutTypeException(final int lineNr, final String msg) {
        super(lineNr, msg);
    }

    public PrutTypeException(final String msg, final int lineNr) {
        super(lineNr, msg);
    }

}
