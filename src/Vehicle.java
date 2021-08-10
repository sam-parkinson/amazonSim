import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The Vehicle class implements a moving, animated vehicle
 *
 */

public abstract class Vehicle
{
	private Rectangle hitbox;
	
	public Vehicle(int x, int y, int height, int width)
	{
		hitbox = new Rectangle(x, y, height, width);
	}
	
	public float getX()
	{
		return hitbox.getX();
	}
	
	public float getY()
	{
		return hitbox.getY();
	}
	
	public void setX(float movement)
	{
		hitbox.setX(getX() + movement);
	}
	
	public void setY(float movement)
	{
		hitbox.setY(getY() + movement);
	}
	
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	public abstract Animation sprite(GameContainer container);
	
	public abstract void updateAnimation(long delta);
}