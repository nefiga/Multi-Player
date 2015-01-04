package net;

import Game.GameLoop;

public class Packet00PlayerLogIn extends Packet{

    String userName;

    public Packet00PlayerLogIn(String userName) {
        super("00");
        this.userName = userName;;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public byte[] getData() {
        return (packetType + userName).getBytes();
    }

    @Override
    public void updateGameFromData(GameLoop game) {

    }
}
