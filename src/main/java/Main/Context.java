package Main;

import Exceptions.DefineException;
import Exceptions.StackEmptyException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Context {
    private LinkedList<Double> stack;
    private Map<String, Double> constants;

    public Context() {
        stack = new LinkedList<>();
        constants = new HashMap<>();
    }

    public int stackSize(){
        return stack.size();
    }

    public void pushNum(double num) {
        stack.push(num);
    }

    public double getNum() throws StackEmptyException {
        if (stack.size() == 0)
            throw new StackEmptyException();
        return stack.getFirst();
    }

    public double popNum() throws StackEmptyException {
        if (stack.size() == 0)
            throw new StackEmptyException();
        return stack.pop();
    }

    public void addConst(String constant, double value) throws DefineException {
        if (constants.containsKey(constant))
            throw new DefineException(String.format("Constant %s already exists", constant));
        else
            constants.put(constant, value);
    }
    public boolean containsConst (String constant){
        return constants.containsKey(constant);
    }

    public double constValue (String constant){
        return constants.get(constant);
    }

    public void clear(){
        stack.clear();
        constants.clear();
    }

}
