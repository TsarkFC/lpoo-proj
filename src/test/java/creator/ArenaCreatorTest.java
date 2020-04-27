package creator;

import controller.ArenaController;
import model.Arena;
import model.GameParticipant;
import org.junit.Test;
import org.mockito.Mockito;

public class ArenaCreatorTest {

    @Test
    public void testCreate(){
        ArenaController controller = Mockito.mock(ArenaController.class);
        ArenaCreator creator = new ArenaCreator();

        GameParticipant part = creator.createParticipant();

        creator.create(controller);

        Mockito.verify(controller, Mockito.times(0)).setPlayerController(part); //Ask teacher
        Mockito.verify(controller, Mockito.times(0)).setEnemyController(part);  //Ask teacher
    }
}
