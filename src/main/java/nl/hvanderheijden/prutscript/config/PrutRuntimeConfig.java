package nl.hvanderheijden.prutscript.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class PrutRuntimeConfig {

    private static final PrutRuntimeConfig ourInstance = new PrutRuntimeConfig();

    private static final Logger logger = Logger.getLogger( PrutRuntimeConfig.class.getName() );

    private final boolean debugMode;

    private PrutRuntimeConfig() {
        final String settingsfile = "/prutscript.properties";
        final String outputKey = "output";
        final String debugKey = "DEBUG";
        boolean isDebug = false;

        try(final InputStream stream = PrutRuntimeConfig.class.getResourceAsStream(settingsfile)){
            if(stream != null) {
                final Properties prop = new Properties();
                prop.load(stream);
                setSystemOut(prop.getProperty(outputKey));
                isDebug = setDebug(prop.getProperty(debugKey));
            }
        } catch (IOException e) {
            logger.log(Level.CONFIG, e.getMessage(), e);
        } finally {

            this.debugMode = isDebug;
        }

    }

    public boolean isDebugMode() {
        return debugMode;
    }



    private boolean setDebug(final String debug){
        if(debug == null || debug.isEmpty()){
            return false;
        } else {
            return Boolean.parseBoolean(debug);
        }
    }

    private void setSystemOut(final String str){
        

    }


    public static PrutRuntimeConfig getInstance() {
        return ourInstance;
    }
}
