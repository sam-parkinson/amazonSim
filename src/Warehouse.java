import org.newdawn.slick.SlickException;

/**
 * 
 * The Warehouse class extends the Building and serves as the player's
 * home base, from which the player starts and to which the player returns
 * in order to refill packages.
 *
 */

public class Warehouse extends Building
{

	public Warehouse(String imageLocation, int x, int y) throws SlickException {
		super(imageLocation, x, y);
	}

	@Override
	public void score() {
		// TODO Auto-generated method stub
		
	}
	
}