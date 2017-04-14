package nl.hvanderheijden.prutscript.core.exceptions;


public class UndefinedBehaviourException extends PrutException{
    public UndefinedBehaviourException(final int lineNr, final String msg) {
        super(msg,lineNr);
    }
}
