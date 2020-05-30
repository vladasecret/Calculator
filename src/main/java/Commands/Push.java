package Commands;

import Exceptions.BadParamException;
import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Push implements Command {
    private static final Logger LOGGER = Logger.getLogger(Push.class.getName());

    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 1){
            throw new IncorrectNumOfArgsException("Push has one parameter: number or defined constant.");
        }
        double value;
        try {
          value = Double.parseDouble(args.get(0));
        }
        catch (NumberFormatException exc){
            if (context.containsConst(args.get(0))){
                value = context.constValue(args.get(0));
            }
            else {
                throw new BadParamException(args + " is not numeral or defined constant.");
            }
        }
        context.pushNum(value);
        LOGGER.log(Level.INFO, "[{0}] is pushed.", args.get(0));
    }
}
