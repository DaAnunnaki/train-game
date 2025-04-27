import java.awt.*;
import java.awt.image.*;
import java.util.*;
//ziyun, since we dont have a text file for the train cards and their images, this class
//can create them and help instantiate it in the gameState and Player classes
//in player, it creates the empty hand, in gameState, it controls the deck
public class Helper {
    public static final Set<String> colors = Set.of("black", "blue", "green", "loco", "orange", "purple", "red", "white", "yellow");

    public static HashMap<String, Integer> getEmptyDeck() {
        HashMap<String, Integer> colorMap = new HashMap<>();
        for (String s : colors) {
            colorMap.put(s, 0);
        }
        return colorMap;
    }

    public static BufferedImage applyOpacity(BufferedImage image, float alpha) {
        BufferedImage target = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = target.createGraphics();
        graphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphic.drawImage(image, 0, 0, null);
        graphic.dispose();
        return target;
    }

    public static BufferedImage rotate(BufferedImage image, int angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
               cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = image.getWidth();
        int h = image.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin),
            newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(neww, newh, image.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawRenderedImage(image, null);
        graphic.dispose();
        return rotated;
    }
}