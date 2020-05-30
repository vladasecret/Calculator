package Main;

import Commands.Command;
import Exceptions.InvalidCommandException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdFactory {
    private static final Logger logger = Logger.getLogger(CmdFactory.class.getName());
    private static Properties properties;
    private static Map<String, Class<?>> classes;

    static {
        properties = new Properties();
        classes = new TreeMap<>();
        try (InputStream file = CmdFactory.class.getResourceAsStream("/Cmd.properties")) {
        //try (FileInputStream file = new FileInputStream("/Cmd.properties")) {
            properties.load(file);
            logger.info("\"Cmp.properties\" is loaded");
        }  catch (IOException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    public static Command getCommand(String cmdName) throws InvalidCommandException {
        cmdName = cmdName.toUpperCase();
        Class<?> resClass;
        Command resultCommand;
        if (classes.containsKey(cmdName)) {
            resClass = classes.get(cmdName);
        } else {
            String className = properties.getProperty(cmdName);
            if (className == null) {
                throw new InvalidCommandException("Invalid command request: " + cmdName);
            }
            try {
                resClass = Class.forName(className);
            }
            catch (ClassNotFoundException e){
                throw new InvalidCommandException("command " + cmdName + " use invalid class: " + className, e);
            }
            classes.put(cmdName, resClass);
        }
        try {
            resultCommand = (Command) resClass.getDeclaredConstructor().newInstance();
            logger.log(Level.INFO, "For command [{0}] created new object.", cmdName);
        }
        catch (NoSuchMethodException e){
                throw new InvalidCommandException("Can't find constructor for class " + resClass.getName(), e);
        }
        catch (Exception e){
            throw new InvalidCommandException("Can't create new object " + resClass.getName(), e);
        }
        return resultCommand;
    }

}
