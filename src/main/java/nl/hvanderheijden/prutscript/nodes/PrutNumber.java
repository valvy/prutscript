package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;

import java.util.List;

public class PrutNumber extends Value {

    private final double value;

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
    public String getType() {
        return "Integer";
    }

    @Override
    public String toString(){
        return String.format("Integer : %d", value);
    }

    @Override
    public String prutToString() {
        return String.format("%f", value );
    }



    @Override
    public String varGetName(final String alt) {
        return alt;
    }


    @Override
    public Value varGetValue(final List<Variable> stack, final ProgramFactory.Program pr) {
        return this;
    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {

    }
}
