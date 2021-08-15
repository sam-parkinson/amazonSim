import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The Apartment class extends the Building abstract class to create an apartment
 * complex to which packages can be delivered
 *
 */

public class Apartment extends Building
{

	private static final int DROP_WIDTH = 8;
	private static final int DROP_HEIGHT = 8;
	private Rectangle dropZone;
	/**
	 * The Apartment constructor extends the superclass constructor
	 * @param imageLocation The location where the image for the Apartment is stored
	 * @param x The x coordinate of the top left corner of the Apartment
	 * @param y The y coordinate of the top left corner of the Apartment
	 * @throws SlickException
	 */
	
	public Apartment(String imageLocation, int x, int y) throws SlickException 
	{
		super(imageLocation, x, y);
		this.dropZone = new Rectangle((x - DROP_WIDTH), y - (DROP_HEIGHT),
				(getHitbox().getWidth() + (DROP_WIDTH * 2)), (getHitbox().getHeight() + (DROP_HEIGHT * 2)));
	}
	
	@Override
	public Rectangle getDropZone() {
		return this.dropZone;
	}

	@Override
	public void score() {
		// TODO Auto-generated method stub
		// 3x scoring
	}
	
}