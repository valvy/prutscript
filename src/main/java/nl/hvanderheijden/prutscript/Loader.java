package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.antlr4.PrutScriptLexer;
import nl.hvanderheijden.prutscript.antlr4.PrutScriptParser;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.UnableToLoadException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loader {

    private final ProgramFactory.Program program;

    private static final Logger logger = Logger.getLogger( Loader.class.getName() );

    public Loader(final InputStream data) throws UnableToLoadException {
        try {
            PrutScriptLexer lexer = new PrutScriptLexer(new ANTLRInputStream(data));
            PrutScriptParser parser = new PrutScriptParser(new BufferedTokenStream(lexer));
            PrutScriptParser.PrutScriptContext tree = parser.prutScript();
            PrutVisitor visitor = new PrutVisitor();
            program = (ProgramFactory.Program) visitor.visit(tree);

        } catch (final IOException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
            throw new UnableToLoadException(null);
        }
    }

    public void execute() throws PrutException {
        program.checkValidity();
        program.execute();
    }



}
