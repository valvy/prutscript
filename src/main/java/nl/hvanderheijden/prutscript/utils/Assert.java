package nl.hvanderheijden.prutscript.utils;


import nl.hvanderheijden.prutscript.core.exceptions.PrutRedefinedException;
import nl.hvanderheijden.prutscript.core.exceptions.PrutTypeException;
import nl.hvanderheijden.prutscript.core.exceptions.UndefinedBehaviourException;
import nl.hvanderheijden.prutscript.core.nodes.Node;

public final class Assert {

    private Assert(){
        throw new UnsupportedOperationException();
    }

    public static void isUndefined(final boolean ass, final Node node) throws UndefinedBehaviourException {
        if(ass){
            throw new UndefinedBehaviourException(node.getLineNr(), "Undefined exeption");
        }
    }

    public static void isUndefined(final boolean ass, final String msg, final Node node) throws UndefinedBehaviourException {
        if(ass){
            throw new UndefinedBehaviourException(node.getLineNr(),msg);
        }
    }

    public static void isAlreadydefined(final boolean ass, final String msg,final Node node) throws PrutRedefinedException {
        if (ass) {
            throw new PrutRedefinedException(node.getLineNr(), msg);
        }
    }

    public static void typeCheck(final boolean ass, final Node node) throws PrutTypeException {
        if(ass){
            throw new PrutTypeException(node.getLineNr(), "Type check failed!");
        }
    }

    public static void typeCheck(final boolean asses, final String msg, final int lineNr) throws PrutTypeException {
        if(asses){
            throw new PrutTypeException(lineNr, msg);
        }
    }
}
