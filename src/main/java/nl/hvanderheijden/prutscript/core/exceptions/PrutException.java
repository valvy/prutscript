package nl.hvanderheijden.prutscript.core.exceptions;


public abstract class PrutException extends Exception {


    private final String msg;

    private final int lineNr;

    private PrutException(){
        throw new UnsupportedOperationException();
    }

    public PrutException(final int lineNr, final String msg) {
        this.msg = msg;
        this.lineNr = lineNr;
    }

    public PrutException(final String msg, final int lineNr) {
        this.msg = msg;
        this.lineNr = lineNr;
    }

    @Override
    public String getMessage() {
        return String.format("Error on line : %d, %s ", lineNr,msg);

    }

    @Override
    public String toString() {
        return this.getMessage();
    }


}