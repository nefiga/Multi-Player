package net;

import Game.GameLoop;

import java.io.IOException;
import java.net.*;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private GameLoop game;

    private Map<String, InetAddress> IPs = new HashMap<String, InetAddress>();

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
                String type = new String(packet.getData()).substring(0, 2);
                System.out.println("Packet Type: " + type);
                if (type.equals("00") && !IPs.containsKey(Integer.toString(packet.getPort()))) {
                    String user = new String(packet.getData());
                    user = user.substring(2, user.length());
                    System.out.println("User: " + user + " just singed in");
                    IPs.put(Integer.toString(packet.getPort()), packet.getAddress());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendData(packet.getData(), Integer.toString(packet.getPort()));
        }
    }

    public void sendData(byte[] data, String excludePort) {
        Object[] keys = IPs.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            String portKey = (String) keys[i];
            if (portKey.equals(excludePort)) continue;
            try {
                DatagramPacket packet = new DatagramPacket(data, data.length, IPs.get(portKey), Integer.getInteger(portKey));
                socket.send(packet);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
