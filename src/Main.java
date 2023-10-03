import core.Food;
import core.MyFrame;
import core.Snake;
import until.Const;
import until.ImageGet;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends MyFrame{

    Snake snake = new Snake(100, 100);
    Food food = new Food();

    Image fail = ImageGet.imageHashMap.get("fail");


    @Override
    public void loadFrame() {
        super.loadFrame();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                snake.keyPressed(e);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(snake.isLive()) {
            snake.draw(g);
            if(food.isLive()){
                food.draw(g);
                snake.eatFood(food);
            }
            else{
                food = new Food();
            }
        }
        else{
            g.drawImage(fail, Const.windowWidth / 2 - fail.getWidth(null)/2, Const.windowHeight / 2 - fail.getHeight(null)/2, null);
        }
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("得分：" + snake.getScore(), Const.windowWidth - 140, 100);

    }

    public static void main(String[] args) {
        new Main().loadFrame();
    }
}
