package com.g13.controller.command;

import com.g13.controller.GameParticipantController;
import com.g13.controller.commands.SkipTurnCommand;
import com.g13.model.GameParticipant;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SkipTurnCommandTest {
    @Test
    public void testExecute(){
        GameParticipant player = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipantController controller = new GameParticipantController(player);

        SkipTurnCommand command = new SkipTurnCommand(controller);
        command.execute();

        assertEquals(true, controller.getParticipant().getTurnOver());
    }
}
