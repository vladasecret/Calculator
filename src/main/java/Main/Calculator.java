package Main;

import Commands.Command;
import Exceptions.CommandException;
import Exceptions.InvalidCommandException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Calculator {
    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getName());
    private static Context context;

    public Calculator() {
        context = new Context();
    }

    public void calculate(InputStream input) {
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String commandStr = scanner.nextLine();
                if (isCommand(commandStr)) {
                    execute(commandStr);
                }
            }
        } catch (Exception exc) {
            LOGGER.severe(exc.toString());
        }
    }

    private static void execute(String cmdStr) throws CommandException, IOException {
        String[] cmdList = cmdStr.split("\\s+");
        Command command = getCommand(cmdList);
        List<String> args = getArgs(cmdList);
        command.execute(context, args);
    }

    private boolean isCommand(String cmdStr) {
        String cmd = cmdStr.trim();
        return !(cmd.startsWith("#") || cmd.equals(""));
    }

    private static Command getCommand(String[] cmdList) throws InvalidCommandException, IOException {
        return CmdFactory.getInstance().getCommand(cmdList[0]);
    }

    private static LinkedList<String> getArgs(String[] cmdList) {
        LinkedList<String> args = new LinkedList<>();
        for (int i = 1; i < cmdList.length; ++i) {
            args.add(cmdList[i]);
        }
        return args;
    }

}
