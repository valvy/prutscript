package nl.hvanderheijden.prutscript;


import nl.hvanderheijden.prutscript.config.PrutRuntimeConfig;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Loader;
import org.junit.Test;

public class CodeTest {

    public CodeTest(){
        PrutRuntimeConfig config = PrutRuntimeConfig.getInstance();
    }

    @Test
    public void testAssign() throws PrutException {

        Loader loader = new Loader("/testcode/assign.ps");

        loader.execute();

    }

    @Test
    public void testImport() throws PrutException {
        Loader loader = new Loader("/testcode/testImport.ps");
        loader.execute();

    }

}
