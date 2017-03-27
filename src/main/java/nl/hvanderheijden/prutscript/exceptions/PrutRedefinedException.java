package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class PrutRedefinedException extends PrutException{

    private final String message;

    public PrutRedefinedException(List<Variable> stackTrace, final String message) {
        super(stackTrace);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
