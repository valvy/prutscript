package nl.hvanderheijden.prutscript.core;

import nl.hvanderheijden.prutscript.config.PrutRuntimeConfig;

import java.util.logging.Logger;

/**
 * Class to disable logger.
 * @author Heiko van der Heijden
 */
public abstract class PrutOutput {

    protected static final Logger logger = Logger.getLogger( PrutOutput.class.getName() );

    public PrutOutput(){
        if(!PrutRuntimeConfig.getInstance().isDebugMode()) {
            logger.setUseParentHandlers(false);
        }
    }


}
