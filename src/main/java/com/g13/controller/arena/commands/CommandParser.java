package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;
import com.g13.view.arena.ArenaViewer;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    private ArenaController controller;
    private int select;

    public CommandParser(ArenaController controller){
        this.controller = controller;
        select = -1;
    }

    public List<Command> parse(ArenaViewer.COMMAND command){
        List<Command> commands = new ArrayList<>();
        if (command == ArenaViewer.COMMAND.ONE) select = 0;
        else if(command == ArenaViewer.COMMAND.TWO) select = 1;
        else if(command == ArenaViewer.COMMAND.THREE) select = 2;
        else if(command == ArenaViewer.COMMAND.FOUR) select = 3;
        if (select != -1) {
            commands.add(new SelectCard(controller, select));
            return commands;
        }

        if (command == ArenaViewer.COMMAND.SWITCH) {
            if (controller.endOfRound())
                commands.add(new InterstageHandler(controller));
            else
                commands.add(new SkipTurnCommand(controller.getPlayerController()));
            return commands;
        }

        if (command == ArenaViewer.COMMAND.DRAW){
            if(!controller.getPlayerController().getTurnOver())
                commands.add(new DrawCardCommand(controller));
            if(!controller.getEnemyController().getTurnOver())
                commands.add(new PlayEnemyTurn(controller));
            return commands;
        }

        if(!controller.getPlayerController().getTurnOver() && command == ArenaViewer.COMMAND.PLAYCARD) {
            commands.add(new PlaySpecialCardCommand(controller));
            return commands;
        }

        if (command == ArenaViewer.COMMAND.QUIT) {
            commands.add(new QuitCommand(controller));
            return commands;
        }

        commands.add(new DoNothing());
        return commands;
    }

}
