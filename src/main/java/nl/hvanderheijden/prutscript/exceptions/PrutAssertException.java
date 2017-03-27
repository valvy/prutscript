package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class PrutAssertException extends PrutException{

    private final String message;

    public PrutAssertException(List<Variable> stackTrace, final String msg) {
        super(stackTrace);
        this.message = msg;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
