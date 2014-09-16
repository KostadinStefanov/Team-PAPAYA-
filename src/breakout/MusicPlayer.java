package breakout;

import java.io.BufferedInputStream;  
import java.io.FileInputStream;  
  
import javazoom.jl.player.Player;  
  
  
public class MusicPlayer {  
    private String filename;  
    private Player player;  
  
    // constructor that takes the name of an MP3 file  
    public MusicPlayer(String filename) {  
        this.filename = filename;  
    }  
  
    // play the MP3 file  
    public void play() {  
        try {  
            FileInputStream fis     = new FileInputStream(filename);  
            BufferedInputStream bis = new BufferedInputStream(fis);  
            player = new Player(bis);  
            player.play();  
        }  
        catch (Exception e) {  
            System.out.println("Problem playing file " + filename);  
            System.out.println(e);  
        }  
  
          
    }  
  
      
    public static void main(String[] args) {  
          
        //plays 07.mp3 file located at C drive  
        MusicPlayer mp3 = new MusicPlayer("c:/Users/Fantomas/Desktop/PAPAYA SUOUND/Team-PAPAYA-/src/resources/Tribal Tech House Beats Demo.mp3");  
        mp3.play();  
     
    }  
  
}  