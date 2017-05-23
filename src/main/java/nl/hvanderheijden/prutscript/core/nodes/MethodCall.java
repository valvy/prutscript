package nl.hvanderheijden.prutscript.core.nodes;

import nl.hvanderheijden.prutscript.core.ProgramFactory;
import nl.hvanderheijden.prutscript.core.PrutContext;
import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.ArrayList;
import java.util.List;

public final class MethodCall extends PrutReference {

    private final String name;

    private final List<PrutReference> arguments;

    private PrutContext tmpContext;

    public MethodCall(final String name,
                      final List<PrutReference> arguments,
                      final int lineNr){
        super(lineNr);
        this.name = name;
        this.arguments = arguments;
    }

    public void preFillContext(final PrutContext context){
        this.tmpContext = context;
    }

    @Override
    public String toString(){
        return String.format("method %s : %s", name, arguments.toString());
    }

    public String getName() {
        return name;
    }

    public List<PrutReference> getArguments() {
        return arguments;
    }

    @Override
    public String prutToString() {
        return String.format("[Method] %s", name);
    }


    @Override
    public void checkValidity(final ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(name == null,this);
        pr.getMethod(this);

    }

    @Override
    public String getName(String alt) {
        return alt;
    }

    @Override
    public PrutReference execute(final PrutContext context) throws PrutException {
        final List<PrutReference> args = new ArrayList<>();
        if(tmpContext == null) {
            final PrutContext cont = new PrutContext(context);
            for (final PrutReference v : arguments) {
                args.add(v.execute(cont).execute(cont));
            }
            return context.getMethod(this).executeMethod(cont,args);
        }
        else{

            final PrutContext cont = new PrutContext(tmpContext);
            for (final PrutReference v : arguments) {
                args.add(v.execute(cont));
            }
            this.tmpContext = null;
            return cont.getMethod(this).executeMethod(cont,args);
        }
    }
}
