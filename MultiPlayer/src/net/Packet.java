package net;

import java.io.Serializable;

public abstract class Packet implements Serializable{

    int packetType;

    public Packet(int type) {
        this.packetType = type;
    }

    public static Packet getPacket(int type) {
        switch (type) {
            case 00:
                return new Packet00PlayerLogIn();
        }
        return null;
    }

    public abstract void updateData(byte[] data);

    public abstract byte[] getData();
}
