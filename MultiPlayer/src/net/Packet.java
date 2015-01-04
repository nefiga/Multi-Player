package net;

import Game.GameLoop;

import java.io.Serializable;

public abstract class Packet implements Serializable{

    String packetType;

    public Packet(String type) {
        this.packetType = type;
    }

    public String getPacketType() {
        return packetType;
    }

    public abstract byte[] getData();

    public abstract void updateGameFromData(GameLoop game);
}
