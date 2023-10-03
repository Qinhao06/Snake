package core;

import until.Const;
import until.ImageGet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends BaseObject{

    private int speed;
    private int length;

    private int score;

    private final BufferedImage snakeHead = (BufferedImage)ImageGet.imageHashMap.get("snakeHead");

    private BufferedImage rotateHeadImage =  snakeHead;

    // 0 - right 1-left 2-up 3-down
    private int direction = 0;

    public List<Point> bodyPoints = new ArrayList<>();


    public Snake(int x, int y) {
        setLive(true);
        setX(x);
        setY(y);
        setImage(ImageGet.imageHashMap.get("snakeBody"));
        setWidth(getImage().getWidth(null));
        setHeight(getImage().getHeight(null));
        this.speed = 5;
        this.length = 1;
        this.score = 0;

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case  KeyEvent.VK_RIGHT:
                direction = 0;
                rotateHeadImage = ImageGet.rotateImage(snakeHead, 0);
                break;
            case  KeyEvent.VK_LEFT:
                direction = 1;
                rotateHeadImage = ImageGet.rotateImage(snakeHead, 180);
                break;
            case  KeyEvent.VK_UP:
                direction = 2;
                rotateHeadImage = ImageGet.rotateImage(snakeHead, -90);
                break;
            case  KeyEvent.VK_DOWN:
                direction = 3;
                rotateHeadImage = ImageGet.rotateImage(snakeHead,  90);
                break;

        }
    }

    public void eatFood(Food food){
        Rectangle rectangle = getRectangle();
       if(food.isLive() && isLive() && rectangle.intersects(food.getRectangle())){
            length++;
            score+= Const.oneFruitScore;
            food.setLive(false);
        }
    }

    public boolean isOut(){
        return getX() < 0 || getX() > Const.windowWidth
                || getY() < 0 || getY() > Const.windowHeight;
    }

    public boolean isTouchBody(){
        for (int i = getWidth() / speed + 5; i < bodyPoints.size() - 1; i++){
            Point point = bodyPoints.get(i);
            if(getX() == point.x && getY() == point.y)
                return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        if(isOut() || isTouchBody()){
            this.setLive(false);

        }
        bodyPoints.add(new Point(getX(), getY()));
        if(bodyPoints.size() == getWidth()/speed * (length + 1)){
            bodyPoints.remove(0);
        }
        g.drawImage(rotateHeadImage, getX(), getY(), null);
        int len = bodyPoints.size() - 1 - getWidth() / speed;
        for(int i = len; i >= getWidth()/speed; i-=getWidth()/speed){
            g.drawImage(getImage(), bodyPoints.get(i).x, bodyPoints.get(i).y,null);
        }
        move();
    }

    @Override
    public void move() {
        switch (direction){
            case 0:
                setX(getX()+speed);
                break;
            case 1:
                setX(getX()-speed);
                break;
            case 2:
                setY(getY()-speed);
                break;
            case 3:
                setY(getY()+speed);
                break;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }
}
