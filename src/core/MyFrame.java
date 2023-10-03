package core;

import until.Const;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Thread.sleep;

public class MyFrame extends Frame {

    /**
     * 用于启动框架和生成背景
     */
    public void loadFrame(){
        this.setBackground(Color.WHITE);
        this.setTitle(Const.GameName);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        this.setSize(Const.windowHeight, Const.windowWidth);
        this.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    MyFrame.this.repaint();
                    try {
                        sleep(1000 / Const.FPS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }



    // test frame
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.loadFrame();
//        frame.paint();
    }
}
