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
	
	/*
	 * Do the below characteristics need to be added? If so, how? Static variables in subclasses?
	 */
	
	private int deliveryTime;		// how long it takes for a package to be delivered (what unit?)
	private int multiplier;			// used in score function, how much score is multiplied
	private int packagesDelivered;	// how many packages are dropped off at once
	
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
	 * The getHitbox method
	 * @return The hitbox for the building, used to detect collisions
	 */
	
	public Rectangle getHitbox()
	{
		return this.hitbox;
	}
	
	public boolean isInactive()
	{
		return status == Status.INACTIVE;
	}
	
	public boolean awaitingDelivery()
	{
		return status == Status.NEEDED;
	}
	
	/**
	* The setDeliveryStatus method updates the delivery status of the building
	* @param Status the incoming delivery status of the building
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
		// TODO: draw flag in proper location
		status.flag.draw(x + (image.getWidth()/2), y - 32);
	}
	
	/**
	 * The getDropZone method must be implemented by any subclass of a building, and returns
	 * a rectangle defining the area in which packages can be delivered.
	 * @return
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