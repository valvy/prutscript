package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.util.List;

public class PrutRedefinedException extends PrutException{


    public PrutRedefinedException(int lineNr, final String message) {
        super(lineNr,message);
    }

}
