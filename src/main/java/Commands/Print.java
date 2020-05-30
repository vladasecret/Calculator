package Commands;

import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Print implements Command {
    private static final Logger LOGGER = Logger.getLogger(Print.class.getName());


    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 0){
            throw new IncorrectNumOfArgsException("PRINT has no parameters.");
        }
        try {
            double value = context.getNum();
            System.out.println(value);
            LOGGER.log(Level.INFO, "Value [{0}] was printed.", value);
        }
        catch (StackEmptyException exc){
            throw new StackEmptyException("Can't execute PRINT");
        }
    }
}
