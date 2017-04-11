package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class UnableToLoadException extends PrutException {
    public UnableToLoadException(final String msg) {
        super(-1,msg);
    }
}
