package Commands;

import Exceptions.BadParamException;
import Exceptions.CommandException;
import Exceptions.DefineException;
import Exceptions.IncorrectNumOfArgsException;
import Main.Context;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DefineTest {
    @Test
    void correctDefine(){
        LinkedList<String> args = new LinkedList<>();
        Command define = new Define();
        Context context = new Context();

        String constant = "CONST";
        double value = Math.random();
        args.add(constant);
        args.add(Double.toString(value));
        try {
            define.execute(context, args);
            assertEquals(value, context.constValue(constant));
        }
        catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    void IncorrectNumArgs(){
        LinkedList<String> args = new LinkedList<>();
        Command define = new Define();
        Context context = new Context();

        assertThrows(IncorrectNumOfArgsException.class, ()-> define.execute(context,args));
        String constant = "CONST";
        String value = Double.toString(Math.random());

        args.add(constant);
        assertThrows(IncorrectNumOfArgsException.class, ()-> define.execute(context,args));

        args.add(value);
        args.add(value);
        assertThrows(IncorrectNumOfArgsException.class, ()-> define.execute(context,args));
    }

    @Test
    void ConstantAlreadyExists(){
        LinkedList<String> args = new LinkedList<>();
        Command define = new Define();
        Context context = new Context();

        String constant = "ZERO";
        String value = "0";
        args.add(constant);
        args.add(value);
        try {
            define.execute(context, args);
        } catch (CommandException e) {
            fail(e);
        }
        args.set(1, "1");
        assertThrows(DefineException.class, ()->define.execute(context, args));
    }

    @Test
    void BadParam(){
        LinkedList<String> args = new LinkedList<>();
        Command define = new Define();
        Context context = new Context();

        args.add("0");
        args.add("constant");
        assertThrows(BadParamException.class, ()->define.execute(context, args));

        args.set(1, "1");
        assertThrows(BadParamException.class, ()->define.execute(context, args));

        args.set(0, "constant");
        args.set(1, "one");
        assertThrows(BadParamException.class, ()->define.execute(context, args));
    }

}