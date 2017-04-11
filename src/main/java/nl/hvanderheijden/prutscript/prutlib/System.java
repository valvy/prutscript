package nl.hvanderheijden.prutscript.prutlib;

import nl.hvanderheijden.prutscript.Main;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutAssertException;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class System {

    private static final Logger logger = LogManager.getLogger(System.class.getName());

    public static class PrutAssert extends Method{
        public PrutAssert() {
            super("assert", null, null, -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(arguments.size() != 2);

            if(!arguments.get(0).equals(arguments.get(1).getValue(context))){
             //   java.lang.System.out.print(arguments);
                throw new PrutAssertException(1,String.format(
                        "Assertion failure, argument %s is not equal to %s",
                        arguments.get(0).prutToString(),
                        arguments.get(1).prutToString())
                );
            }




            return new PrutNumber(0,1);
        }

    }

    public static class Print extends Method{

        public Print() {
            super("print", null, null, -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(context == null);
            for(final Node node : arguments){
                Assert.isUndefined(node == null);

                java.lang.System.out.print(node.prutToString());
             //   logger.info(node.prutToString());

            }
            java.lang.System.out.println();

            return new PrutNumber(0,-1);
        }
    }

    public static class Input extends Method{
        public Input() {
            super("input", null, null,-1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Scanner reader = new Scanner(java.lang.System.in);
            new Print().executeMethod(context, arguments);
            reader.nextLine();
            return new PrutString("a", 0);
        }
    }

    public static List<Method> getIOMethods(){
        final List<Method> ioMethods = new Vector<Method>();
        ioMethods.add(new Print());
        ioMethods.add(new Input());
        ioMethods.add(new PrutAssert());
        return ioMethods;
    }

}
