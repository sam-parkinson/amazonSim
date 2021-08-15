import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The House class extends the Building abstract class to create a house to which
 * packages can be delivered.
 *
 */

public class House extends Building
{
	private static final int DROP_HEIGHT = 8;
	private Rectangle dropZone;
	
	/**
	 * The House constructor extends the superclass constructor and creates the drop zone
	 * for the House.
	 * @param imageLocation The location where the image for the House is stored
	 * @param x The x coordinate of the top left corner of the House
	 * @param y The y coordinate of the top left corner of the House
	 * @throws SlickException
	 */
	
	public House(String imageLocation, int x, int y) throws SlickException 
	{
		super(imageLocation, x, y);
		this.dropZone = new Rectangle(x, (y + getHitbox().getHeight()), (getHitbox().getWidth()), DROP_HEIGHT);
	}
	
	/**
	 * The getDropZone method
	 * @return The delivery radius for the house.
	 */

	@Override
	public Rectangle getDropZone() {
		return this.dropZone;
	}
	
	@Override
	public int score() {
		// TODO Auto-generated method stub
		return 1;
	}

}