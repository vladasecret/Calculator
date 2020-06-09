package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Multiplication implements Command {
    private static final Logger LOGGER = Logger.getLogger(Multiplication.class.getName());

    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 0){
            throw new IncorrectNumOfArgsException("You can't use " + args.toString()
                    + ". Multiplication uses parameters from stack.");
        }
        if (context.stackSize() < 2)
            throw new StackEmptyException("Can't execute multiplication.");

        double a = context.popNum();
        double b = context.popNum();

        context.pushNum(a * b);
        LOGGER.log(Level.INFO, "Multiplication: {0} * {1} = {2} (successfully)", new Object[]{a, b, a * b});
    }
}
