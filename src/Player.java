import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * The Player class implements the player's character, which is a vehicle
 * whose animations respond to the player inputs.
 */

public class Player extends Vehicle
{
	private SpriteSheet truckRight, truckLeft, truckUp, truckDown, 
		truckRightStill, truckLeftStill, truckUpStill, truckDownStill;
	private Animation truckRightAni, truckLeftAni, truckUpAni, truckDownAni, 
		truckRightStillAni, truckLeftStillAni, truckUpStillAni, truckDownStillAni;
	private char lastKeyPressed;
	
	/**
	 * The four-arg constructor defines the player's hitbox and the spritesheets
	 * and animations used
	 * @param x The starting x coordinate of the top left corner of the player
	 * @param y The starting y coordinate of the top left corner of the player
	 * @param width The width of the player's hitbox and sprite
	 * @param height The height of the player's hitbox and sprite
	 * @throws SlickException
	 */
	
	public Player(int x, int y, int height, int width) throws SlickException
	{
		super(x, y, height, width);
		
		truckUp = new SpriteSheet("Sprites/vehicles/truckUp.png", 20, 25);
		truckDown = new SpriteSheet("Sprites/vehicles/truckDown.png", 20, 25);
		truckLeft = new SpriteSheet("Sprites/vehicles/truckLeft.png", 23, 23);
		truckRight = new SpriteSheet("Sprites/vehicles/truckRight.png", 23, 23);
		truckUpStill = new SpriteSheet("Sprites/vehicles/truckUpStill.png", 20, 24);
		truckDownStill = new SpriteSheet("Sprites/vehicles/truckDownStill.png", 20, 24);
		truckLeftStill = new SpriteSheet("Sprites/vehicles/truckLeftStill.png", 23, 22);
		truckRightStill = new SpriteSheet("Sprites/vehicles/truckRightStill.png", 23, 22);
		
		truckUpAni = new Animation(truckUp, 150);
		truckDownAni = new Animation(truckDown, 150);
		truckLeftAni = new Animation(truckLeft, 150);
		truckRightAni = new Animation(truckRight, 150);
		truckUpStillAni = new Animation(truckUpStill, 150);
		truckDownStillAni = new Animation(truckDownStill, 150);
		truckLeftStillAni = new Animation(truckLeftStill, 150);
		truckRightStillAni = new Animation(truckRightStill, 150);
	}
	
	/**
	 * The sprite method is responsible for tracking the animation that should be displayed
	 * based on the direction key being held down or which was most recently pressed.
	 * @param container The container in which the game is hosted.
	 */
	
	@Override
	public Animation sprite(GameContainer container)
	{
		
		if (container.getInput().isKeyDown(Input.KEY_D))
		{
			lastKeyPressed = 'd';
			return truckRightAni;
		}
		else if (container.getInput().isKeyDown(Input.KEY_A))
		{
			lastKeyPressed = 'a';
			return truckLeftAni;
		}
		else if (container.getInput().isKeyDown(Input.KEY_W))
		{
			lastKeyPressed = 'w';
			return truckUpAni;
		}
		else if (container.getInput().isKeyDown(Input.KEY_S))
		{
			lastKeyPressed = 's';
			return truckDownAni;
		}
		else if(lastKeyPressed == 'd')
			return truckRightStillAni;
		
		else if(lastKeyPressed == 'a')
			return truckLeftStillAni;
		
		else if(lastKeyPressed == 'w')
			return truckUpStillAni;
		
		else if(lastKeyPressed == 's')
			return truckDownStillAni;
		
		return truckRightStillAni;
		
	}
	
	/**
	 * The updateAnimation method updates each of the vehicle animations to
	 * display the correct frame.
	 * @param delta The amount of time passed since the last update
	 */

	@Override
	public void updateAnimation(long delta) {
		truckUpAni.update(delta);
		truckDownAni.update(delta);
		truckLeftAni.update(delta);
		truckRightAni.update(delta);		
	}
}