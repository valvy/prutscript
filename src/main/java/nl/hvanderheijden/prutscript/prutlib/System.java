package nl.hvanderheijden.prutscript.prutlib;

import nl.hvanderheijden.prutscript.Main;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutAssertException;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class System {

    private System(){
        throw new UnsupportedOperationException();
    }

    public static class PrutAssert extends Method{
        private PrutAssert() {
            super("assert", null, null, -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(arguments.size() != 2, this.getLineNr());

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

        private Print() {
            super("print", null, null, -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(context == null,this.getLineNr());
            for(final Node node : arguments){
                Assert.isUndefined(node == null,this.getLineNr());
            //    logger.log(Level.INFO, node.prutToString());
                java.lang.System.out.print(node.prutToString());
             //   logger.info(node.prutToString());

            }
            java.lang.System.out.println();

            return new PrutNumber(0,-1);
        }
    }

    public static class Input extends Method{
        private Input() {
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
        final List<Method> ioMethods = new ArrayList<>();
        ioMethods.add(new Print());
        ioMethods.add(new Input());
        ioMethods.add(new PrutAssert());
        return ioMethods;
    }

}
