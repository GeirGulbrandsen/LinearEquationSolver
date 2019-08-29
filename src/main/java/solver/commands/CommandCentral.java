package solver.commands;

public class CommandCentral {
    private Command slot;

    public void setSlot(Command slot) {
        this.slot = slot;
    }

    public void processComms() {
        slot.execute();
    }
}
