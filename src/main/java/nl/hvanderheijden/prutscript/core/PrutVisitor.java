package nl.hvanderheijden.prutscript.core;

import nl.hvanderheijden.prutscript.antlr4.PrutScriptBaseVisitor;
import nl.hvanderheijden.prutscript.antlr4.PrutScriptParser;
import nl.hvanderheijden.prutscript.core.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.core.nodes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrutVisitor<T> extends PrutScriptBaseVisitor<T> {

    private static final Logger logger = Logger.getLogger( PrutVisitor.class.getName() );

    @Override
    public T visitPrutScript(PrutScriptParser.PrutScriptContext ctx) {
        final ProgramFactory program = new ProgramFactory();
        try {
            for (final PrutScriptParser.ExpressionContext d : ctx.expression()) {
                program.addToken((Node) visit(d));
            }

            for (final PrutScriptParser.BlockContext blocks : ctx.block()) {
                program.addToken((Node) visit(blocks));
            }
        } catch(final UnableToLoadException ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return (T) program.getProgram();
    }

    @Override
    public T visitStringExpr(PrutScriptParser.StringExprContext ctx) {
        final PrutString str = new PrutString(
                ctx.getText().substring(1, ctx.getText().length() - 1),
                ctx.getStart().getLine()
        );
        return (T) str;

    }

    @Override
    public T visitVariable(PrutScriptParser.VariableContext ctx) {
        final Variable var = new Variable(ctx.getText(), ctx.getStart().getLine());
        return (T) var;
    }

    @Override
    public T visitAssignExpr(PrutScriptParser.AssignExprContext ctx) {
        final Variable var = new Variable(
                (PrutReference) visit(ctx.expression()),
                ctx.identifier.getText(),
                ctx.getStart().getLine()

        );
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
                (PrutReference) visit(ctx.expression(0)),
                (PrutReference) visit(ctx.expression(1)),
                op,
                ctx.getStart().getLine()
        );
        return (T) mathematicalExpr;
    }

    @Override
    public T visitExponentialExpr(PrutScriptParser.ExponentialExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitMethodExpr(PrutScriptParser.MethodExprContext ctx) {
        final List<PrutReference> data = new ArrayList<>();
        for(PrutScriptParser.ExpressionContext d : ctx.expression()){
            data.add((PrutReference) visit(d));
        }

        final MethodCall call = new MethodCall(
                ctx.identifier.getText(),
                data,
                ctx.getStart().getLine()
        );
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
                (PrutReference) visit(ctx.expression(0)),
                (PrutReference) visit(ctx.expression(1)),
                op,
                ctx.getStart().getLine()
        );
        return (T) mathematicalExpr;
    }

    @Override
    public T visitNumberExpr(PrutScriptParser.NumberExprContext ctx) {

        final PrutNumber integer = new PrutNumber(Double.parseDouble(ctx.getText()), ctx.getStart().getLine());// new PrutNumber(Integer.getInteger(ctx.getText()));
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

        List<PrutReference> data = new ArrayList<>();
        for(PrutScriptParser.ExpressionContext d : ctx.expression()){
            data.add((PrutReference) visit(d));
        }
        final Method method = new Method(ctx.identity.getText(), data, args, ctx.getStart().getLine());
        return (T) method;
    }
}
