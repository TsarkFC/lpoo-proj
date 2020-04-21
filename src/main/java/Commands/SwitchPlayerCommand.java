package Commands;

import Model.Arena;

public class SwitchPlayerCommand implements Command{
    private final Arena arena;

    public SwitchPlayerCommand(Arena arena){
        this.arena = arena;
    }

    @Override
    public void execute() {
        arena.switchPlayer();
    }
}
