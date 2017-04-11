package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class UndefinedBehaviourException extends PrutException{
    public UndefinedBehaviourException(final int lineNr, final String msg) {
        super(msg,lineNr);
    }
}
