package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PopTest {

    @Test
    void correctPop(){
        LinkedList<String> args = new LinkedList<>();
        Command pop = new Pop();
        Context context = new Context();

        double value = 230;
        context.pushNum(value);
        try{
            pop.execute(context, args);
            assertThrows(StackEmptyException.class, ()->pop.execute(context, args));
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command pop = new Pop();
        Context context = new Context();
        String value = "param for pop";
        args.add(value);
        assertThrows(IncorrectNumOfArgsException.class, ()->pop.execute(context, args));
    }

    @Test
    void StackEmpty(){
        LinkedList<String> args = new LinkedList<>();
        Command pop = new Pop();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->pop.execute(context, args));

    }
}