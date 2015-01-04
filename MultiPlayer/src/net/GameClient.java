package net;

import Game.GameLoop;

import java.io.IOException;
import java.net.*;

public class GameClient extends Thread {

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GameLoop game;

    public GameClient(GameLoop game, String ipAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
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
            System.out.println("Server > " +" IP " + packet.getAddress().getHostAddress() + "  " + new String(packet.getData()));
        }
    }

    public void sendData(byte[] data) {
        System.out.println("Sending data to: " + ipAddress.getHostAddress());
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
