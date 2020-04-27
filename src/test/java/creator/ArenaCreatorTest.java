package creator;

import controller.ArenaController;
import model.Arena;
import model.GameParticipant;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ArenaCreatorTest {

    @Test
    public void testCreate(){
        ArenaController controller = Mockito.mock(ArenaController.class);
        ParticipantCreator part_creator = Mockito.mock(ParticipantCreator.class);
        ArenaCreator creator = new ArenaCreator();

        GameParticipant part = new GameParticipant(new ArrayList<>(),0,0,0,0,0);

        Mockito.when(part_creator.createParticipant()).thenReturn(part);
        creator.create(controller, part_creator);

        Mockito.verify(controller, Mockito.times(1)).setPlayerController(part);
        Mockito.verify(controller, Mockito.times(1)).setEnemyController(part);
    }
}
