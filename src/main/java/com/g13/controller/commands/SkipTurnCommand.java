package com.g13.controller.commands;
import com.g13.controller.ParticipantController;

public class SkipTurnCommand implements Command{
    private ParticipantController controller;

    public SkipTurnCommand(ParticipantController controller){
        this.controller = controller;
    }

    public void execute() {
        controller.setTurnOver(true);
    }
}