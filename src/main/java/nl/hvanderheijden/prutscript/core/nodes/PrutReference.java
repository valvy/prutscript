package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;

/**
 * An abstract instruction.
 * This is the base class for handling and executing instructions.
 * @author Heiko van der Heijden.
 */
public abstract class PrutReference implements Node {

    private final int lineNr;

    private PrutReference(){
        throw new UnsupportedOperationException();
    }

    public PrutReference(final int lineNr){
        this.lineNr = lineNr;
    }

    /**
     * In some cases you want the name of a variable.
     * But if this is not a variable you want an alternative name.
     * This will place the alternative name in case it's not a variable.
     * @param alt the alternative name
     * @return the name you can use this reference with.
     */
    public String getName(final String alt){
        return alt;
    }

    @Override
    public int getLineNr() {
        return this.lineNr;
    }

    /**
     * Executes this instruction with the specified context.
     * When done it will always return a new instruction.
     *
     * @param context the context to execute this instruction with.
     * @return the result of the execution.
     * @throws PrutException Error encountered while executing.
     */
    public abstract PrutReference execute(final PrutContext context) throws PrutException;
}
