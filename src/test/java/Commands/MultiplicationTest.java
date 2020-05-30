package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest {
    @Test
    void correctMultiplication(){
        LinkedList<String> args = new LinkedList<>();
        Command multiplication = new Multiplication();
        Context context = new Context();
        double first = Math.random();
        double second = Math.random();
        context.pushNum(first);
        context.pushNum(second);
        try {
            multiplication.execute(context, args);
            assertEquals(first * second, context.getNum());
        }
        catch (CommandException exs){
            fail(exs.getLocalizedMessage());
        }
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command multiplication = new Multiplication();
        Context context = new Context();
        double first = 5923;
        double second = 4654;
        args.add(Double.toString(first));
        args.add(Double.toString(second));
        assertThrows(IncorrectNumOfArgsException.class, ()->multiplication.execute(context, args));
    }

    @Test
    void StackEmpty(){
        LinkedList<String> args = new LinkedList<>();
        Command multiplication = new Multiplication();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->multiplication.execute(context, args));
        double value = -58.7654;
        context.pushNum(value);
        assertThrows(StackEmptyException.class, ()->multiplication.execute(context, args));
    }

}