import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameSounds {


	public static  Sound collisionSound() throws SlickException {
		
		return new Sound("sounds/collision.wav");
	}
	
	
	public static  Sound packagedroppedSound() throws SlickException {
		
		return new Sound("sounds/packagedropped.wav");
	}
	
	
	public static  Sound pointRewardSound() throws SlickException {
		
		return new Sound("sounds/pointReward.wav");
	}
	
	
	public static  Sound gameOverSound() throws SlickException {
		
		return new Sound("sounds/gover.wav");
	}
	
	
}
