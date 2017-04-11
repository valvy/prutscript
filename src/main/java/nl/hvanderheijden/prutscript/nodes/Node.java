package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;

import java.util.List;

public interface Node {

    int getLineNr();

    String prutToString();

    void checkValidity(ProgramFactory.Program pr) throws PrutException;

}
