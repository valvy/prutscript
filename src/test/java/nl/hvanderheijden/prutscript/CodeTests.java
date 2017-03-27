package nl.hvanderheijden.prutscript;


import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.nodes.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class CodeTests {


    @Test
    public void bigMethod() throws PrutException {
        final String src = "func arg { print arg * 10; }; func 9;";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

    @Test
    public void mathInMethod() throws PrutException {
        final String src = "print 10*2 * 9;";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

    @Test
    public void helloWorld() throws PrutException {
        final String src = "print \"Hello world\";";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

    @Test
    public void additionTest() throws PrutException {
        final String src = "x = 10 - 10 + 3; print x;";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

    @Test
    public void variableTest() throws PrutException {
        final String src = "y = 10\n x = 2; print y;";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

    @Test
    public void blockTest() throws PrutException {
        final List<Node> methodIns = new Vector<>();
        final List<Node> printArgs1 = new Vector<>();
        final List<Node> printArgs2 = new Vector<>();
        printArgs1.add(new PrutString("hello "));
        printArgs1.add(new PrutString("world"));
        printArgs2.add(new PrutNumber(1) );

        methodIns.add(new MethodCall("print", printArgs1));
        methodIns.add(new MethodCall("print", printArgs2 ));
        final Method method = new Method("test",methodIns, new Vector<String>());
        ProgramFactory factory = new ProgramFactory();
        factory.addToken(method);
        factory.addToken(new MethodCall("test",new Vector<Node>()));
        final ProgramFactory.Program program = factory.getProgram();
        program.execute();


    }

    @Test
    public void blockTest2() throws PrutException {
        final String src = "test x { print x; print x; }; test 6;";
        InputStream is = new ByteArrayInputStream( src.getBytes() );
        Loader loader = new Loader(is);
        loader.execute();
    }

}
