package nl.hvanderheijden.prutscript.exceptions;

import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class ReferenceNotFoundException extends  PrutException{

    private final String message;

    public ReferenceNotFoundException(List<Variable> stackTrace, final String message) {
        super(stackTrace);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
