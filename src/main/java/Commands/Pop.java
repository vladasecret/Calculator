package Commands;

import Exceptions.BadParamException;
import Exceptions.CommandException;

import Exceptions.IncorrectNumOfArgsException;
import Exceptions.StackEmptyException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pop implements Command {
    private static final Logger LOGGER = Logger.getLogger(Pop.class.getName());


    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 0){
            throw new IncorrectNumOfArgsException("POP has no parameters.");
        }
        try {
            double value = context.popNum();
            LOGGER.log(Level.INFO, "[{0}] is pushed.", value);
        } catch (StackEmptyException exs) {
            throw new StackEmptyException("Can't execute POP");
        }
    }

}
