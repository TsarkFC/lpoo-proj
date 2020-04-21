package Commands;

import Model.Arena;
import Model.GameParticipant;

public class DrawCardCommand implements Command{
    private final Arena arena;
    private final GameParticipant part;
    public DrawCardCommand(Arena arena, GameParticipant part){
        this.arena = arena;
        this.part = part;
    }

    @Override
    public void execute() {
        arena.drawCard(part);
    }
}
