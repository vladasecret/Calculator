package Commands;
import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Addition implements Command {
    private static final Logger LOGGER = Logger.getLogger(Addition.class.getName());

    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 0){
            throw new IncorrectNumOfArgsException("You can't use " + args.toString()
                    + ". Addition uses parameters from stack.");
        }
        double a;
        double b;
        try {
            a = context.popNum();
            b = context.popNum();
        }
        catch (StackEmptyException e){
            throw new StackEmptyException("Can't execute addition.");
        }
        context.pushNum(a + b);
        LOGGER.log(Level.INFO, "{0} + {1} = {2} (successfully)", new Object[]{a, b, a + b});
    }
}
