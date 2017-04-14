package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Loader;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main {

    private static final Logger logger = Logger.getLogger( Main.class.getName() );

    private Main(){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] arguments){

        if(arguments.length == 0){
            logger.log(Level.INFO, "Please define a source file..");
            return;
        }


        try {
            final Loader loader = new Loader(arguments[0]);
            loader.execute();
        } catch (PrutException e) {
            logger.log(Level.WARNING , String.format("\u001B[31m %s \u001B[0m", e.getMessage()),e);
        }
/*
        try(final InputStream is = new FileInputStream(arguments[0])){

            Loader loader = new Loader(is);
            loader.execute();
        }catch(final IOException ex){
            logger.log(Level.SEVERE, ex.getMessage(),ex);
        } catch (final PrutException e) {
            logger.log(Level.WARNING , String.format("\u001B[31m %s \u001B[0m", e.getMessage()),e);
        }*/

    }

}
