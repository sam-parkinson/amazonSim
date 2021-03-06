import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The Store class extends the Building abstract class to create a store to which
 * packages can be delivered.
 *
 */

public class Store extends Building
{
	private static final int DROP_WIDTH = 8;
	private static final int DROP_HEIGHT = 8;
	private static final int PARCEL_MOD = 2;
	private Rectangle dropZone;
	
	/**
	 * The Store constructor extends the superclass constructor and creates the 
	 * drop zone for the Store.
	 * @param imageLocation The location where the image for the Store is stored
	 * @param x The x coordinate of the top left corner of the Store
	 * @param y The y coordinate of the top left corner of the Store
	 * @throws SlickException
	 */
	
	public Store(String imageLocation, int x, int y) throws SlickException 
	{
		super(imageLocation, x, y);
		this.dropZone = new Rectangle((x - DROP_WIDTH), y - (DROP_HEIGHT),
				(getHitbox().getWidth() + (DROP_WIDTH * 2)), (getHitbox().getHeight() + (DROP_HEIGHT * 2)));
	}
	
	/**
	 * The getDropZone function
	 * @return The delivery zone for the Store
	 */
	
	@Override
	public Rectangle getDropZone() 
	{
		return this.dropZone;
	}
	
	/**
	 * The parcels method
	 * @return The number of parcels the building type deducts
	 */
	
	public int parcels()
	{
		return PARCEL_MOD;
	}
	
	/**
	 * The score function
	 * @return The score modifier for the building type
	 */

	@Override
	public int score() 
	{
		return 4;
	}
	
}