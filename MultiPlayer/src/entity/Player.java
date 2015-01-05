package entity;


import Game.GameLoop;
import net.Packet00PlayerLogIn;

public class Player extends Entity {

    public String userName;

    public Player(String userName, int[] sprite, int x, int y, int width, int height) {
        super(sprite, x, y, width, height);
        this.userName = userName;
        GameLoop.sendData(new Packet00PlayerLogIn(userName, x, y));
    }

    @Override
    public void update() {

    }

    public String getUserName() {
        return userName;
    }
}
