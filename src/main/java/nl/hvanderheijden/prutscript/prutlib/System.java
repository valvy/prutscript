package nl.hvanderheijden.prutscript.prutlib;

import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.utils.Loader;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutAssertException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.core.nodes.*;
import nl.hvanderheijden.prutscript.utils.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;


public final class System {

    private System(){
        throw new UnsupportedOperationException();
    }

    public static class PrutAssert extends Method{
        private PrutAssert() {
            super("assert", new ArrayList<>(), new ArrayList<>(), -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {

            Assert.isUndefined(arguments.size() != 2, this);

            if(!arguments.get(0).equals(arguments.get(1).execute(context))){
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
            super("ymportearje", new ArrayList<>(), new ArrayList<>(), -1);
        }

        @Override
        public PrutReference executeMethod(final PrutContext context,
                                           final List<PrutReference> arguments) throws PrutException {
            Assert.typeCheck(arguments.size() != 1, "Import statement has only 1 argument", this.getLineNr());
            Assert.typeCheck(!(arguments.get(0) instanceof PrutString), "Import statement can contain only strings", this.getLineNr());
            try {
                final String dat = ((PrutString) arguments.get(0)).getValue();
                final Loader loader = new Loader(dat);
                final ProgramFactory.Program pr = loader.getProgram(context);
                context.linkProgramToContext(pr, this);
                return new PrutNumber(0, this.getLineNr());
            } catch (final UnableToLoadException ex){
                logger.log(Level.WARNING, ex.getMessage(), ex);
                return new PrutNumber(-1, this.getLineNr());
            }
        }
    }

    public static class Print extends Method{

        private Print() {
            super("sjen", new ArrayList<>(), new ArrayList<>(), -1);
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
            super("Toetseboerd", new ArrayList<>(), new ArrayList<>(),-1);
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
