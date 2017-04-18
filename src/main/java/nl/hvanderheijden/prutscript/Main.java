package nl.hvanderheijden.prutscript;

import nl.hvanderheijden.prutscript.core.exceptions.PrutException;
import nl.hvanderheijden.prutscript.utils.Loader;

public final class Main {

    private Main(){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] arguments){

        if(arguments.length == 0){
            System.out.println("Please define a source file..");
            return;
        }

        SpecialArguments.executeSpecialArgument(arguments[0]);

        try {
            final Loader loader = new Loader(arguments[0]);
            loader.execute();
        } catch (PrutException e) {
            System.out.println(String.format("\u001B[31m %s \u001B[0m", e.getMessage()));
        }

    }

}
