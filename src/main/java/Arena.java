import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Player player;   //to fix
    private Enemy enemy;     //to fix
    private boolean current; //to fix  true->player | false -> enemy

    public Arena(Player player, Enemy enemy, int width, int height){
        this.player = player;
        this.enemy = enemy;
        this.width = width;
        this.height = height;
        this.current = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
