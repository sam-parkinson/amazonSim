import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * 
 * The NonPlayerCar class creates NPC vehicles that behave according to preset rules
 * and are capable of serving as obstacles to the player's progress
 *
 */

public class NonPlayerCar extends Vehicle
{
	// however track gets represented
	
	private int[] coords;
	private int i = 0;
	private float movementX, movementY;
	
	
	private boolean routeFinished = false;
	
	private SpriteSheet carUp, carDown, carRight, carLeft;
	private Animation carUpAni, carDownAni, carRightAni, carLeftAni, correctAni;
	
	
	
	public NonPlayerCar(int x, int y, int height, int width, int[] coords) throws SlickException {
		super(x, y, height, width);

		this.coords = coords;

				
		carUp = new SpriteSheet("Sprites/vehicles/carUp.png", 18, 18);
		carDown = new SpriteSheet("Sprites/vehicles/carDown.png", 18, 18);
		carLeft = new SpriteSheet("Sprites/vehicles/carLeft.png", 18, 18);
		carRight = new SpriteSheet("Sprites/vehicles/carRight.png", 18, 18);
		
		carUpAni = new Animation(carUp, 150);
		carDownAni = new Animation(carDown, 150);
		carLeftAni = new Animation(carLeft, 150);
		carRightAni = new Animation(carRight, 150);
		
	}

	@Override
	public void updateAnimation(long delta) {
		carUpAni.update(delta);
		carDownAni.update(delta);
		carLeftAni.update(delta);
		carRightAni.update(delta);
		
	}
	

	public void movement()
	{	
		if(coords.length > 0)
		{
			for(int moves = coords.length / 2; moves > 0; moves--)
			{
				if(coords[i] > getX() && Map.getInAccident() == false)
					movementX = .5f;
		
				else if(coords[i] < getX() && Map.getInAccident() == false)
					movementX = -.5f;
				
				else if(Map.getInAccident())
					movementX = 0;
				
				else
					movementX = 0;
				
				setX(movementX);
				
				if(coords[i+1] > getY() && Map.getInAccident() == false)
					movementY = .5f;
				
				else if(coords[i+1] < getY() && Map.getInAccident() == false)
					movementY = -.5f;
					
				else if(Map.getInAccident())
					movementY = 0;
				
				else
					movementY = 0;
				
				setY(movementY);
				
				if(getX() == coords[i] && getY() == coords[i+1])
					if(i+2 < coords.length)
						i += 2;
					else 
						routeFinished = true;
				
			}
		}
	}
	// contains own function to move self around?
	
	public boolean routeFinished()
	{
		return routeFinished;
	}

	@Override
	public Animation sprite(GameContainer container)
	{
		if(coords[i] > getX())
			correctAni = carRightAni;

		else if(coords[i] < getX())
			correctAni = carLeftAni;
		
		else if(coords[i+1] > getY())
			correctAni = carDownAni;
		
		else if(coords[i+1] < getY())
			correctAni = carUpAni;
		return correctAni;
	}
	
	/*
	 * This function knows what the track is and whether or not the NPC is colliding
	 * with the player car, if it's not colliding it moves the car along the track
	 * 
	 * Handful of if/else statements about where it should turn
	 */
	
}
