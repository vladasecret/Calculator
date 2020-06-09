package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Division implements Command{
    private static final Logger LOGGER = Logger.getLogger(Division.class.getName());

    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 0){
            throw new IncorrectNumOfArgsException("You can't use " + args.toString()
                    + ". Division uses parameters from stack.");
        }

        if (context.stackSize() < 2)
            throw new StackEmptyException("Can't execute division.");

        double a = context.popNum();
        double b = context.popNum();
        context.pushNum(b / a);
        LOGGER.log(Level.INFO, "Division: {0} / {1} = {2} (successfully)", new Object[]{b, a, b / a});
    }
}
