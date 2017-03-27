package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.UnableToLoadException;
import sun.misc.IOUtils;


import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] arguments) throws UnableToLoadException {

        try(final InputStream is = Main.class.getResourceAsStream("/lib/test.ps")){

            Loader loader = new Loader(is);
            
            loader.execute();
        }catch(IOException ex){

        } catch (PrutException e) {
            e.printStackTrace();
        }

    }

}
