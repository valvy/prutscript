package nl.hvanderheijden.prutscript.core.nodes;


import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.MathOperations;
import nl.hvanderheijden.prutscript.utils.Assert;

/**
 * Stores a mathematical expression.
 * @author Heiko van der Heijden.
 */
public final class MathematicalExpr extends PrutReference {

    public enum Operation{
        Addition,
        Division,
        Multiplication,
        Substraction
    }

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
    public PrutReference execute(PrutContext context) throws PrutException {

        final PrutReference lh = this.getValue(context,leftValue);
        final PrutReference rh = this.getValue(context,rightValue);

        Assert.typeCheck(
                lh.getClass() != rh.getClass(),
                String.format("Can't operate on different types! (%s and %s)",lh.toString(), rh.toString()),
                this.getLineNr()
                );
        if(lh instanceof PrutNumber && rh instanceof PrutNumber){
            return MathOperations.executeMath((PrutNumber)lh, operation,(PrutNumber) rh).execute(context);
        }
        throw new UnsupportedOperationException();
    }


    public PrutReference getValue(final PrutContext context, final PrutReference value) throws PrutException {
        return value.execute(context);
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
        Assert.isUndefined(leftValue == null || rightValue == null, this);
        Assert.typeCheck(!(leftValue instanceof PrutNumber || leftValue instanceof Variable),this);
        Assert.typeCheck(!(rightValue instanceof PrutNumber || rightValue instanceof Variable),this);
    }

}
