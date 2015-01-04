package entity;

import Game.Screen;

public class Entity {

    protected int x, y;
    protected int width, height;

    protected int[] sprite;

    public Entity(int[] sprite, int x, int y, int width, int height) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update() {

    }

    public void render(Screen screen) {
        screen.render(sprite, x, y, width, height);
    }
}
