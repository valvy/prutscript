package nl.hvanderheijden.prutscript.nodes;


import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.prutlib.MathOperations;
import nl.hvanderheijden.prutscript.utils.Assert;

public final class MathematicalExpr extends PrutReference {

    public enum Operation{
        Addition,
        Division,
        Multiplication,
        Substraction
    }


   // private final int lineNr;
    private final PrutReference leftValue;
    private final PrutReference rightValue;
    private final Operation operation;

    public MathematicalExpr(final PrutReference leftValue,
                            final PrutReference rightValue,
                            final Operation operation,
                            final int lineNr) {
        super(lineNr);
        this.leftValue = leftValue;
        this.rightValue =  rightValue;
        this.operation = operation;
    }


    @Override
    public PrutReference getValue(PrutContext context) throws PrutException {

        final PrutReference lh = this.getValue(context,leftValue);
        final PrutReference rh = this.getValue(context,rightValue);

        Assert.typeCheck(
                lh.getClass() != rh.getClass(),
                String.format("Can't operate on different types! (%s and %s)",lh.toString(), rh.toString()),
                this.getLineNr()
                );
        if(lh instanceof PrutNumber && rh instanceof PrutNumber){
            return MathOperations.executeMath((PrutNumber)lh, operation,(PrutNumber) rh).getValue(context);
        }
        throw new UnsupportedOperationException();
    }


    public PrutReference getValue(final PrutContext context, final PrutReference value) throws PrutException {
        return value.getValue(context);
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
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException{
        Assert.typeCheck(!(leftValue instanceof PrutReference || leftValue instanceof Variable));
        Assert.typeCheck(!(rightValue instanceof PrutReference || rightValue instanceof Variable));
        Assert.isUndefined(leftValue == null || rightValue == null);

    }

}
