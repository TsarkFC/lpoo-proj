package creator;

import controller.ArenaController;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ArenaCreator {
    private ArenaController controller;

    public ArenaCreator(ArenaController controller){
        this.controller = controller;
    }

    private GameParticipant createParticipant(){
        List<Card> player_card = new ArrayList<>();
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));

        return new GameParticipant(player_card, player_special, 10, 10, 20, 20, 12);
    }

    public void create(){
        GameParticipant player = createParticipant();
        GameParticipant enemy = createParticipant();

        controller.setPlayerController(player);
        controller.setEnemyController(enemy);
    }

}
