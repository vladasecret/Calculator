package Main;

import Commands.Command;
import Exceptions.InvalidCommandException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdFactory {
    private static final Logger logger = Logger.getLogger(CmdFactory.class.getName());
    private static volatile CmdFactory INSTANCE = null;
    private static Properties properties;

    private CmdFactory() throws IOException {
        properties = new Properties();
        InputStream file = CmdFactory.class.getResourceAsStream("/Cmd.properties");
        properties.load(file);
        logger.info("\"Cmp.properties\" is loaded.");
    }

    public static CmdFactory getInstance() throws IOException {
        if (INSTANCE == null){
            synchronized (CmdFactory.class){
                if (INSTANCE == null) {
                    INSTANCE = new CmdFactory();
                    logger.info("New CmdFactory was created");
                }
            }
        }
        return INSTANCE;
    }


    public Command getCommand(String cmdName) throws InvalidCommandException {
        cmdName = cmdName.toUpperCase();
        Class<?> resClass;
        Command resultCommand;
        String className = properties.getProperty(cmdName);
        if (className == null) {
            throw new InvalidCommandException("Invalid command request: " + cmdName);
        }
        try {
            resClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new InvalidCommandException("command " + cmdName + " use invalid class: " + className, e);
        }
        try {
            resultCommand = (Command) resClass.getDeclaredConstructor().newInstance();
            logger.log(Level.INFO, "For command [{0}] created new object.", cmdName);
        } catch (NoSuchMethodException e) {
            throw new InvalidCommandException("Can't find constructor for class " + resClass.getName(), e);
        } catch (Exception e) {
            throw new InvalidCommandException("Can't create new object " + resClass.getName(), e);
        }
        return resultCommand;
    }
}
