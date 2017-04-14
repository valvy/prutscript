package nl.hvanderheijden.prutscript.prutlib;

import nl.hvanderheijden.prutscript.utils.Loader;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutAssertException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class System {
    private static final Logger logger = Logger.getLogger( System.class.getName() );
    private System(){
        throw new UnsupportedOperationException();
    }

    public static class PrutAssert extends Method{
        private PrutAssert() {
            super("assert", new ArrayList<PrutReference>(), new ArrayList<String>(), -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(arguments.size() != 2, this);

            if(!arguments.get(0).equals(arguments.get(1).getValue(context))){
                throw new PrutAssertException(1,String.format(
                        "Assertion failure, argument %s is not equal to %s",
                        arguments.get(0).prutToString(),
                        arguments.get(1).prutToString())
                );
            }




            return new PrutNumber(0,this.getLineNr());
        }

    }

    public static class Import extends Method {
        private Import() {
            super("import", new ArrayList<PrutReference>(), new ArrayList<String>(), -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {
            Assert.typeCheck(arguments.size() != 1, "Import statement has only 1 argument", this.getLineNr());
            Assert.typeCheck(!(arguments.get(0) instanceof PrutString), "Import statement can contain only strings", this.getLineNr());
            final String dat = ((PrutString)arguments.get(0)).getValue();
            final Loader loader = new Loader(dat);
            context.linkProgramToContext(loader.getProgram(context),this);

            return new PrutNumber(0,this.getLineNr());
        }
    }

    public static class Print extends Method{

        private Print() {
            super("print", new ArrayList<PrutReference>(), new ArrayList<String>(), -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(context == null,this);
            for(final Node node : arguments){
                Assert.isUndefined(node == null,this);
            //    logger.log(Level.INFO, node.prutToString());
                java.lang.System.out.print(node.prutToString());
             //   logger.info(node.prutToString());

            }
            java.lang.System.out.println();

            return new PrutNumber(0,this.getLineNr());
        }
    }

    public static class Input extends Method{
        private Input() {
            super("input", new ArrayList<PrutReference>(), new ArrayList<String>(),-1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Scanner reader = new Scanner(java.lang.System.in);
            new Print().executeMethod(context, arguments);
            reader.nextLine();
            return new PrutString("a", this.getLineNr());
        }
    }

    public static List<Method> getIOMethods() {
        final List<Method> ioMethods = new ArrayList<>();
        ioMethods.add(new Print());
        ioMethods.add(new Input());
        ioMethods.add(new Import());
        ioMethods.add(new PrutAssert());
        return ioMethods;
    }

}
