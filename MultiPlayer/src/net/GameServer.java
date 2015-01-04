package net;

import Game.GameLoop;
import entity.Player;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private GameLoop game;

    private List<Player> conectedPlayers = new ArrayList<Player>();

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
                Packet p = getPacket(packet.getData());
            } catch (IOException e) {
                e.printStackTrace();
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

    public Packet getPacket(byte[] data) {
        Packet packet = null;
        try {
            ByteArrayInputStream b = new ByteArrayInputStream(data);
            ObjectInputStream o = new ObjectInputStream(b);
            packet = (Packet) o.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return packet;
    }
}
