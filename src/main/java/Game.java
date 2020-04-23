import Commands.ArenaObserver;
import Commands.Command;
import Commands.TurnChecker;
import Model.*;
import View.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game implements ArenaObserver {
    private Gui gui;
    private Arena arena;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    private void start() throws IOException, InterruptedException {
        List<Card> player_card = new ArrayList<>();
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));
        Player player = new Player(player_card, player_special, 10, 10, 20, 20, 12);

        List<Card> enemy_card = new ArrayList<>();
        enemy_card.add(new Card(2));
        enemy_card.add(new Card(7));
        List<SpecialCard> enemy_special = new ArrayList<>();
        enemy_special.add(new SpecialCard(2, '*', "card no 2"));
        enemy_special.add(new SpecialCard(7, '+', "card no 7"));
        enemy_special.add(new SpecialCard(4, '-', "card no 4"));
        enemy_special.add(new SpecialCard(5, '/', "card no 5"));
        Enemy enemy = new Enemy(enemy_card, enemy_special, 10, 10, 20, 20, 12);



        Arena arena = new Arena(player, enemy, 50, 30);
        arena.addObserver(this);
        gui = new Gui(arena);
        gui.draw();

        while(!arena.isFinished()){
            Command command = gui.getNextCommand();
            command.execute();
        }
    }

    @Override
    public void arenaChanged() {
        try{
            gui.draw();
        } catch (IOException e) {

        }
    }
}
