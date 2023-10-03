package until;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

public class ImageGet {
    public static HashMap<String, Image> imageHashMap = new HashMap<>();

    static {
        imageHashMap.put("snakeHead", getImage(Const.ImagePath + "head.png"));
        imageHashMap.put("snakeBody", getImage(Const.ImagePath + "orange.png"));
        imageHashMap.put("food", getImage(Const.ImagePath + "pineapple.png"));
        imageHashMap.put("fail", getImage(Const.ImagePath + "fail.png"));
    }

    public static Image getImage(String path) {

        URL url = ImageGet.class.getClassLoader().getResource(path);
        try {
            if (url != null) {
                return ImageIO.read(url);
            }
            else{
                throw new RuntimeException("图片不存在");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static BufferedImage rotateImage(BufferedImage image, int angle){
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int type = image.getType();
        BufferedImage bufferedImage = null;
        Graphics2D graphics2D;
        graphics2D = ((bufferedImage = new BufferedImage(w, h, type)).createGraphics());
        graphics2D.rotate(Math.toRadians(angle), (double) w / 2, (double) h / 2);
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();
        return bufferedImage;
    }



}
