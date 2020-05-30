package Main;

import Commands.Command;
import Exceptions.CommandException;
import Exceptions.InvalidCommandException;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class main {
    private static final Logger LOGGER = Logger.getLogger(main.class.getName());
    private static Context context = new Context();

    public static void main(String[] args)  {
        loadLogFile();

        try (Scanner scanner = getScanner(args)) {

            while (scanner.hasNextLine()) {
                String commandStr = scanner.nextLine();
                if (isCommand(commandStr)) {
                    execute(commandStr);
                }
            }
        } catch (CommandException exc){
            LOGGER.severe(exc.toString());
        }
    }

    private static void loadLogFile(){
        try {
            LogManager.getLogManager().readConfiguration(main.class.getResourceAsStream("/logging.properties"));
        }
        catch (IOException e){
            System.err.println(e.getLocalizedMessage());
            System.exit(-1);
        }
        LOGGER.info("Logging config file is loaded.");
    }

    private static Scanner getScanner(String[] args){
        Scanner scanner = null;
        if (args.length > 0){
            try{
                scanner = new Scanner(new FileInputStream(args[0]));
                LOGGER.log(Level.INFO, "Read from file {0}.",args[0]);
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Scanner creating error: ", e);
                System.exit(-1);
            }
        }
        else {
            scanner = new Scanner(System.in);
            LOGGER.log(Level.INFO, "Scanner reads from console");
        }
        return scanner;
    }

    private static boolean isCommand(@NotNull String cmdStr){
        String cmd = cmdStr.trim();
        return !(cmd.startsWith("#") || cmd.equals(""));
    }


    private static Command getCommand(String[] cmdList) throws InvalidCommandException {
        return  CmdFactory.getCommand(cmdList[0]);
    }

    private static LinkedList<String> getArgs(String[] cmdList) throws InvalidCommandException {
        LinkedList<String> args = new LinkedList<>();
        for (int i = 1; i < cmdList.length; ++i){
            args.add(cmdList[i]);
        }
        return args;
    }


    private static void execute(String cmdStr) throws CommandException {
        String[] cmdList = cmdStr.split("\\s+");
        Command command = getCommand(cmdList);
        List<String> args = getArgs(cmdList);
        command.execute(context, args);

    }


}
