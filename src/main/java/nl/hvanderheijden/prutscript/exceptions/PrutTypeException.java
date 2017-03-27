package nl.hvanderheijden.prutscript.exceptions;

import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;


public class PrutTypeException extends PrutException{

    private final String msg;

    public PrutTypeException(final List<Variable> stackTrace, final String msg) {
        super(stackTrace);
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return msg;
    }

}
