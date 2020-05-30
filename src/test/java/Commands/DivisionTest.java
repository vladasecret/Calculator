package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {
    @Test
    void correctDivision(){
        LinkedList<String> args = new LinkedList<>();
        Command division = new Division();
        Context context = new Context();
        double first = Math.random();
        double second = Math.random();
        context.pushNum(first);
        context.pushNum(second);
        try {
            division.execute(context, args);
            assertEquals(first / second, context.getNum());
        }
        catch (CommandException exs){
            fail(exs.getLocalizedMessage());
        }
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command division = new Division();
        Context context = new Context();
        double first = 354;
        double second = 750;
        args.add(Double.toString(first));
        args.add(Double.toString(second));
        assertThrows(IncorrectNumOfArgsException.class, ()->division.execute(context, args));
    }

    @Test
    void StackEmpty(){
        LinkedList<String> args = new LinkedList<>();
        Command division = new Division();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->division.execute(context, args));
        double value = 678545;
        context.pushNum(value);
        assertThrows(StackEmptyException.class, ()->division.execute(context, args));
    }

}