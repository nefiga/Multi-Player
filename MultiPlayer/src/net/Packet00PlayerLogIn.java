package net;

import Game.GameLoop;

public class Packet00PlayerLogIn extends Packet{

    String userName;
    int x, y;

    public Packet00PlayerLogIn(String userName, int x, int y) {
        super("00");
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public byte[] getData() {
        userName += ",";
        userName += x;
        userName += ",";
        userName += y;
        return (packetType + userName).getBytes();
    }

    @Override
    public void updateGameFromData(GameLoop game) {

    }
}
