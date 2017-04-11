package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.UnableToLoadException;



import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main {

    private static final Logger logger = Logger.getLogger( Main.class.getName() );

    private Main(){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] arguments) throws UnableToLoadException {

        try(final InputStream is = Main.class.getResourceAsStream("/lib/test.ps")){

            Loader loader = new Loader(is);
            
            loader.execute();
        }catch(final IOException ex){
            logger.log(Level.SEVERE, ex.getMessage(),ex);
        } catch (final PrutException e) {
            System.out.println(String.format("\u001B[31m %s \u001B[0m", e.getMessage())
            );
         //   logger.error(e);
        }

    }

}
