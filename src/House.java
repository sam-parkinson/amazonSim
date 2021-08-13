import org.newdawn.slick.SlickException;

/**
 * 
 * The House class extends the Building abstract class to create a house to which
 * packages can be delivered.
 *
 */

public class House extends Building
{
	
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
	
}