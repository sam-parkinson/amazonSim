import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * 
 * The GameSounds class keeps track of sound effects that the game will play
 *
 */

public class GameSounds {

	/**
	 * The collisionSound method plays a specific sound when collision occurs.
	 * @return returns collision sound.
	 */
	public static  Sound collisionSound() throws SlickException {
		
		return new Sound("sounds/collision.wav");
	}
	
	/**
	 * The packagedroppedSound method plays a specific sound when a package will be dropped.
	 * @return returns sound when the package dropped.
	 */	
	public static  Sound packagedroppedSound() throws SlickException {
		
		return new Sound("sounds/packagedropped.wav");
	}
	
	/**
	 * The pointRewardSound method plays a specific sound when points are earned.
	 * @return returns sound when points are earned.
	 */
	public static  Sound pointRewardSound() throws SlickException {
		
		return new Sound("sounds/pointReward.wav");
	}
	
	/**
	 * The gameOverSound method plays a specific sound when the game is over.
	 * @return returns sound when the game is over.
	 */
	public static  Sound gameOverSound() throws SlickException {
		
		return new Sound("sounds/gover.wav");
	}
	
	
}
