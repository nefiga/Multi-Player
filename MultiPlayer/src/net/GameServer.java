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
                System.out.println("SERVER > " + "Packet Type: " + type);
                if (type.equals("00")) {
                    String user = new String(packet.getData()).trim();
                    user = user.substring(2, user.indexOf(','));
                    System.out.println("SERVER > " + "User: " + user + " just singed in");
                    IPs.put(Integer.toString(packet.getPort()), packet.getAddress());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendData(packet.getData(), Integer.toString(packet.getPort()));
        }
    }

    public void sendData(byte[] data, String excludePort) {
        for (String key : IPs.keySet()) {
            if (key.equals(excludePort)) continue;
            try {
                int portInt = Integer.parseInt(key);
                InetAddress address = IPs.get(key);
                DatagramPacket packet = new DatagramPacket(data, data.length, address, portInt);
                socket.send(packet);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
