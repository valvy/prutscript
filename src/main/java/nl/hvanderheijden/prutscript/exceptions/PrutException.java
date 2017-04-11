package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

public abstract class PrutException extends Exception {


    private final String msg;

    private final int lineNr;

    private PrutException(){
        throw new UnsupportedOperationException();
    }

    public PrutException(final int lineNr, final String msg) {
        this.msg = msg;
        this.lineNr = lineNr;
    }

    public PrutException(final String msg, final int lineNr) {
        this.msg = msg;
        this.lineNr = lineNr;
    }

    @Override
    public String getMessage() {
        return String.format("Error on line : %d, %s ", lineNr,msg);

    }

    @Override
    public String toString() {
        return this.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    //@Override
  //  public StackTraceElement[] getStackTrace() {
      //  StackTraceElement[] a = {};
        //return a;
    //}

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
    }

}