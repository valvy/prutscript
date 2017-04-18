package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.config.PrutRuntimeConfig;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Loader;


import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class Main {

    private static final Logger logger = Logger.getLogger( Main.class.getName() );

    private Main(){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] arguments){

        if(arguments.length == 0){
            //System.out.println("Please define a source file..");
            logger.log(Level.CONFIG, "Please define a source file..");
            return;
        }


        try {
            final Loader loader = new Loader(arguments[0]);
            loader.execute();
        } catch (PrutException e) {
            System.out.println(String.format("\u001B[31m %s \u001B[0m", e.getMessage()));
        }

    }

}
