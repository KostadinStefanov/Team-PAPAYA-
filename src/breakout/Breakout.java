package breakout;

import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;

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

	public static void main(String[] args) {
		Date curTime = new Date();
		Levels firstLevel = new Levels(0, 9, 1, 6, curTime);
		firstLevel.startLevel();
		
		 //plays 07.mp3 file located at C drive  
        MusicPlayer mp3 = new MusicPlayer("c:/Users/Fantomas/Desktop/PAPAYA SUOUND/Team-PAPAYA-/src/resources/Tribal Tech House Beats Demo.mp3");  
        mp3.play();  
	}
}