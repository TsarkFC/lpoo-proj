package com.g13.controller.command;

import com.g13.controller.ParticipantController;
import com.g13.controller.commands.SkipTurnCommand;
import com.g13.model.BarSet;
import com.g13.model.GameParticipant;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
