package nl.hvanderheijden.prutscript.antlr4;// Generated from /Users/heikovanderheijden/Desktop/prutscript/src/main/antlr4/PrutScript.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrutScriptParser}.
 */
public interface PrutScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrutScriptParser#prutScript}.
	 * @param ctx the parse tree
	 */
	void enterPrutScript(PrutScriptParser.PrutScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrutScriptParser#prutScript}.
	 * @param ctx the parse tree
	 */
	void exitPrutScript(PrutScriptParser.PrutScriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(PrutScriptParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(PrutScriptParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(PrutScriptParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(PrutScriptParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExponentialExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExponentialExpr(PrutScriptParser.ExponentialExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExponentialExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExponentialExpr(PrutScriptParser.ExponentialExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(PrutScriptParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(PrutScriptParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(PrutScriptParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(PrutScriptParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodExpr(PrutScriptParser.MethodExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodExpr(PrutScriptParser.MethodExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariable(PrutScriptParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariable(PrutScriptParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(PrutScriptParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(PrutScriptParser.ParenthesizedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(PrutScriptParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(PrutScriptParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrutScriptParser#endExpr}.
	 * @param ctx the parse tree
	 */
	void enterEndExpr(PrutScriptParser.EndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrutScriptParser#endExpr}.
	 * @param ctx the parse tree
	 */
	void exitEndExpr(PrutScriptParser.EndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrutScriptParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(PrutScriptParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrutScriptParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(PrutScriptParser.BlockContext ctx);
}