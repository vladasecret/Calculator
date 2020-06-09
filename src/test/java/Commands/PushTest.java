package Commands;

import Exceptions.BadParamException;
import Exceptions.CommandException;
import Exceptions.DefineException;
import Exceptions.IncorrectNumOfArgsException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {
    @Test
    void correctPush(){
        LinkedList<String> args = new LinkedList<>();
        Command push = new Push();
        Context context = new Context();

        String value = "-1234.5";
        args.add(value);
        try{
            push.execute(context, args);
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    void definedCorrectPush() {
        LinkedList<String> args = new LinkedList<>();
        Command push = new Push();
        Context context = new Context();

        String constant = "CONST";
        double value = Math.random();
        try {
            context.addConst(constant, value);
        } catch (DefineException e){
            fail(e);
        }
        args.add(constant);
        try{
            push.execute(context, args);
            assertEquals(value, context.getNum());
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    void BadParam(){
        LinkedList<String> args = new LinkedList<>();
        Command push = new Push();
        Context context = new Context();
        String PI = "PI";
        args.add(PI);
        assertThrows(BadParamException.class, ()->push.execute(context, args));
        args.clear();
        String notNum = "-134.fdlvk";
        args.add(notNum);
        assertThrows(BadParamException.class, ()->push.execute(context, args));
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command push = new Push();
        Context context = new Context();

        assertThrows(IncorrectNumOfArgsException.class, ()->push.execute(context, args));
        String value = "1234";
        args.add(value);
        args.add(value);
        assertThrows(IncorrectNumOfArgsException.class, ()->push.execute(context, args));
    }

}