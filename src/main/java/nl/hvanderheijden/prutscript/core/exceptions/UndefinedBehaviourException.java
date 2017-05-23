package nl.hvanderheijden.prutscript.core.exceptions;


/**
 * When an instruction encountered something it doesn't know how to handle.
 * @author Heiko van der Heijden
 */
public class UndefinedBehaviourException extends PrutException{
    public UndefinedBehaviourException(final int lineNr, final String msg) {
        super(msg,lineNr);
    }
}
