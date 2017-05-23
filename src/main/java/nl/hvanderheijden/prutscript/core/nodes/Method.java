package nl.hvanderheijden.prutscript.core.nodes;


import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.PrutOutput;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage of a method.
 * @author Heiko van der Heijden.
 */
public class Method extends PrutOutput implements Node {

    private final List<PrutReference> instructions;

    private final List<String> arguments;

    private final String name;

    private final int lineNr;

    private Method(){
        throw new UnsupportedOperationException();
    }

    /**
     * Initializer of the method.
     * @param name method name.
     * @param nodelist The instructions of the method
     * @param arguments the argument name in order.
     * @param lineNr the line number of the method.
     */
    public Method(final String name,
                  final List<PrutReference> nodelist,
                  final List<String> arguments,
                  final int lineNr) {

        this.lineNr = lineNr;
        this.arguments = arguments;

        this.name = name;
        if(nodelist == null){
            this.instructions = new ArrayList<>();
        } else{
            this.instructions = nodelist;
        }
    }


    private void addVariable(final PrutContext context, final PrutReference node) throws PrutException {
        if (node instanceof Variable) {
            context.addtoStack(
                    new Variable((Variable) node,context));
        }
    }

    private List<Variable> getArguments(final PrutContext context, final MethodCall call) throws PrutException {
        final List<Variable> res = new ArrayList<>();
        int i = 0;
        for(final PrutReference arg : call.getArguments()){
            i++;
            res.add(new Variable(arg.getName(Integer.toString(i)),
                    arg.execute(context),
                    arg.getLineNr()
            ));

        }


        return res;
    }

    private PrutReference callMethod(final PrutContext context, final Node node) throws PrutException {

        if(node instanceof MethodCall){
            final MethodCall method = (MethodCall)node;

         //   final List<Variable> args = getArguments(context, method);//getArguments(stack,method,program);
            PrutReference res = method.execute(context);
            while(true){

                if(res instanceof MethodCall){
                    res = res.execute(context);
                } else{
                    return res;
                }
            }
        }
        return null;
    }

    /**
     * Executes the method.
     * @param context the context of the method.
     * @param arguments the arguments required.
     * @return The result.
     * @throws PrutException Error while executing.
     */
    public PrutReference executeMethod(final PrutContext context, final List<PrutReference> arguments) throws PrutException {

        Assert.typeCheck(arguments.size() != this.arguments.size(),this);

        /**
         * Add the arguments to the stack
         */
        for(int i = 0; i < arguments.size(); i++) {
            Assert.isUndefined(arguments.get(i) == null,this);
            final PrutReference r = arguments.get(i).execute(context);
            context.addtoStack(new Variable(
                    r,
                    this.arguments.get(i),
                    this.getLineNr()

            ));
        }

        for(int i = 0; i < instructions.size() - 1; i++){

            addVariable(context,instructions.get(i));
            callMethod(context, instructions.get(i));
        }

        //Do a tailcall
        PrutReference lastItem = instructions.get(instructions.size() -1 );

        if(lastItem instanceof MethodCall){
            final MethodCall call = (MethodCall) lastItem;
            call.preFillContext(context);
            return call;
        } else if(lastItem instanceof Variable){
            return lastItem;
        }

        return lastItem.execute(context);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getLineNr() {
        return this.lineNr;
    }

    @Override
    public String prutToString() {
        return String.format("[Method] %s", name) ;
    }


    @Override
    public String toString(){
        return this.prutToString();
    }

    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(name == null,this);

        for(final Node n : this.instructions){
            n.checkValidity(pr);
        }
    }
}
