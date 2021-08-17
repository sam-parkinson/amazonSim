import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The Warehouse class extends the Building and serves as the player's
 * home base, from which the player starts and to which the player returns
 * in order to refill packages.
 *
 */

public class Warehouse extends Building
{
	private Rectangle dropZone;
	
	public Warehouse(String imageLocation, int x, int y) throws SlickException 
	{
		super(imageLocation, x, y);
		this.dropZone = new Rectangle(864, 464, 80, 24);
	}
	
	@Override
	public Rectangle getDropZone() 
	{
		return this.dropZone;
	}
	
	/**
	 * The Warehouse implementation of parcels returns -1 to trigger
	 * the refilling of packages up to capacity.
	 */
	
	@Override
	public int parcels()
	{
		return -1;
	}

	@Override
	public int score() 
	{
		return 0;
	}
	
	@Override
	public void drawBuilding()
	{
		getImage().draw(x, y);
	}
	
}