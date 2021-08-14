import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The Vehicle abstract class implements a moving, animated vehicle that
 * must be extended by a subclass which provides more concrete details
 * about the animation of the vehicle.
 *
 */

public abstract class Vehicle
{
	private Rectangle hitbox;
	
	/**
	 * The 4-arg constructor
	 * @param x The starting x coordinate of the top left corner of the vehicle
	 * @param y The starting y coordinate of the top left corner of the vehicle
	 * @param width The width of the vehicle's hitbox and sprite
	 * @param height The height of the vehicle's hitbox and sprite
	 */
	
	public Vehicle(int x, int y, int width, int height)
	{
		hitbox = new Rectangle(x, y, width, height);
	}
	
	/**
	 * The getX method for the Vehicle calls the Slick method for getX on the
	 * private hitbox
	 * @return The current x coordinate of the top left corner of the vehicle
	 */
	
	public float getX()
	{
		return hitbox.getX();
	}
	
	/**
	 * The getY method for the Vehicle calls the Slick method for getX on the
	 * private hitbox
	 * @return The current y coordinate of the top left corner of the vehicle
	 */
	
	public float getY()
	{
		return hitbox.getY();
	}
	
	/**
	 * The setX method moves the x coordinate of the Vehicle's hitbox by the specified amount
	 * @param movement The amount by which the Vehicle moves in the x axis
	 */
	
	public void setX(float movement)
	{
		hitbox.setX(getX() + movement);
	}
	
	/**
	 * The setY method moves the y coordinate of the Vehicle's hitbox by the specified amount
	 * @param movement The amount by which the Vehicle moves in the y axis
	 */
	
	public void setY(float movement)
	{
		hitbox.setY(getY() + movement);
	}
	
	/**
	 * The getHitbox method
	 * @return The Vehicle's hitbox
	 */
	
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	public abstract Animation sprite(GameContainer container);
	
	public abstract void updateAnimation(long delta);
}