package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.UnableToLoadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.IOUtils;


import java.io.IOException;
import java.io.InputStream;

public final class Main {

    private Main(){
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] arguments) throws UnableToLoadException {

        try(final InputStream is = Main.class.getResourceAsStream("/lib/test.ps")){

            Loader loader = new Loader(is);
            
            loader.execute();
        }catch(IOException ex){
            logger.error(ex);
        } catch (PrutException e) {
            System.out.println(String.format("\u001B[31m %s \u001B[0m", e.getMessage())
            );
         //   logger.error(e);
        }

    }

}
