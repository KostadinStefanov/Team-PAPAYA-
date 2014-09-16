package breakout;

import java.util.Date;
import javax.swing.JFrame;

import javazoom.jl.decoder.JavaLayerException;

public class Breakout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Breakout(Levels currentLevel) {
		add(new Board(currentLevel, currentLevel.gettimeInterval(),
				currentLevel.getBrickRows(), currentLevel.getBrickColumns()));

		setTitle("SoftuniBreaker Level " + (int)(currentLevel.getLevel() + 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Commons.WIDTH, Commons.HEIGTH);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		setResizable(false);
		setVisible(true);
		
			
				
	}

	public static void main(String[] args) throws JavaLayerException {
		Date curTime = new Date();
		Levels firstLevel = new Levels(0, 9, 1, 6, curTime);
		firstLevel.startLevel();
		
       MusicPlayer mp3 = new MusicPlayer("src/resources/Tribal.mp3");  
        mp3.play();  
	}
}