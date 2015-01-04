package entity;

import java.net.InetAddress;

public class PlayerMP extends Player{

    InetAddress ipAddress;
    int port;

    public PlayerMP(String name, int[] sprite, int x, int y, int width, int height, InetAddress ipAddress, int port) {
        super(name, sprite, x, y, width, height);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void update() {

    }
}
