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

    private Player createPlayer(){
        List<Card> player_card = new ArrayList<>();
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));

        return new Player(player_card, player_special, 10, 10, 20, 20, 12);
    }

    private Enemy createEnemy(){
        List<Card> enemy_card = new ArrayList<>();
        List<SpecialCard> enemy_special = new ArrayList<>();
        enemy_special.add(new SpecialCard(2, '*', "card no 2"));
        enemy_special.add(new SpecialCard(7, '+', "card no 7"));
        enemy_special.add(new SpecialCard(4, '-', "card no 4"));
        enemy_special.add(new SpecialCard(5, '/', "card no 5"));
        return new Enemy(enemy_card, enemy_special, 10, 10, 20, 20, 12);
    }

    public void create(){
        Player player = createPlayer();
        Enemy enemy = createEnemy();

        controller.setPlayerController(player);
        controller.setEnemyController(enemy);
    }

}
