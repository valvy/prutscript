package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;

/**
 * Stores a double decimal value in memory.
 * @author Heiko van der Heijden
 */
public final class PrutNumber extends PrutReference {

    private final double value;

    public PrutNumber(final double value, final int lineNr){
        super(lineNr);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        return other instanceof PrutNumber && ((PrutNumber) other).value == value;

    }

    @Override
    public PrutReference execute(PrutContext context) throws PrutException {
        return this;
    }

    @Override
    public String toString(){
        return String.format("Prut Double : %f", value);
    }

    @Override
    public String prutToString() {
        return String.format("%f", value );
    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException { }
}
