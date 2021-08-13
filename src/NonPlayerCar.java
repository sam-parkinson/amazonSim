import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;

/**
 * 
 * The NonPlayerCar class creates NPC vehicles that behave according to preset rules
 * and are capable of serving as obstacles to the player's progress
 *
 */

public class NonPlayerCar extends Vehicle
{
	// however track gets represented
	
	public NonPlayerCar(int x, int y, int height, int width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Animation sprite(GameContainer container) {
		// TODO Add animations for moving car
		return null;
	}

	@Override
	public void updateAnimation(long delta) {
		// TODO Auto-generated method stub
		
	}
	
	// contains own function to move self around?
	
	/*
	 * This function knows what the track is and whether or not the NPC is colliding
	 * with the player car, if it's not colliding it moves the car along the track
	 * 
	 * Handful of if/else statements about where it should turn
	 */
	
}