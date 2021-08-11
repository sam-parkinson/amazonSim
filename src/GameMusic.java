import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class GameMusic {
	/*
	 * The menuMusic method play specific music when the menu opens up.
	 * @return returns menu music.
	 * */
	public static Music menuMusic() throws SlickException {
	
		return new Music ("sounds/background1.wav");
    }
	
	/*
	 * The mapMusic method play specific music when the map opens up.
	 * @return returns map music.
	 * */
	public static Music mapMusic() throws SlickException {
		
		return new Music ("sounds/background2.wav");
    }

}
