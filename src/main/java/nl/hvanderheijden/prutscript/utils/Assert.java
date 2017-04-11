package nl.hvanderheijden.prutscript.utils;


import nl.hvanderheijden.prutscript.exceptions.PrutTypeException;
import nl.hvanderheijden.prutscript.exceptions.UndefinedBehaviourException;

public class Assert {

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
}
