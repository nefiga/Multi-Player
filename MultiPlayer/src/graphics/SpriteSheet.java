package graphics;

public class SpriteSheet {

    public static SpriteSheet spriteSheet = new SpriteSheet("sprite_sheet", 320, 320);

    private String spriteSheetImage;

    private int width, height;

    private int[] sheet;

    public SpriteSheet(String spriteSheetImage, int width, int height) {
        this.spriteSheetImage = spriteSheetImage;
        this.width = width;
        this.height = height;
        sheet = ImageUtils.getImage(spriteSheetImage);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getImage(int startX, int startY, int imageWidth, int imageHeight) {
        int[] returnPixels = new int[imageWidth * imageHeight];
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                if (x + startX > width || y + startY > height) throw new RuntimeException("Trying to access pixels outside of the sprite sheet: " + spriteSheetImage);
                returnPixels[x + y  * imageWidth] = sheet[x + startX + (y + startY) * width];
            }
        }
        return returnPixels;
    }
}
