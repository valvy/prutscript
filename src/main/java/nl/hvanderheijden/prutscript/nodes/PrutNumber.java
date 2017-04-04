package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;

public final class PrutNumber extends PrutReference {

    private final double value;

    private PrutNumber(){
        throw new UnsupportedOperationException();
    }

    public PrutNumber(final double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object other){
        if(other == this) {
            return true;
        }
        if(other instanceof PrutNumber){
            return ((PrutNumber) other).value == value;
        }

        return false;
    }

    @Override
    public PrutReference getValue(PrutContext context) throws PrutException {
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
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {

    }
}
