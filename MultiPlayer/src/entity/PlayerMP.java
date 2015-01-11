package entity;

public class PlayerMP extends Player{

    public PlayerMP(String userName, int[] sprite, int x, int y, int width, int height) {
        super(userName, sprite, x, y, width, height);
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
