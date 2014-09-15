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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Board extends JPanel implements Commons {

	Image ii;
	Timer timer;
	Date startTime;
	String message = "Game Over";
	Ball ball;
	Paddle paddle;
	Background backgroundFirstPart;
	Background backgroundSecondPart;
	Brick bricks[];
	TotalPoints totalPoints;

	boolean ingame = true;
	int timerId;
	int brickRows;
	int brickColumns;
	int timeInterval;
	private Levels currentLevel;

	public Board(Levels currentLevel, int timeInterval, int brickRows,
			int brickColumns) {

		this.brickRows = brickRows;
		this.brickColumns = brickColumns;
		this.timeInterval = timeInterval;
		this.currentLevel = currentLevel;
		this.startTime = currentLevel.getStartTime();

		addKeyListener(new TAdapter());
		setFocusable(true);

		bricks = new Brick[brickRows * brickColumns];
		setDoubleBuffered(true);
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 1000, timeInterval);
	}

	public void addNotify() {
		super.addNotify();
		gameInit();
	}

	public void gameInit() {

		ball = new Ball();
		paddle = new Paddle();
		backgroundFirstPart = new Background(currentLevel.getLevel());
		totalPoints = new TotalPoints(currentLevel);
		
		int k = 0;
		for (int i = 0; i < brickRows; i++) {
			for (int j = 0; j < brickColumns; j++) {
				bricks[k] = new Brick(j * 40 + 30, i * 10 + 100,
						currentLevel.getLevel());
				k++;
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (ingame) {
			g.drawImage(backgroundFirstPart.getImage(), backgroundFirstPart.getX(), backgroundFirstPart.getY(),
					backgroundFirstPart.getWidth(), backgroundFirstPart.getHeight(), this);
			
			g.drawImage(ball.getImage(), ball.getX(), ball.getY(),
					ball.getWidth(), ball.getHeight(), this);
			
			g.drawImage(ball.getImage(), ball.getX(), ball.getY(),
					ball.getWidth(), ball.getHeight(), this);
			g.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
					paddle.getWidth(), paddle.getHeight(), this);

			Font font = new Font("Verdana", Font.BOLD, 13);
			FontMetrics metr = this.getFontMetrics(font);
			
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString("Points:" + totalPoints.getPoints(), 0, 10);
			

		    g.drawString("Time:" + currentLevel.getCurrentTime() , 215, 10);
			
			
			for (int i = 0; i < brickColumns * brickRows; i++) {
				if (!bricks[i].isDestroyed())
					g.drawImage(bricks[i].getImage(), bricks[i].getX(),
							bricks[i].getY(), bricks[i].getWidth(),
							bricks[i].getHeight(), this);
			}
		} else {

			Font font = new Font("Verdana", Font.BOLD, 18);
			FontMetrics metr = this.getFontMetrics(font);

			g.setColor(Color.BLACK);
			g.setFont(font);
			
			g.drawString("Points:" + totalPoints.getPoints(), 0, 30);
			
		    g.drawString("Time:" + currentLevel.getCurrentTime() , 180, 30);
		    
			g.drawString(message,
					(Commons.WIDTH - metr.stringWidth(message)) / 2,
					Commons.WIDTH / 2);
			g.drawString(message,
					(Commons.WIDTH - metr.stringWidth(message)) / 2,
					Commons.WIDTH / 2);		
			
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);
		}
	}

	class ScheduleTask extends TimerTask {
		public void run() {
			ball.move();
			paddle.move();
			checkCollision();
			repaint();
		}
	}

	public void stopGame() {
		ingame = false;
		timer.cancel();
	}

	public void checkCollision() {

		if (ball.getRect().getMaxY() > Commons.BOTTOM) {
			stopGame();
		}

		for (int i = 0, j = 0; i < brickColumns * brickRows; i++) {
			if (bricks[i].isDestroyed()) {
				j++;
			}
			if (j == brickColumns * brickRows) {
				message = "Next Level";
				if (currentLevel.getLevel() <= 2) {
					Levels newLevel = new Levels(currentLevel.getLevel() + 1,
							currentLevel.gettimeInterval() - 2,
							currentLevel.getBrickRows() + 1,
							currentLevel.getBrickColumns(),
							currentLevel.getStartTime());
					currentLevel = newLevel;
					stopGame();
					currentLevel.startLevel();
				} else {
					message = "Victory";
					stopGame();
				}

			}
		}

		if ((ball.getRect()).intersects(paddle.getRect())) {

			int paddleLPos = (int) paddle.getRect().getMinX();
			int ballLPos = (int) ball.getRect().getMinX();

			int first = paddleLPos + 8;
			int second = paddleLPos + 16;
			int third = paddleLPos + 24;
			int fourth = paddleLPos + 32;

			if (ballLPos < first) {
				ball.setXDir(-1);
				ball.setYDir(-1);
			}

			if (ballLPos >= first && ballLPos < second) {
				ball.setXDir(-1);
				ball.setYDir(-1 * ball.getYDir());
			}

			if (ballLPos >= second && ballLPos < third) {
				ball.setXDir(0);
				ball.setYDir(-1);
			}

			if (ballLPos >= third && ballLPos < fourth) {
				ball.setXDir(1);
				ball.setYDir(-1 * ball.getYDir());
			}

			if (ballLPos > fourth) {
				ball.setXDir(1);
				ball.setYDir(-1);
			}

		}

		for (int i = 0; i < brickColumns * brickRows; i++) {
			if ((ball.getRect()).intersects(bricks[i].getRect())) {

				int ballLeft = (int) ball.getRect().getMinX();
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getWidth();
				int ballTop = (int) ball.getRect().getMinY();

				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point pointTop = new Point(ballLeft, ballTop - 1);
				Point pointBottom = new Point(ballLeft, ballTop + ballHeight
						+ 1);

				if (!bricks[i].isDestroyed()) {
					if (bricks[i].getRect().contains(pointRight)) {
						ball.setXDir(-1);
					}

					else if (bricks[i].getRect().contains(pointLeft)) {
						ball.setXDir(1);
					}

					if (bricks[i].getRect().contains(pointTop)) {
						ball.setYDir(1);
					}

					else if (bricks[i].getRect().contains(pointBottom)) {
						ball.setYDir(-1);
					}

					bricks[i].setDestroyed(true);
					totalPoints.addNewPoints(currentLevel.getLevel());
				}
			}
		}
	}
}