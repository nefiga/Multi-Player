package net;

import Game.GameLoop;
import entity.Player;
import graphics.SpriteSheet;

public class PacketParser {

    GameLoop game;

    public PacketParser(GameLoop game) {
        this.game = game;
    }

    public void parseData(byte[] data) {
        String stringData = new String(data).trim();
        String packetType = stringData.substring(0, 2);
        if (packetType.equals("00")) {
            String[] dataLines = stringData.split(",");
            String name = dataLines[0];
            int x = Integer.getInteger(dataLines[1]);
            int y = Integer.getInteger(dataLines[2]);
            int[] playerSprite = SpriteSheet.spriteSheet.getImage(0, 0, 16, 16);
            game.addPlayer(new Player(name, playerSprite, x, y, 16, 16));
        }
    }
}
