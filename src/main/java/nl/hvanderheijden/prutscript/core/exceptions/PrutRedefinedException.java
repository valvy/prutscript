package nl.hvanderheijden.prutscript.core.exceptions;


public class PrutRedefinedException extends PrutException{

    public PrutRedefinedException(int lineNr, final String message) {
        super(lineNr,message);
    }

}
