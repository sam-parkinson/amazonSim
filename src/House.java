import org.newdawn.slick.SlickException;

/**
 * 
 * The House class extends the Building abstract class to create a house to which
 * packages can be delivered.
 *
 */

public class House extends Building
{
	
	/**
	 * The House constructor extends the superclass constructor
	 * @param imageLocation The location where the image for the House is stored
	 * @param x The x coordinate of the top left corner of the House
	 * @param y The y coordinate of the top left corner of the House
	 * @throws SlickException
	 */
	
	public House(String imageLocation, int x, int y) throws SlickException 
	{
		super(imageLocation, x, y);
	}
	
	// house is 52h, 32w (64h, 32w including whitespace)

	@Override
	public void score() {
		// TODO Auto-generated method stub
		// Standard scoring
	}
	
	// get and set drop zone function added here
	// generate drop zones in building
	
}