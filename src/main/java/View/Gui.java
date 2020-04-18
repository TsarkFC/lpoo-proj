package View;

import Commands.Command;
import Commands.DoNothingCommand;
import Commands.DrawCardCommand;
import Commands.QuitCommand;
import Model.Arena;
import Model.Card;
import Model.Enemy;
import Model.Player;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Gui {
    private Arena arena;
    private TerminalScreen screen;

    public Gui(Arena arena) throws IOException {
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(arena.getWidth(), arena.getHeight())).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.arena = arena;
    }

    public void draw() throws IOException {
        screen.clear();
        drawBackground();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        drawPlayer(arena.getPlayer());
        drawEnemy(arena.getEnemy());


        screen.refresh();
    }

    private void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth(), arena.getHeight()), ' ');
    }

    private void drawPlayer(Player player){
        drawCard(1, 19, player.getDraw_deck().size());
        //drawHealthBar(15, 3, player.getHealth(), player.getMaxHealth());
        drawHealthBar(15, 21, player.getHealth(), player.getMaxHealth());
        drawManaBar(15, 22, player.getMana(), player.getMaxMana());
        drawPointBar(15, 12, player.getPoints(), 12);

    }

    private void drawEnemy(Enemy enemy){
        drawCard(1, 0, enemy.getDraw_deck().size());
        //drawHealthBar(15, 1, enemy.getHealth(), enemy.getMaxHealth());
        drawHealthBar(15, 1, enemy.getHealth(), enemy.getMaxHealth());
        drawManaBar(15, 2, enemy.getMana(), enemy.getMaxMana());
        drawPointBar(15, 8, enemy.getPoints(), 12);

    }

    private void drawCard(int x, int y, int deck_size){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        graphics.putString(x + 1, y + 3, " ");
        graphics.putString(x + 2, y + 3, " ");
        graphics.putString(x + 3, y + 3, " ");
        graphics.putString(x + 1, y, " ");
        graphics.putString(x + 2, y, " ");
        graphics.putString(x + 3, y, " ");
        graphics.putString(x, y + 3, " ");
        graphics.putString(x, y + 2, " ");
        graphics.putString(x, y + 1, " ");
        graphics.putString(x + 4, y + 3, " ");
        graphics.putString(x + 4, y + 2, " ");
        graphics.putString(x + 4, y + 1, " ");
        graphics.putString(x + 5, y + 3, " ");
        graphics.putString(x + 5, y + 2, " ");
        graphics.putString(x + 5, y + 1, " ");
        graphics.putString(x + 5, y, " ");

        graphics.putString(x + 1, y + 1, " ");
        graphics.putString(x + 1, y + 2, " ");
        //graphics.putString(x + 2, y + 1, " ");
        graphics.putString(x + 2, y + 2, " "); //No need for this one, the number always covers it;
        graphics.putString(x + 3, y + 1, " ");
        graphics.putString(x + 3, y + 2, " ");


        graphics.putString(x, y, " ");
        graphics.putString(x + 4, y, " ");


        graphics.putString(x + 2, y + 1, String.valueOf(deck_size));



    }

    private void drawPointBar(int x, int y, int point_val, int max_point_val){ //TODO: Variable maximum

        TextGraphics unfilled_point = screen.newTextGraphics();
        unfilled_point.setBackgroundColor(TextColor.Factory.fromString("#AA8855"));
        unfilled_point.setForegroundColor(TextColor.Factory.fromString("#FF0000"));


        TextGraphics filled_point = screen.newTextGraphics();
        filled_point.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        filled_point.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        if(point_val == max_point_val){
            filled_point.setBackgroundColor(TextColor.Factory.fromString("#FFEECC"));
            filled_point.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        }
        for(int i = 0; i <= 16; i+=4) {
                if((i / 4) * 3 <= point_val) {
                    filled_point.putString(x + i, y, "|");
                }
                else{
                    unfilled_point.putString(x + i, y, "|");
                }

        }
        for(int j = 0; j <= 11; j++){
            if(j < point_val){
                filled_point.putString(x + j + j / 3 + 1, y, "_");
            }
            else{
                unfilled_point.putString(x + j + j / 3 + 1, y, "_");
            }
        }

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#AA8855"));

        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(point_val));
        pointNumber.putString(x + 20, y, "/");
        pointNumber.putString(x + 21, y, String.valueOf(max_point_val));



    }

    private void drawHealthBar(int x, int y, int health, int max_health){
        TextGraphics unfilled_point = screen.newTextGraphics();
        unfilled_point.setBackgroundColor(TextColor.Factory.fromString("#775555"));
        unfilled_point.setForegroundColor(TextColor.Factory.fromString("#777777"));


        TextGraphics filled_point = screen.newTextGraphics();
        filled_point.setBackgroundColor(TextColor.Factory.fromString("#BB1111"));
        filled_point.setForegroundColor(TextColor.Factory.fromString("#AA5555"));


        for(int i = 0; i <= 16; i+=4) {
            if((i / 4) * 3 <= (health / (float) max_health) * 12) {
                filled_point.putString(x + i, y, "|");
            }
            else{
                unfilled_point.putString(x + i, y, "|");
            }

        }

        for(int j = 0; j <= 11; j++){
            if(j < (health / (float) max_health) * 12){
                filled_point.putString(x + j + j / 3 + 1, y, "_");
            }
            else{
                unfilled_point.putString(x + j + j / 3 + 1, y, "_");
            }
        }

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#BB1111"));
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 20, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(health));
        pointNumber.putString(x + 21, y, "/");
        pointNumber.putString(x + 22, y, String.valueOf(max_health));

        pointNumber.putString(x - 3, y, "HP:");

    }

    private void drawManaBar(int x, int y, int mana, int max_mana){
        TextGraphics unfilled_point = screen.newTextGraphics();
        unfilled_point.setBackgroundColor(TextColor.Factory.fromString("#6688AA"));
        unfilled_point.setForegroundColor(TextColor.Factory.fromString("#777777"));


        TextGraphics filled_point = screen.newTextGraphics();
        filled_point.setBackgroundColor(TextColor.Factory.fromString("#3D84CC"));
        filled_point.setForegroundColor(TextColor.Factory.fromString("#5555AA"));


        for(int i = 0; i <= 16; i+=4) {
            if((i / 4) * 3 <= (mana / (float) max_mana) * 12) {
                filled_point.putString(x + i, y, "|");
            }
            else{
                unfilled_point.putString(x + i, y, "|");
            }

        }

        for(int j = 0; j <= 11; j++){
            if(j < (mana / (float) max_mana) * 12){
                filled_point.putString(x + j + j / 3 + 1, y, "_");
            }
            else{
                unfilled_point.putString(x + j + j / 3 + 1, y, "_");
            }
        }

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#3D84CC"));
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 20, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(mana));
        pointNumber.putString(x + 21, y, "/");
        pointNumber.putString(x + 22, y, String.valueOf(max_mana));

        pointNumber.putString(x - 5, y, "Mana:");
    }




    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'd') return new DrawCardCommand(arena);
        //if (input.getKeyType() == KeyType.ArrowDown) return new MoveHeroDownCommand(arena);
        //if (input.getKeyType() == KeyType.ArrowUp) return new MoveHeroUpCommand(arena);
        //if (input.getKeyType() == KeyType.ArrowLeft) return new MoveHeroLeftCommand(arena);
       // if (input.getKeyType() == KeyType.ArrowRight) return new MoveHeroRightCommand(arena);

        return new DoNothingCommand();
    }

}
