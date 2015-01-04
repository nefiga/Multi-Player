package Game;

import graphics.ImageUtils;

public class Screen {

    private int[] pixels;

    private int width, height;

    public Screen(int width, int height) {
        pixels = new int[width * height];
        this.width = width;
        this.height = height;
    }

    public void clear(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    public void render(int[] image, int startX, int startY, int imageWidth, int imageHeight) {
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                if (x + startX > width || y + startY > height) continue;
                if (image[x + y * imageWidth] == ImageUtils.TRANSPARENT) continue;
                pixels[x + startX + (y + startY) * width] = image[x + y * imageWidth];
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
