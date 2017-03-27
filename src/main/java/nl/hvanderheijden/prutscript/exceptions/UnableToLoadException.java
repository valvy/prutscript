package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class UnableToLoadException extends PrutException {
    public UnableToLoadException(List<Variable> stackTrace) {
        super(stackTrace);
    }
}
