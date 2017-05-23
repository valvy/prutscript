package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.exceptions.UndefinedBehaviourException;

/**
 * Base interface of all the tokens in PrutScript.
 * @author Heiko van der Heijden.
 */
public interface Node {

    int getLineNr();

    String prutToString() throws UndefinedBehaviourException;

    /**
     * Checks if the program doesn't contain errors.
     * @param pr the program you want to check it with.
     * @throws PrutException Error thrown when the program is invalid.
     */
    void checkValidity(ProgramFactory.Program pr) throws PrutException;

}
