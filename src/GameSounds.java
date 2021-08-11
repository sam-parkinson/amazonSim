import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameSounds {

/*
 * The collisionSound method plays specific sound when collision occurs.
 * @return returns collision sound.
 * */
	public static  Sound collisionSound() throws SlickException {
		
		return new Sound("sounds/collision.wav");
	}
	
	/*
	 * The packagedroppedSound method plays specific sound when a package will be dropped.
	 * @return returns sound when the package dropped.
	 * */	
	public static  Sound packagedroppedSound() throws SlickException {
		
		return new Sound("sounds/packagedropped.wav");
	}
	
	/*
	 * The pointRewardSound method plays specific sound when a point earned.
	 * @return returns sound when a point earned.
	 * */
	public static  Sound pointRewardSound() throws SlickException {
		
		return new Sound("sounds/pointReward.wav");
	}
	
	/*
	 * The gameOverSound method plays specific sound when the game is over.
	 * @return returns sound when the game is over.
	 * */
	public static  Sound gameOverSound() throws SlickException {
		
		return new Sound("sounds/gover.wav");
	}
	
	
}
