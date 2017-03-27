package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;

import java.util.List;

public interface Node {

    String prutToString();

    String varGetName(final String alt);

    Value varGetValue(final List<Variable> stack, final ProgramFactory.Program pr) throws PrutException;

    void checkValidity(ProgramFactory.Program pr) throws PrutException;

}
