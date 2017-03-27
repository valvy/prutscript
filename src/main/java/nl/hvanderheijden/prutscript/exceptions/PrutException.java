package nl.hvanderheijden.prutscript.exceptions;


import nl.hvanderheijden.prutscript.nodes.Variable;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

public abstract class PrutException extends Exception {

    final List<Variable> stackTrace;

    public PrutException(final List<Variable> stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        return super.toString();
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