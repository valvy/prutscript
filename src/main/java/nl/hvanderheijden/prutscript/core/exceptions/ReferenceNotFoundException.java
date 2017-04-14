package nl.hvanderheijden.prutscript.core.exceptions;

public class ReferenceNotFoundException extends  PrutException{

    public ReferenceNotFoundException(int lineNr ,final String message) {
        super(lineNr, message);
    }

}
