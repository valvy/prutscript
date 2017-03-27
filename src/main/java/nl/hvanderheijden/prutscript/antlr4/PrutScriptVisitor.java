package nl.hvanderheijden.prutscript.antlr4;// Generated from /Users/heikovanderheijden/Desktop/HeikoScript/src/main/antlr4/PrutScript.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PrutScriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PrutScriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PrutScriptParser#prutScript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrutScript(PrutScriptParser.PrutScriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(PrutScriptParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(PrutScriptParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExponentialExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentialExpr(PrutScriptParser.ExponentialExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(PrutScriptParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(PrutScriptParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodExpr(PrutScriptParser.MethodExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(PrutScriptParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpr(PrutScriptParser.ParenthesizedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link PrutScriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(PrutScriptParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrutScriptParser#endExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndExpr(PrutScriptParser.EndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrutScriptParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(PrutScriptParser.BlockContext ctx);
}