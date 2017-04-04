package nl.hvanderheijden.prutscript.prutlib;

import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutAssertException;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class System {


    public static class PrutAssert extends Method{
        public PrutAssert() {
            super("assert", null, null);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(arguments.size() != 2);

            if(!arguments.get(0).equals(arguments.get(1).getValue(context))){
             //   java.lang.System.out.print(arguments);
                throw new PrutAssertException(null,String.format(
                        "Assertion failure, argument %s is not equal to %s",
                        arguments.get(0).prutToString(),
                        arguments.get(1).prutToString())
                );
            }




            return new PrutNumber(0);
        }

    }

    public static class Print extends Method{

        public Print() {
            super("print", null, null);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {
            for(final Node node : arguments){
                Assert.isUndefined(node == null);

                java.lang.System.out.print(node.prutToString());

            }
            java.lang.System.out.println();

            return new PrutNumber(0);
        }
    }

    public static class Input extends Method{
        public Input() {
            super("input", null, null);
        }
        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Scanner reader = new Scanner(java.lang.System.in);
            new Print().executeMethod(context, arguments);
            reader.nextLine();
            return new PrutString("a");
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
