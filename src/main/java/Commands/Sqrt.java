package Commands;

import Exceptions.*;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sqrt implements Command {
    private static final Logger LOGGER = Logger.getLogger(Sqrt.class.getName());

    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 0) {
            throw new IncorrectNumOfArgsException("You can't use " + args.toString()
                    + ". SQRT uses parameter from stack.");
        }

        if (context.stackSize() == 0)
            throw new StackEmptyException("Can't execute SQRT.");

        double value = context.popNum();

        if (value < 0)
            throw new NegativeNumException(String.format("Given numeral from stack is [%f], " +
                    "SQRT don't work with negative numerals", value));

        double res = Math.sqrt(value);
        context.pushNum(res);
        LOGGER.log(Level.INFO, "SQRT({0}) = {1} (successfully)", new Object[]{value, res});
    }
}
