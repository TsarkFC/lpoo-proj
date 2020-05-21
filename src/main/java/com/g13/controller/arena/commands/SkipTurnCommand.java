package com.g13.controller.arena.commands;
import com.g13.controller.arena.ParticipantController;

public class SkipTurnCommand implements Command{
    private ParticipantController controller;

    public SkipTurnCommand(ParticipantController controller){
        this.controller = controller;
    }

    public void execute() {
        controller.setTurnOver(true);
    }
}