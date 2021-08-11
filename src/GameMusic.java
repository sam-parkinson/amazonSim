import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class GameMusic {

	public static Music menuMusic() throws SlickException {
	
		return new Music ("sounds/background1.wav");
    }
	

	public static Music mapMusic() throws SlickException {
		
		return new Music ("sounds/background2.wav");
    }

}
