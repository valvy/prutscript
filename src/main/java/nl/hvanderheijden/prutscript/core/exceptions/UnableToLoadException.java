package nl.hvanderheijden.prutscript.core.exceptions;


public class UnableToLoadException extends PrutException {
    public UnableToLoadException(final String msg) {
        super(-1,msg);
    }
}
