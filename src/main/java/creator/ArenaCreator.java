package creator;

import controller.ArenaController;

public class ArenaCreator {
    public void create(ArenaController controller, ParticipantCreator part_creator){
        controller.setPlayerController(part_creator.createParticipant());
        controller.setEnemyController(part_creator.createParticipant());
    }
}
