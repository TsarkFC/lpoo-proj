package com.g13.controller.arena.command;

import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.commands.SkipTurnCommand;
import com.g13.model.arena.BarSet;
import com.g13.model.arena.GameParticipant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkipTurnCommandTest {
    @Test
    public void testExecute(){
        BarSet barSet = Mockito.mock(BarSet.class);
        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);
        ParticipantController controller = new ParticipantController(player);

        SkipTurnCommand command = new SkipTurnCommand(controller);
        command.execute();

        assertEquals(true, controller.getParticipant().getTurnOver());
    }
}
