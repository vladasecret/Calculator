package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    void correctAddition(){
        LinkedList<String> args = new LinkedList<>();
        Command addition = new Addition();
        Context context = new Context();
        double first = Math.random();
        double second = Math.random();
        context.pushNum(first);
        context.pushNum(second);
        try {
            addition.execute(context, args);
            assertEquals(first + second, context.getNum());
        }
        catch (CommandException exs){
            fail(exs.getLocalizedMessage());
        }
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command addition = new Addition();
        Context context = new Context();
        double first = 528.1;
        double second = 5473;
        args.add(Double.toString(first));
        args.add(Double.toString(second));
        assertThrows(IncorrectNumOfArgsException.class, ()->addition.execute(context, args));
    }

    @Test
    void StackEmpty(){
        LinkedList<String> args = new LinkedList<>();
        Command addition = new Addition();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->addition.execute(context, args));
        double value = -1583.1;
        context.pushNum(value);
        assertThrows(StackEmptyException.class, ()->addition.execute(context, args));
    }

}