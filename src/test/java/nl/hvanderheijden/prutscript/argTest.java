package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.nodes.Method;
import nl.hvanderheijden.prutscript.nodes.MethodCall;
import nl.hvanderheijden.prutscript.nodes.Node;
import nl.hvanderheijden.prutscript.nodes.PrutString;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class argTest {

    @Test
    public void testLoader() throws PrutException {
        String src =
                "print \"test\" \n print 1 \n print \"tes2 \"";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

    @Test
    public void printTest() throws PrutException {
        ProgramFactory factory = new ProgramFactory();
        List<Node> values = new Vector<Node>();
        values.add(new PrutString("Heloworld"));
        factory.addToken(new MethodCall("print", values));
        factory.getProgram().execute();
    }

    @Test
    public void testVersion() throws UnableToLoadException {
        String arg[] = new String[2];
        Main.main(arg);
    }
}
