package net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Packet00PlayerLogIn extends Packet{

    String userName;
    int x, y;

    public Packet00PlayerLogIn(byte[] data) {
        super(00);
        updateData(data);
    }

    public Packet00PlayerLogIn(String userName, int x, int y) {
        super(00);
    }

    @Override
    public void updateData(byte[] data) {
        PlayerLogInData playerData = null;
        try {
            ObjectInputStream objectinput = new ObjectInputStream(new ByteArrayInputStream(data));
            playerData = (PlayerLogInData) objectinput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (playerData != null) {
            userName = playerData.userName;
            x = playerData.x;
            y = playerData.y;
        }
    }

    private class PlayerLogInData implements Serializable {
        int x, y;
        String userName;
    }
}
