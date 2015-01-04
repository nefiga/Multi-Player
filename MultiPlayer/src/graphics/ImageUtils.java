package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static final int TRANSPARENT = 0xFFff7cf7;

    public static int[] getImage(String fileName) {
        int[] pixels;
        try {
            BufferedImage image = ImageIO.read(ImageUtils.class.getResourceAsStream("/" + fileName + ".png"));
            pixels = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
            return pixels;
        } catch (IOException e) {
            throw new RuntimeException("Could not find image: " + fileName);
        }
    }
}
