package nl.hvanderheijden.prutscript.core.exceptions;


/**
 * Thrown when a duplicate variable or method is found.
 * @author Heiko van der Heijden
 */
public class PrutRedefinedException extends PrutException{

    public PrutRedefinedException(int lineNr, final String message) {
        super(lineNr,message);
    }

}
