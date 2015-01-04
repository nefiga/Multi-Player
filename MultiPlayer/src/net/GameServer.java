package net;

import Game.GameLoop;

import java.io.IOException;
import java.net.*;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private GameLoop game;

    public GameServer(GameLoop game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData()).trim();
            System.out.println("Client > "  + "  Client port number: " + packet.getPort() + "  " + new String(packet.getData()));
            if (message.equals("ping")) {
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
            }
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
