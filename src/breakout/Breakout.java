package breakout;

import java.util.Date;

import javax.swing.JFrame;

public class Breakout extends JFrame {
	
	public Breakout(Levels currentLevel) {
		add(new Board(currentLevel, currentLevel.gettimeInterval(),
				currentLevel.getBrickRows(), currentLevel.getBrickColumns()));

		setTitle("Breakout");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Commons.WIDTH, Commons.HEIGTH);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		Date curTime = new Date();
		Levels firstLevel = new Levels(0, 9, 1, 6, curTime);
		firstLevel.startLevel();
	}
}