package nl.hvanderheijden.prutscript.exceptions;

import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class ReferenceNotFoundException extends  PrutException{

    public ReferenceNotFoundException(int lineNr ,final String message) {
        super(lineNr, message);
    }

}
