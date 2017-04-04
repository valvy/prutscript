package nl.hvanderheijden.prutscript.prutlib;

import nl.hvanderheijden.prutscript.nodes.MathematicalExpr;
import nl.hvanderheijden.prutscript.nodes.PrutNumber;

public final class MathOperations {

    private MathOperations(){
        throw new UnsupportedOperationException();
    }

    public static PrutNumber executeMath(final PrutNumber lh,
                                         final MathematicalExpr.Operation operation,
                                         final PrutNumber rh){
        switch(operation){
            case Addition:{
                return new PrutNumber(lh.getValue() + rh.getValue());
            }
            case Substraction: {
                return new PrutNumber(lh.getValue() - rh.getValue());
            }
            case Division:{
                return new PrutNumber(lh.getValue() / rh.getValue());
            }
            default:{
                return new PrutNumber(lh.getValue() * rh.getValue());
            }
        }
    }
}
