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
	
	/**
	 * The Warehouse constructor calls the superclass constructor then hard-codes
	 * the drop zone to be in a set location in front of the warehouse.
	 * @param imageLocation The location where the sprite is stored
	 * @param x	The x location of the upper left corner of the Warehouse
	 * @param y The y location of the upper left corner of the Warehouse
	 * @throws SlickException
	 */
	
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
	 * @return The -1 indicating that this is the warehouse
	 */
	
	@Override
	public int parcels()
	{
		return -1;
	}
	
	/**
	 * The score function
	 * @return The score modifier for the building type
	 */

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