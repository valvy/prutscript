package nl.hvanderheijden.prutscript.nodes;


import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.PrutContext;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.ArrayList;
import java.util.List;

public class Method implements Node {

    private final List<PrutReference> instructions;

    private final List<String> arguments;

    private final String name;

    private final int lineNr;

    private Method(){
        throw new UnsupportedOperationException();
    }

    public Method(final String name,
                  final List<PrutReference> nodelist,
                  final List<String> arguments,
                  final int lineNr){

        this.lineNr = lineNr;
        if(arguments != null){
            this.arguments = arguments;
        }else{
            this.arguments = new ArrayList<>();
        }

        this.name = name;
        if(nodelist == null){
            this.instructions = new ArrayList<>();
        } else{
            this.instructions = nodelist;
        }
    }

    public Variable getVariable(final String name,
                                final List<Variable> stack) throws ReferenceNotFoundException {
        for(final PrutReference v : stack){
            if(v instanceof  Variable){
                Variable var = (Variable) v;
                if(var.getName().equals(name)){
                    return var;
                }
            }
        }
        throw new ReferenceNotFoundException(1,String.format("Variable %s is not defined", name));
    }


    protected void addVariable(final PrutContext context, final PrutReference node) throws PrutRedefinedException {
        if (node instanceof Variable) {
            context.addtoStack((Variable) node);
        }
    }

    private List<Variable> getArguments(final PrutContext context, final MethodCall call) throws PrutException {
        final List<Variable> res = new ArrayList<>();
        int i = 0;
        for(final PrutReference arg : call.getArguments()){
            i++;
            res.add(new Variable(arg.getName(Integer.toString(i)),
                    arg.getValue(context),
                    arg.getLineNr()
            ));

        }


        return res;
    }

    private PrutReference callMethod(final PrutContext context, final Node node) throws PrutException {

        if(node instanceof MethodCall){
            final MethodCall method = (MethodCall)node;

            final List<Variable> args = getArguments(context, method);//getArguments(stack,method,program);
            PrutReference res = method.getValue(context);
            while(true){

                if(res instanceof MethodCall){
                    res = res.getValue(context);
                } else{
                    return res;
                }
            }
        }
        return null;
    }

    public PrutReference executeMethod(final PrutContext context, final List<PrutReference> arguments) throws PrutException {
        Assert.typeCheck(arguments.size() != this.arguments.size());

        /**
         * Add the arguments to the stack
         */
        for(int i = 0; i < arguments.size(); i++) {

            Assert.isUndefined(arguments.get(i) == null);
            final PrutReference r = arguments.get(i).getValue(context);
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
        }

        return lastItem.getValue(context);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getLineNr() {
        return 0;
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
        Assert.isUndefined(name == null);

        for(final Node n : this.instructions){
            n.checkValidity(pr);
        }
    }
}
