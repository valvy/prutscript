package nl.hvanderheijden.prutscript.prutlib;


import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.core.nodes.Method;

/**
 * Singleton to load all the different system classes.
 * @author Heiko van der Heijden.
 */
public final class PrutStd {

    private static PrutStd ourInstance;

    private final ProgramFactory.Program io;

    private PrutStd() throws UnableToLoadException {
        final ProgramFactory factory = new ProgramFactory();
        for(final Method m : System.getIOMethods()){
            factory.addToken(m);
        }
        this.io = factory.getProgram();
    }

    public static PrutStd getInstance() throws UnableToLoadException {
        if(ourInstance == null){
            ourInstance = new PrutStd();
        }
        return ourInstance;
    }

    public ProgramFactory.Program getStandardIO() {
        return this.io;
    }
}
