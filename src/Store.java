import org.newdawn.slick.SlickException;

/**
 * 
 * The Store class extends the Building abstract class to create a store to which
 * packages can be delivered.
 *
 */

public class Store extends Building
{

	/**
	 * The Store constructor extends the superclass constructor
	 * @param imageLocation The location where the image for the Store is stored
	 * @param x The x coordinate of the top left corner of the Store
	 * @param y The y coordinate of the top left corner of the Store
	 * @throws SlickException
	 */
	
	public Store(String imageLocation, int x, int y) throws SlickException 
	{
		super(imageLocation, x, y);
	}

	@Override
	public void score() {
		// TODO Auto-generated method stub
		// 2x scoring
	}
	
}