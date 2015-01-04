package net;

import Game.GameLoop;

import java.io.Serializable;

public abstract class Packet implements Serializable{

    int packetType;

    public Packet(int type) {
        this.packetType = type;
    }

    public int getPacketType() {
        return packetType;
    }

    public abstract void updateGameFromData(GameLoop game);
}
