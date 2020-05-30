package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest {

    @Test
    void correctSubtraction(){
        LinkedList<String> args = new LinkedList<>();
        Command subtraction = new Subtraction();
        Context context = new Context();
        double first = Math.random();
        double second = Math.random();
        context.pushNum(first);
        context.pushNum(second);
        try {
            subtraction.execute(context, args);
            assertEquals(first - second, context.getNum());
        }
        catch (CommandException exs){
            fail(exs.getLocalizedMessage());
        }
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command  subtraction = new Subtraction();
        Context context = new Context();
        double first = 345.1;
        double second = 2352;
        args.add(Double.toString(first));
        args.add(Double.toString(second));
        assertThrows(IncorrectNumOfArgsException.class, ()->subtraction.execute(context, args));
    }

    @Test
    void StackEmpty(){
        LinkedList<String> args = new LinkedList<>();
        Command subtraction = new Subtraction();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->subtraction.execute(context, args));
        double value = 348.1;
        context.pushNum(value);
        assertThrows(StackEmptyException.class, ()->subtraction.execute(context, args));
    }

}