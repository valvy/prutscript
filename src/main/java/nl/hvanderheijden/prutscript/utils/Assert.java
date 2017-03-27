package nl.hvanderheijden.prutscript.utils;


import nl.hvanderheijden.prutscript.exceptions.PrutTypeException;
import nl.hvanderheijden.prutscript.exceptions.UndefinedBehaviourException;

public class Assert {

    public static void isUndefined(final boolean ass) throws UndefinedBehaviourException {
        if(ass){
            throw new UndefinedBehaviourException(null);
        }
    }



    public static void typeCheck(final boolean ass) throws PrutTypeException {
        if(ass){
            throw new PrutTypeException(null, "Type check failed!");
        }
    }
}
