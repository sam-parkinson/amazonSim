import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * The Building abstract class defines a building on the map and specifies methods to be implemented by its
 * child classes.
 *
 */

public abstract class Building
{
	/**
	 * 
	 * The Status enum lists out the possible delivery statuses of a house: inactive, delivery needed, delivery
	 * in progress, and delivery recently completed.
	 *
	 */
	
	private enum Status
	{
		INACTIVE ("Sprites/flags/inactiveFlag.png"), 
		NEEDED ("Sprites/flags/neededFlag.png"), 
		PROGRESS ("Sprites/flags/progressFlag.png"), 
		COMPLETED ("Sprites/flags/completeFlag.png");
		
		private Image flag;
		Status(String imageLocation)
		{
			try {
				this.flag = new Image(imageLocation);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected int x;				// the x position of the top left corner;
	protected int y;				// the y position of the top left corner;
	private Rectangle hitbox;
	private Image image;			// all buildings must have a picture indicating which building they are
	private Status status;			// whether or not the building wants a package
	
	/**
	 * The Building constructor takes the location of the image depicting the building and the 
	 * coordinates of the top left corner of the object, and creates a new Building as well
	 * as its hitbox and the overlapping image
	 * 
	 * @param imageLocation The location where the image for the building is stored
	 * @param x The x coordinate of the top left corner of the Building
	 * @param y The y coordinate of the top left corner of the Building
	 * @throws SlickException
	 */
	
	public Building(String imageLocation, int x, int y) throws SlickException
	{
		this.x = x;
		this.y = y;
		this.image = new Image(imageLocation);
		this.hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());
		this.status = Status.INACTIVE;
	}
	
	/**
	 * The getX method
	 * @return The x coordinate of the top-left corner of the Building
	 */
	
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * The getY method
	 * @return The y coordinate of the top-left corner of the Building
	 */
	
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * The getImage method
	 * @return The image used by the Building
	 */
	
	public Image getImage()
	{
		return this.image;
	}
	
	/**
	 * The getHitbox method
	 * @return The hitbox for the building, used to detect collisions
	 */
	
	public Rectangle getHitbox()
	{
		return this.hitbox;
	}
	
	/**
	 * The isInactive function
	 * @return True if a building is inactive, otherwise False
	 */
	
	public boolean isInactive()
	{
		return status == Status.INACTIVE;
	}
	
	/**
	 * The awaitingDelivery function
	 * @return True if a building needs a package, otherwise False
	 */
	
	public boolean awaitingDelivery()
	{
		return status == Status.NEEDED;
	}
	
	/**
	 * The delivered function
	 * @return True if a building recently received a package, else False
	 */
	
	public boolean delivered()
	{
		return status == Status.COMPLETED;
	}
	
	/**
	* The setDeliveryStatus method updates the delivery status of the building
	* @param int The incoming delivery status of the building
	*/

	public void setDeliveryStatus(int newStatus)
	{
		switch (newStatus)
		{
			case 0:
				this.status = Status.INACTIVE;
				break;
			case 1:
				this.status = Status.NEEDED;
				break;
			case 2:
				this.status = Status.PROGRESS;
				break;
			case 3:
				this.status = Status.COMPLETED;
				break;
		}	
	}
	
	/**
	 * The drawBuilding method calls the draw method of the Building's Image provided by the Slick
	 * library using the x and y coordinates provided when the Building was defined. It also draws
	 * the flag representing the building's current delivery status above the building.
	 */
	
	public void drawBuilding()
	{
		image.draw(x, y);
		status.flag.draw(x - 16 + (image.getWidth()/2), y - 32);
	}
	
	/**
	 * The getDropZone method must be implemented by any subclass of a building, and returns
	 * a rectangle defining the area in which packages can be delivered.
	 * @return The building's drop zone
	 */
	
	public abstract Rectangle getDropZone();
	
	/**
	 * The packages method returns the number of packages to be withdrawn
	 * @return The package modifier
	 */
	
	public abstract int parcels();
	
	/**
	 * The score method must be implemented by any subclass of a building, part of what distinguishes 
	 * building subclasses is the differences that occur when package delivery is completed.
	 * @return The score modifier.
	 */
	
	public abstract int score();
	
}