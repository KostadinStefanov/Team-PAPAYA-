package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Board extends JPanel implements Commons {

    Image ii;
    Timer timer;
    String message = "Game Over";
    Ball ball;
    Paddle paddle;
    Brick bricks[];

    boolean ingame = true;
    int timerId;


    public Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        bricks = new Brick[30];
        setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }

        public void addNotify() {
            super.addNotify();
            gameInit();
        }

    public void gameInit() {

        ball = new Ball();
        paddle = new Paddle();


        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }
    }

}
