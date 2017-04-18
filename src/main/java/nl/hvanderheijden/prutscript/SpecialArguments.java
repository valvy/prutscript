package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Loader;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

/**
 * Created by heikovanderheijden on 18/04/2017.
 */
public final class SpecialArguments{

    private static final Logger logger = Logger.getLogger( Main.class.getName() );

    private SpecialArguments(){
        throw new UnsupportedOperationException();
    }

    public static void executeSpecialArgument(final String arg){

        if ("--version".equals(arg)) {
            try {
                final Loader l = new Loader("/arguments/Version.ps");
                l.execute();
                exit(0);
            } catch (PrutException e) {
               logger.log(Level.SEVERE, e.getMessage(),e);
               exit(-1);
            }

        }

        if("--repl".equals(arg)){
            try {
                Loader l = new Loader("/arguments/repl/Main.ps");
                l.execute();
                exit(0);
            } catch (PrutException e) {
                logger.log(Level.SEVERE, e.getMessage(),e);
                exit(-1);
            }
        }
    }
}
