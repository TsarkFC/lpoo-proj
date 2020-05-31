package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.commands.*;
import com.g13.view.arena.ArenaViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandParserTest {
    @Property
    public void parseTest(@ForAll ArenaViewer.COMMAND command, @ForAll boolean finished){
        ArenaController controller = Mockito.mock(ArenaController.class);
        ParticipantController fake_part = Mockito.mock(ParticipantController.class);
        Mockito.when(controller.getEnemyController()).thenReturn(fake_part);
        Mockito.when(controller.getPlayerController()).thenReturn(fake_part);
        Mockito.when(fake_part.getTurnOver()).thenReturn(finished);
        Mockito.when(controller.endOfRound()).thenReturn(finished);

        CommandParser parser = new CommandParser(controller);
        List<Command> commands = parser.parse(command);

        int select = -1;
        if (command == ArenaViewer.COMMAND.ONE) select = 0;
        else if(command == ArenaViewer.COMMAND.TWO) select = 1;
        else if(command == ArenaViewer.COMMAND.THREE) select = 2;
        else if(command == ArenaViewer.COMMAND.FOUR) select = 3;
        if (select != -1)
            assertTrue(commands.get(0) instanceof SelectCard);

        if (command == ArenaViewer.COMMAND.SWITCH) {
            if (controller.endOfRound())
                assertTrue(commands.get(0) instanceof InterstageHandler);
            else
                assertTrue(commands.get(0) instanceof SkipTurnCommand);
        }

        if (command == ArenaViewer.COMMAND.DRAW){
            if(!controller.getPlayerController().getTurnOver() && !controller.getEnemyController().getTurnOver()) {
                assertTrue(commands.get(0) instanceof DrawCardCommand);
                assertTrue(commands.get(1) instanceof PlayEnemyTurn);
            }
            else if (!controller.getEnemyController().getTurnOver())
                assertTrue(commands.get(0) instanceof PlayEnemyTurn);
            else if (!controller.getPlayerController().getTurnOver())
                assertTrue(commands.get(0) instanceof DrawCardCommand);

        }

        if(!controller.getPlayerController().getTurnOver() && command == ArenaViewer.COMMAND.PLAYCARD)
            assertTrue(commands.get(0) instanceof PlaySpecialCardCommand);

        if (command == ArenaViewer.COMMAND.QUIT)
            assertTrue(commands.get(0) instanceof QuitCommand);
    }
}
