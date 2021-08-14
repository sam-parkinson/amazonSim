import org.newdawn.slick.SlickException;

/**
 * 
 * The Apartment class extends the Building abstract class to create an apartment
 * complex to which packages can be delivered
 *
 */

public class Apartment extends Building
{

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
	}

	@Override
	public void score() {
		// TODO Auto-generated method stub
		// 3x scoring
	}
	
}