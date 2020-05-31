package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.commands.SelectCard;
import com.g13.controller.arena.creator.ArenaCreator;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.Arena;
import com.g13.view.arena.ArenaViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectCardTest {
    @Property
    public void selectCardTest(@ForAll @IntRange(min = 0, max = 7) int selection){
        Arena arena = new Arena(10, 10);
        ArenaViewer view = Mockito.mock(ArenaViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController controller = new ArenaController(view, arena, recognizer);
        ArenaCreator creator = new ArenaCreator();
        creator.create(controller);

        SelectCard cmd = new SelectCard(controller, selection);
        cmd.execute();
        assertTrue(controller.getPlayerController().getPlayDeck().get(selection).getSelected());
    }
}
