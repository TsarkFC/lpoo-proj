package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.commands.DoNothing;
import com.g13.controller.arena.commands.QuitCommand;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.Arena;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuitCommandTest {
    @Test
    public void quitCommandTest(){
        Arena arena = new Arena(10, 10);
        ArenaViewer view = Mockito.mock(ArenaViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController controller = new ArenaController(view, arena, recognizer);

        QuitCommand command = new QuitCommand(controller);
        command.execute();
        new DoNothing().execute();

        assertTrue(arena.isFinished());
    }
}
