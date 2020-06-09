package Commands;

import Exceptions.BadParamException;
import Exceptions.CommandException;
import Exceptions.IncorrectNumOfArgsException;
import Main.Context;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Define implements Command {
    private static final Logger LOGGER = Logger.getLogger(Define.class.getName());

    public void execute(Context context, List<String> args) throws CommandException {
        if (args.size() != 2) {
            throw new IncorrectNumOfArgsException("DEFINE parameters: first is constant, second is numeral");
        }
        if (!isDigit(args.get(0)) && isDigit(args.get(1))) {
            double value = Double.parseDouble(args.get(1));
            context.addConst(args.get(0), value);
            LOGGER.log(Level.INFO, "DEFINE {0} {1} (successfully)", new Object[]{args.get(0), value});
        }
        else throw new BadParamException("DEFINE: first parameter - constant, second - numeral");
    }

    private boolean isDigit(String data) {
        try {
            Double.parseDouble(data);
            return true;
        } catch (NumberFormatException exc) {
            return false;
        }

    }
}
