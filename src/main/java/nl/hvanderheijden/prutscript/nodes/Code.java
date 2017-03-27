package nl.hvanderheijden.prutscript.nodes;

import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Heiko van der Heijden.
 * This class manages the execution of a code block
 *
 */
public abstract class Code {

    protected final List<Node> instructions;

    protected final List<String> arguments;

    public Code(){
        this.instructions = new Vector<>();
        this.arguments = new Vector<>();
    }

    public Variable getVariable(final String name,
                                final List<Variable> stack) throws ReferenceNotFoundException {
        for(final Node v : stack){
            if(v instanceof  Variable){
                Variable var = (Variable) v;
                if(var.getName().equals(name)){
                    return var;
                }
            }
        }
        throw new ReferenceNotFoundException(stack,String.format("Variable %s is not defined", name));
    }

    protected void addVariable(final List<Variable> stack, final Node node, final ProgramFactory.Program program) throws PrutException {
        if(node instanceof Variable){
            for(final Variable i : stack){
                if(i.getName().equals(((Variable) node).getName())){
                    throw new PrutRedefinedException(stack, String.format("Variable %s has already been defined", i.getName()));
                }
            }
            stack.add((Variable) node);

        }
    }

    private List<Variable> getArguments(final List<Variable> stack,
                                        final MethodCall call,
                                        final ProgramFactory.Program program) throws PrutException {
        final List<Variable> res = new ArrayList<>();
        int i = 0;
        for(final Node arg : call.getArguments()){
            i++;
            res.add(new Variable(arg.varGetName(Integer.toString(i)),arg.varGetValue(stack,program)));

        }

        return res;
    }

    private Value callMethod(final List<Variable> stack,
                            final Node node,
                            final ProgramFactory.Program program) throws PrutException{
        if(node instanceof MethodCall){
            final MethodCall method = (MethodCall)node;

            final List<Variable> args = getArguments(stack,method,program);


            return node.varGetValue(args,program);
        }

        return null;

    }


    public Value executeMethod(final ProgramFactory.Program program,
                               final List<Node> arguments) throws PrutException{
        Assert.typeCheck(arguments.size() != this.arguments.size());

        final List<Variable> stack = new Vector<>();
        /**
         * Add the arguments to the stack
         */
        for(int i = 0; i < arguments.size(); i++) {

            Assert.isUndefined(arguments.get(i) == null);
            stack.add(new Variable(
                    arguments.get(i).varGetValue(stack,program),
                    this.arguments.get(i)
            ));
        }


        for(int i = 0; i < instructions.size() -1; i++){
            addVariable(stack,instructions.get(i), program);
           callMethod(stack,instructions.get(i), program);

        }

        //Do a tailcall
        Node lastItem = instructions.get(instructions.size() - 1);
        if(lastItem instanceof MethodCall){
            ((MethodCall) lastItem).preFillStack(getArguments(stack, (MethodCall) lastItem,program));
            return (Value) lastItem;
        }

        return lastItem.varGetValue(stack,program);
    }

}

