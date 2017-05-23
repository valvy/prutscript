package nl.hvanderheijden.prutscript.core.exceptions;

/**
 * Thrown when a reference is not found.
 * @author Heiko van der Heijden.
 */
public class ReferenceNotFoundException extends  PrutException{

    public ReferenceNotFoundException(int lineNr ,final String message) {
        super(lineNr, message);
    }

}
