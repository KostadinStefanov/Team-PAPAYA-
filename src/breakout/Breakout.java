package breakout;

import java.util.Date;

import javax.swing.JFrame;

public class Breakout extends JFrame {
<<<<<<< HEAD


    public static void main(String[] args) {
    	Levels firstLevel = new Levels(0,7,5,6);
    	firstLevel.startLevel();
    }

=======
	
>>>>>>> afe1d83def278ff2f3927c43204a83b8206c25c9
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
<<<<<<< HEAD
=======

	public static void main(String[] args) {
		Date curTime = new Date();
		Levels firstLevel = new Levels(0, 9, 1, 6, curTime);
		firstLevel.startLevel();
	}
>>>>>>> afe1d83def278ff2f3927c43204a83b8206c25c9
}