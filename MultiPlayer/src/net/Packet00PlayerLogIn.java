package net;

import Game.GameLoop;

public class Packet00PlayerLogIn extends Packet{

    String userName;
    int x, y;

    public Packet00PlayerLogIn(String userName, int x, int y) {
        super(00);
        this.userName = userName;
        this.x = x;
        this.y = y;
    }

    public String getUserName() {
        return userName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void updateGameFromData(GameLoop game) {

    }
}
