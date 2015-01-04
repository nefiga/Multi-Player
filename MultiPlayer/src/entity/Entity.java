package entity;

import Game.Screen;

public class Entity {

    protected String name;

    protected int x, y;
    protected int width, height;

    protected int[] sprite;

    public Entity(String name, int[] sprite, int x, int y, int width, int height) {
        this.name = name;
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
