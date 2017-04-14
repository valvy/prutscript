package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.exceptions.UndefinedBehaviourException;


public interface Node {

    int getLineNr();

    String prutToString() throws UndefinedBehaviourException;

    void checkValidity(ProgramFactory.Program pr) throws PrutException;

}
