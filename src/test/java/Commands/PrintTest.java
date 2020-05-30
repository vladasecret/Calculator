package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {
    @Test
    void correctPrint() {
        LinkedList<String> args = new LinkedList<>();
        Command print = new Print();
        Context context = new Context();
        double value = 5.75;

        context.pushNum(value);
        try {
            print.execute(context, args);
            assertEquals(value, context.getNum());
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    void IncorrectNumArgs() {
        LinkedList<String> args = new LinkedList<>();
        Command print = new Print();
        Context context = new Context();
        String value = "Print it";
        args.add(value);
        assertThrows(IncorrectNumOfArgsException.class, ()->print.execute(context, args));
    }

    @Test
    void StackEmpty() {
        LinkedList<String> args = new LinkedList<>();
        Command print = new Print();
        Context context = new Context();
        assertThrows(StackEmptyException.class, ()->print.execute(context, args));
    }
}