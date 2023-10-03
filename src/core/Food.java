package core;

import until.Const;
import until.ImageGet;

import java.awt.*;
import java.util.Random;

public class Food extends BaseObject{


    public Food() {
        setLive(true);
        setImage(ImageGet.imageHashMap.get("food"));
        setHeight(this.getImage().getHeight(null));
        setWidth(this.getImage().getWidth(null));
        Random random = Const.random;
        setX(random.nextInt(100, Const.windowWidth-100));
        setY(random.nextInt(100, Const.windowHeight-100));

    }

    @Override
    public void move() {

    }



    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }
}
