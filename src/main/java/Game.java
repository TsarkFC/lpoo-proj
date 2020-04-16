import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

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
        List<Card> player_card = new ArrayList<>();
        player_card.add(new Card(2));
        player_card.add(new Card(7));
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2));
        player_special.add(new SpecialCard(7));
        Player player = new Player(player_card, player_special);

        List<Card> enemy_card = new ArrayList<>();
        enemy_card.add(new Card(2));
        enemy_card.add(new Card(7));
        List<SpecialCard> enemy_special = new ArrayList<>();
        enemy_special.add(new SpecialCard(2));
        enemy_special.add(new SpecialCard(7));
        Enemy enemy = new Enemy(enemy_card, enemy_special);

        Arena arena = new Arena(player, enemy, 50, 20);
        gui = new Gui(arena);
        gui.draw();
    }
}
