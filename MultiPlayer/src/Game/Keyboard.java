package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] keyPressed = new boolean[128];
    public boolean up, down, right, left;

    public void update() {
        up = keyPressed[KeyEvent.VK_UP];
        down = keyPressed[KeyEvent.VK_DOWN];
        right = keyPressed[KeyEvent.VK_RIGHT];
        left = keyPressed[KeyEvent.VK_LEFT];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed[e.getKeyCode()] = false;
    }
}
