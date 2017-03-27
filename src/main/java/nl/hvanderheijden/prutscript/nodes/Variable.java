package nl.hvanderheijden.prutscript.nodes;


import nl.hvanderheijden.prutscript.ProgramFactory;
import nl.hvanderheijden.prutscript.exceptions.PrutException;
import nl.hvanderheijden.prutscript.exceptions.ReferenceNotFoundException;
import nl.hvanderheijden.prutscript.exceptions.UnableToLoadException;
import nl.hvanderheijden.prutscript.utils.Assert;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Variable extends Value {

    private final Value value;

    private final String name;

    private Variable(){
        throw new UnsupportedOperationException();
    }

    public Variable(final String name){
        this.name = name;
        this.value = null;
    }

    public Variable(final String name,
                    final Value value){
        this.value = value;
        this.name = name;
    }

    public Variable(final Value value,
                    final String name){
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("%s : %s", name, value);
    }

    @Override
    public Value varGetValue(final List<Variable> stack, final ProgramFactory.Program program) throws PrutException {

        if(this.value == null){
            for(Variable n : stack){
                if(n.getName().equals(this.name)){
                    //this.value = n.varGetValue(stack, program);
                    return n.varGetValue(stack, program);
                }
            }

            try {
                return program.getVariable(this.getName());
            }
            catch(final PrutException ex){
                System.out.println(stack);
                throw ex;
            }
        }
        return value.varGetValue(stack, program);
    }

    @Override
    public void checkValidity(ProgramFactory.Program pr) throws PrutException {
        Assert.isUndefined(name == null);
    }

    public String getName() {
        return name;
    }


    @Override
    public String prutToString() {
        return value.prutToString();
    }

    @Override
    public String varGetName(final String alt) {
        return this.name;
    }


    @Override
    public String getType() {
        return "Var";
    }
}
