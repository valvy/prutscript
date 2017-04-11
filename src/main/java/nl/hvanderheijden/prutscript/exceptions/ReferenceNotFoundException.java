package nl.hvanderheijden.prutscript.exceptions;

import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class ReferenceNotFoundException extends  PrutException{

    private final String message;

    public ReferenceNotFoundException(int lineNr ,final String message) {
        super(lineNr, message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
