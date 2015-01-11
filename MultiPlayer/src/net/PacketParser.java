package net;

import Game.GameLoop;
import entity.PlayerMP;
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
            String userName = dataLines[0].substring(2);
            int x = Integer.parseInt(dataLines[1]);
            int y = Integer.parseInt(dataLines[2]);
            int[] playerSprite = SpriteSheet.spriteSheet.getImage(0, 0, 16, 16);
            game.addPlayer(new PlayerMP(userName, playerSprite, x, y, 16, 16));
        }
        if (packetType.equals("01")) {
            String[] dataLines = stringData.split(",");
            String userName = dataLines[0].substring(2);
            int x = Integer.parseInt(dataLines[1]);
            int y = Integer.parseInt(dataLines[2]);
            game.updatePlayerPosition(userName, x, y);
        }
    }
}
