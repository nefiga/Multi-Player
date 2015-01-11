package net;

import Game.GameLoop;

import java.io.IOException;
import java.net.*;

public class GameClient extends Thread {

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private PacketParser parser;
    private GameLoop game;

    public GameClient(GameLoop game, String ipAddress) {
        this.game = game;
        parser = new PacketParser(game);
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
                parser.parseData(packet.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("CLIENT > " +" IP " + packet.getAddress().getHostAddress() + "  " + new String(packet.getData()));
        }
    }

    public void sendData(byte[] data) {
        //System.out.println("CLIENT > " + "Sending data to: " + ipAddress.getHostAddress() + " on port: " + "1331");
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
