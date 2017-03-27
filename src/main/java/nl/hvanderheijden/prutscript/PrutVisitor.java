package nl.hvanderheijden.prutscript;




import nl.hvanderheijden.prutscript.antlr4.PrutScriptBaseVisitor;
import nl.hvanderheijden.prutscript.antlr4.PrutScriptParser;
import nl.hvanderheijden.prutscript.nodes.*;

import java.util.List;
import java.util.Vector;

public class PrutVisitor<T> extends PrutScriptBaseVisitor<T> {

    @Override
    public T visitPrutScript(PrutScriptParser.PrutScriptContext ctx) {
        final ProgramFactory program = new ProgramFactory();
        for(final PrutScriptParser.ExpressionContext d : ctx.expression()){
            program.addToken((Node)visit(d));
        }

        for(final PrutScriptParser.BlockContext blocks : ctx.block()){
            program.addToken((Node)visit(blocks));
        }

        return (T) program.getProgram();
    }

    @Override
    public T visitStringExpr(PrutScriptParser.StringExprContext ctx) {
        final PrutString str = new PrutString(ctx.getText());
        return (T) str;

    }

    @Override
    public T visitVariable(PrutScriptParser.VariableContext ctx) {
        final Variable var = new Variable(ctx.getText());
        return (T) var;
    }

    @Override
    public T visitAssignExpr(PrutScriptParser.AssignExprContext ctx) {
        final Variable var = new Variable(
                (Value) visit(ctx.expression()),
                ctx.identifier.getText());
        return (T) var;
    }

    @Override
    public T visitMultiplicativeExpr(PrutScriptParser.MultiplicativeExprContext ctx) {

        MathematicalExpr.Operation op = MathematicalExpr.Operation.Multiplication;

        switch (ctx.operatorToken.getType()){
            case PrutScriptParser.DIVIDE: op = MathematicalExpr.Operation.Division; break;
            default: op = MathematicalExpr.Operation.Multiplication; break;
        }

        final MathematicalExpr mathematicalExpr = new MathematicalExpr(
                (Node)visit(ctx.expression(0)),
                (Node)visit(ctx.expression(1)),
                op
        );
        return (T) mathematicalExpr;
    }

    @Override
    public T visitExponentialExpr(PrutScriptParser.ExponentialExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitMethodExpr(PrutScriptParser.MethodExprContext ctx) {
        final List<Node> data = new Vector<Node>();
        for(PrutScriptParser.ExpressionContext d : ctx.expression()){
            data.add((Node)visit(d));
        }

        final MethodCall call = new MethodCall(ctx.identifier.getText(),data);
        return (T) call;
    }

    @Override
    public T visitAdditiveExpr(PrutScriptParser.AdditiveExprContext ctx) {
        MathematicalExpr.Operation op = MathematicalExpr.Operation.Addition;

        switch (ctx.operatorToken.getType()){
            case PrutScriptParser.SUBTRACT: op = MathematicalExpr.Operation.Substraction; break;
            default: op = MathematicalExpr.Operation.Addition; break;
        }

        final MathematicalExpr mathematicalExpr = new MathematicalExpr(
                (Node)visit(ctx.expression(0)),
                (Node)visit(ctx.expression(1)),
                op
        );
        return (T) mathematicalExpr;
    }

    @Override
    public T visitNumberExpr(PrutScriptParser.NumberExprContext ctx) {

        final PrutNumber integer = new PrutNumber(Double.parseDouble(ctx.getText()));// new PrutNumber(Integer.getInteger(ctx.getText()));
        return (T) integer;
      //  return visitChildren(ctx);
    }

    @Override
    public T visitParenthesizedExpr(PrutScriptParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public T visitBlock(PrutScriptParser.BlockContext ctx) {
        final String name = ctx.identity.getText();
        final List<String> args = new Vector<>();
        for(int i = 1; i < ctx.Identifier().size(); i++){

            args.add(ctx.Identifier(i).getText());
        }

        List<Node> data = new Vector<Node>();
        for(PrutScriptParser.ExpressionContext d : ctx.expression()){
            data.add((Node)visit(d));
        }
        final Method method = new Method(ctx.identity.getText(), data, args);
        return (T) method;
    }
}
