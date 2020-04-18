import Model.*;
import View.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Gui gui;
    private Arena arena;
    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    private void start() throws IOException, InterruptedException {
        List<AnyCard> player_card = new ArrayList<>();
        //player_card.add(new Card(2));
        //player_card.add(new Card(7));
        List<AnyCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2));
        player_special.add(new SpecialCard(7));
        Player player = new Player(player_card, player_special, 10, 10, 20, 20);

        List<AnyCard> enemy_card = new ArrayList<>();
        enemy_card.add(new Card(2));
        enemy_card.add(new Card(7));
        List<AnyCard> enemy_special = new ArrayList<>();
        enemy_special.add(new SpecialCard(2));
        enemy_special.add(new SpecialCard(7));
        Enemy enemy = new Enemy(enemy_card, enemy_special, 10, 10, 20, 20);



        Arena arena = new Arena(player, enemy, 1000, 500);
        gui = new Gui(arena);
        gui.draw();
    }
}
