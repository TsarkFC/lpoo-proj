package Commands;

import Model.Arena;

public class DrawCardCommand implements Command{
    private final Arena arena;

    public DrawCardCommand(Arena arena){
        this.arena = arena;
    }

    @Override
    public void execute() {
        arena.drawCard();
    }
}
