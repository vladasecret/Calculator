package Commands;
import Exceptions.CommandException;
import Main.Context;
import java.util.List;

public interface Command {
    public void execute (Context context, List<String> args) throws CommandException;
}
