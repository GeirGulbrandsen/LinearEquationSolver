package solver.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandCentral {
    private List<Command> commands;

    public CommandCentral() {
        this.commands = new ArrayList<>();
    }

    public void processCmds() {
        while (!commands.isEmpty()) {
            commands.get(0).execute();
            commands.remove(0);
        }
    }

    public void addCmd(Command command) {
        commands.add(command);
    }
}
