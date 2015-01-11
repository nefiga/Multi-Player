package entity;


import Game.GameLoop;
import Game.Keyboard;
import net.Packet01PlayerMoved;

public class Player extends Entity {

    public String userName;

    public Keyboard keyboard;

    public Player(String userName, int[] sprite, int x, int y, int width, int height) {
        super(sprite, x, y, width, height);
        this.userName = userName;
    }

    public Player(String userName, int[] sprite, int x, int y, int width, int height, Keyboard keyboard) {
        super(sprite, x, y, width, height);
        this.userName = userName;
        this.keyboard = keyboard;
    }

    @Override
    public void update() {
        if (keyboard.up) y--;
        if (keyboard.down) y++;
        if (keyboard.left) x--;
        if (keyboard.right) x++;
        GameLoop.sendData(new Packet01PlayerMoved(userName, x, y));
    }

    public String getUserName() {
        return userName;
    }
}
