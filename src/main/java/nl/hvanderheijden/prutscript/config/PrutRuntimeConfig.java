package nl.hvanderheijden.prutscript.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class PrutRuntimeConfig {

    private static PrutRuntimeConfig ourInstance = new PrutRuntimeConfig();

    private static final Logger logger = Logger.getLogger( PrutRuntimeConfig.class.getName() );



    private PrutRuntimeConfig() {
        final String settingsfile = "prutscript.properties";
        final String outputKey = "output";



        try(final InputStream stream = PrutRuntimeConfig.class.getResourceAsStream(settingsfile)){
            final Properties prop = new Properties();
            prop.load(stream);
            setSystemOut(prop.getProperty(outputKey));
        } catch (IOException e) {
            logger.log(Level.CONFIG, e.getMessage(), e);
        }
    }


    private void setSystemOut(final String str){
        

    }


    public static PrutRuntimeConfig getInstance() {
        return ourInstance;
    }
}
