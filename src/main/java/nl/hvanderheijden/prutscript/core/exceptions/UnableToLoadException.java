package nl.hvanderheijden.prutscript.core.exceptions;


public class UnableToLoadException extends PrutException {
    public UnableToLoadException(final String msg) {
        super(-1,msg);
    }
    public UnableToLoadException(final Exception ex) {super(-1,ex.getMessage());}
}
