package net;

import Game.GameLoop;

public class Packet00Player extends Packet{

    public Packet00Player() {
        super("-1");
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public void updateGameFromData(GameLoop game) {

    }
}
