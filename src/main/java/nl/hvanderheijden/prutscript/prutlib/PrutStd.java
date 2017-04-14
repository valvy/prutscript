package nl.hvanderheijden.prutscript.prutlib;


import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.nodes.Method;

public final class PrutStd {

    private static final PrutStd ourInstance = new PrutStd();

    private final ProgramFactory.Program io;

    public static PrutStd getInstance() {
        return ourInstance;
    }

    private PrutStd() {
        final ProgramFactory factory = new ProgramFactory();
        for(final Method m : System.getIOMethods()){
            factory.addToken(m);
        }
        this.io = factory.getProgram();
    }

    public ProgramFactory.Program getStandardIO(){
        return this.io;
    }
}
