package nl.hvanderheijden.prutscript.utils;

import nl.hvanderheijden.prutscript.antlr4.PrutScriptLexer;
import nl.hvanderheijden.prutscript.antlr4.PrutScriptParser;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.PrutVisitor;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.core.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.core.nodes.MethodCall;
import nl.hvanderheijden.prutscript.core.nodes.PrutReference;
import nl.hvanderheijden.prutscript.prutlib.PrutStd;
import nl.hvanderheijden.prutscript.core.ProgramFactory;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loader {

    private  ProgramFactory.Program program;

    private static final Logger logger = Logger.getLogger( Loader.class.getName() );

    private final boolean couldLoad;

    private Loader(){
        throw new UnsupportedOperationException();
    }

    public Loader(final InputStream data){
        boolean loaded = true;
        try {
            PrutScriptLexer lexer = new PrutScriptLexer(new ANTLRInputStream(data));
            PrutScriptParser parser = new PrutScriptParser(new BufferedTokenStream(lexer));
            PrutScriptParser.PrutScriptContext tree = parser.prutScript();
            PrutVisitor visitor = new PrutVisitor();
            program = (ProgramFactory.Program) visitor.visit(tree);
            program.linkProgram(PrutStd.getInstance().getStandardIO(),new MethodCall("@IO",new ArrayList<PrutReference>(),1));
            program.checkValidity();

        } catch (final IOException | PrutException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
            loaded = false;
        }

        this.couldLoad = loaded;
    }

    public ProgramFactory.Program getProgram(final PrutContext context) throws PrutException {
        program.execute(context);
        return program;
    }

    public boolean isCouldLoad() {
        return couldLoad;
    }

    public void execute() throws PrutException {
        if(couldLoad) {
            program.execute();
        }
    }



}
