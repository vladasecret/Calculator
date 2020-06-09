package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.NegativeNumException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {
    @Test
    void correctSqrt(){
        LinkedList<String> args = new LinkedList<>();
        Command sqrt = new Sqrt();
        Context context = new Context();

        double value = Math.random();
        context.pushNum(value);
        try {
            sqrt.execute(context, args);
            assertEquals(Math.sqrt(value), context.getNum());
        }
        catch (CommandException exc){
            fail(exc);
        }
    }

    @Test
    void negativeNum(){
        LinkedList<String> args = new LinkedList<>();
        Command sqrt = new Sqrt();
        Context context = new Context();

        double value = -142.6;
        context.pushNum(value);
        assertThrows(NegativeNumException.class, ()->sqrt.execute(context, args));
    }

    @Test
    void StackEmpty(){
        LinkedList<String> args = new LinkedList<>();
        Command sqrt = new Sqrt();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->sqrt.execute(context, args));
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command sqrt = new Sqrt();
        Context context = new Context();
        String value = "param fpr sqrt";
        args.add(value);
        assertThrows(IncorrectNumOfArgsException.class, ()->sqrt.execute(context, args));
    }



}