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
		
	

		class AudioPlayer {{
		    new Thread(new Runnable() {
		      public void run() {
		        try {

		          Clip clip = AudioSystem.getClip();
		          AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream("BackgroundMusic.wav"));         
		          clip.open(inputStream);
		          clip.start(); 
		        } catch (Exception e) {
		          System.err.println(e.getMessage());
		        }
		      }
		    }).start();  
		  }     
		}
		
		
		
	}

	public static void main(String[] args) {
		Date curTime = new Date();
		Levels firstLevel = new Levels(0, 9, 1, 6, curTime);
		firstLevel.startLevel();
	}
}