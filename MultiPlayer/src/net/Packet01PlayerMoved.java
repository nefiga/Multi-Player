package net;

import Game.GameLoop;

public class Packet01PlayerMoved extends Packet{

    String userName;
    int x, y;

    public Packet01PlayerMoved(String userName, int x, int y) {
        super("01");
        this.userName = userName;
        this.x = x;
        this.y = y;
    }

    @Override
    public byte[] getData() {
        String player = userName + "," + x + "," + y;
        return (packetType + player).getBytes();
    }

    @Override
    public void updateGameFromData(GameLoop game) {

    }
}
