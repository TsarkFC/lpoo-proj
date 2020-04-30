package creator;

import controller.ArenaController;
import model.Arena;
import model.GameParticipant;
import org.junit.Test;
import org.mockito.Mockito;
import view.Gui;

import static org.junit.Assert.assertEquals;

public class ArenaCreatorTest {

    @Test
    public void testCreation(){
        Gui gui = Mockito.mock(Gui.class);
        Arena arena = new Arena(10, 10);
        ArenaController controller = new ArenaController(gui, arena);
        ArenaCreator creator = new ArenaCreator();

        GameParticipant part = creator.createParticipant();

        creator.create(controller);

        assertEquals(part.getMana(), controller.getEnemy().getMana());
        assertEquals(part.getMana(), controller.getPlayer().getMana());
        assertEquals(part.getHealth(), controller.getEnemy().getHealth());
        assertEquals(part.getHealth(), controller.getPlayer().getHealth());
        assertEquals(part.getPlay_deck().size(), controller.getEnemy().getPlay_deck().size());
    }
}
