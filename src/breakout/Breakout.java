package breakout;

import javax.swing.JFrame;

public class Breakout extends JFrame {
	
	public Breakout(Levels currentLevel, int timeInterval, int brickRows, int brickColumns)
    {
        add(new Board(currentLevel,timeInterval,brickRows,brickColumns));
        
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGTH);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
    	Levels firstLevel = new Levels(0,10,5,6);
    	firstLevel.startLevel();
    }
}