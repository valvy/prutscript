package nl.hvanderheijden.prutscript.utils;


import nl.hvanderheijden.prutscript.exceptions.PrutTypeException;
import nl.hvanderheijden.prutscript.exceptions.UndefinedBehaviourException;
import nl.hvanderheijden.prutscript.nodes.Node;

public final class Assert {

    private Assert(){
        throw new UnsupportedOperationException();
    }

    public static void isUndefined(final boolean ass) throws UndefinedBehaviourException {
        if(ass){
            throw new UndefinedBehaviourException(1, "Undefined exeption");
        }
    }

    public static void isUndefined(final boolean ass, final String msg) throws UndefinedBehaviourException {
        if(ass){
            throw new UndefinedBehaviourException(1,msg);
        }
    }


    public static void typeCheck(final boolean ass) throws PrutTypeException {
        if(ass){
            throw new PrutTypeException(1, "Type check failed!");
        }
    }

    public static void typeCheck(final boolean asses, final String msg, final int lineNr) throws PrutTypeException {
        if(asses){
            throw new PrutTypeException(lineNr, msg);
        }
    }
}
