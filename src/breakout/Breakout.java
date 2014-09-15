package breakout;

import javax.swing.JFrame;

public class Breakout extends JFrame {


    public static void main(String[] args) {
    	Levels firstLevel = new Levels(0,7,5,6);
    	firstLevel.startLevel();
    }

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
}