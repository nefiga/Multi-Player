package entity;


public class Player extends Entity {

    public String userName;

    public Player(String userName, int[] sprite, int x, int y, int width, int height) {
        super(sprite, x, y, width, height);
        this.userName = userName;
    }

    @Override
    public void update() {

    }

    public String getUserName() {
        return userName;
    }
}
