package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class main {
    private static final Logger LOGGER = Logger.getLogger(main.class.getName());

    public static void main(String[] args) {
        loadLogFile();

        InputStream input = null;
        if (args.length == 1) {
            try {
                input = new FileInputStream(args[0]);
                LOGGER.log(Level.INFO,"Read from {0}", args[0]);
            } catch (FileNotFoundException exs) {
                LOGGER.severe(exs.getLocalizedMessage());
                System.exit(0);
            }
        } else {
            input = System.in;
            LOGGER.info("Read from console");
        }
        Calculator calculator = new Calculator();
        LOGGER.info("Calculator was created");
        calculator.calculate(input);
    }

    private static void loadLogFile() {
        try {
            LogManager.getLogManager().readConfiguration(main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(-1);
        }
        LOGGER.info("Logging config file is loaded.");
    }
}
