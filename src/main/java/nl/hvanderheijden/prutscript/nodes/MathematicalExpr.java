package nl.hvanderheijden.prutscript.nodes;


import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.List;

public class MathematicalExpr extends Value {


    @Override
    public String getType() {
        return null;
    }

    public enum Operation{
        Addition,
        Division,
        Multiplication,
        Substraction
    }

    private final Node leftValue;
    private final Node rightValue;

    private final Operation operation;

    public MathematicalExpr(final Node leftValue,
                            final Node rightValue,
                            final Operation operation) {

        this.leftValue = leftValue;
        this.rightValue =  rightValue;
        this.operation = operation;
    }

    private Node getValue(final Node node , final List<Variable> stack, final ProgramFactory.Program program) throws PrutException {

        return node.varGetValue(stack, program);
    }

    @Override
    public String toString(){
        return String.format("left: %s, right %s", leftValue.toString(), rightValue.toString());
    }



    @Override
    public String prutToString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String varGetName(final String alt) {
//        throw new UnsupportedOperationException();
        return alt;
    }


    @Override
    public Value varGetValue(final List<Variable> stack, final ProgramFactory.Program program) throws PrutException {

        Node lval = getValue(leftValue, stack, program);
        Node rval = getValue(rightValue, stack, program);
        if(rval instanceof PrutNumber){
            switch(operation){
                case Addition:{
                    return new PrutNumber(
                            ((PrutNumber)lval.varGetValue(stack,program)).getValue() + ((PrutNumber)rval.varGetValue(stack,program)).getValue()
                    );
                }
                case Substraction: {
                    return new PrutNumber(
                            ((PrutNumber)lval.varGetValue(stack,program)).getValue() - ((PrutNumber)rval.varGetValue(stack,program)).getValue()
                    );
                }
                case Division:{

                    return new PrutNumber(
                            ((PrutNumber)lval.varGetValue(stack,program)).getValue() / ((PrutNumber)rval.varGetValue(stack,program)).getValue()
                    );
                }
                default:{
                    return new PrutNumber(
                            ((PrutNumber)lval.varGetValue(stack,program)).getValue() * ((PrutNumber)rval.varGetValue(stack,program)).getValue()
                    );
                }

            }
        }



        return new PrutNumber(-1);

    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException{
        Assert.typeCheck(!(leftValue instanceof Value || leftValue instanceof Variable));
        Assert.typeCheck(!(rightValue instanceof Value || rightValue instanceof Variable));
        Assert.isUndefined(leftValue == null || rightValue == null);



    }

}
