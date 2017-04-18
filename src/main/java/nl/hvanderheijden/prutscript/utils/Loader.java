package nl.hvanderheijden.prutscript.utils;

import nl.hvanderheijden.prutscript.antlr4.PrutScriptLexer;
import nl.hvanderheijden.prutscript.antlr4.PrutScriptParser;
import nl.hvanderheijden.prutscript.config.PrutRuntimeConfig;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.PrutOutput;
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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loader extends PrutOutput{

    private ProgramFactory.Program program;

    private Loader(){
        throw new UnsupportedOperationException();
    }

    public Loader(final String file) throws PrutException {
        ProgramFactory.Program program = null;
        try {
            URL resource = Loader.class.getResource(file);
            if (resource == null) {
                resource = Paths.get(file).toUri().toURL();
            }
            try (final InputStream stream = resource.openStream()) {
                Assert.typeCheck(stream == null, String.format("Could not load file %s ", file),0);
                program = loadProgram(stream);
            } catch (IOException | PrutException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                throw new UnableToLoadException(e);
            }
        } catch (final MalformedURLException ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new UnableToLoadException(ex);
        }

        this.program = program;



    }

    private ProgramFactory.Program loadProgram(final InputStream data) throws PrutException {
        PrutScriptLexer lexer = null;
        try {
            lexer = new PrutScriptLexer(new ANTLRInputStream(data));
            PrutScriptParser parser = new PrutScriptParser(new BufferedTokenStream(lexer));
            PrutScriptParser.PrutScriptContext tree = parser.prutScript();
            PrutVisitor visitor = new PrutVisitor();
            ProgramFactory.Program program = (ProgramFactory.Program) visitor.visit(tree);
            program.linkProgram(PrutStd.getInstance().getStandardIO(),new MethodCall("@IO",new ArrayList<PrutReference>(),1));
           // program.linkLibrary(program, new PrutContext(program), new MethodCall("@IO", new ArrayList<>(),1));
            program.checkValidity();
            return program;
        } catch (IOException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
            throw new UnableToLoadException("Could not load program");
        }


    }

/*
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
    }*/

    public ProgramFactory.Program getProgram(final PrutContext context) throws PrutException {
        program.execute(context);
        return program;
    }

    public boolean isCouldLoad() {
        return program != null;
    }

    public void execute() throws PrutException {
        if(program != null) {
            program.execute();
        }
    }



}
