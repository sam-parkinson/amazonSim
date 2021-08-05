import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Building
{
	// how to implement this?
	private enum Status {INACTIVE, NEEDED, PROGRESS, COMPLETED} // potentially expand with statuses knowing their images?
	
	protected int x;					// the x position of the top left corner;
	protected int y;					// the y position of the top left corner;
	private Image image;			// all buildings must have a picture indicating which building they are
	private int deliveryTime;		// how long it takes for a package to be delivered (what unit?)
	private int multiplier;			// used in score function, how much score is multiplied
	private int packagesDelivered;	// how many packages are dropped off at once
	private Status status;	// whether or not the building wants a package
	
	/**
	 * The Building constructor takes the location of the image depicting the building and creates
	 * a new building set by default to inactive for delivery purposes
	 * 
	 * @param imageLocation
	 * @throws SlickException
	 */
	
	public Building(String imageLocation, int x, int y) throws SlickException
	{
		this.x = x;
		this.y = y;
		this.image = new Image(imageLocation);
		this.status = Status.INACTIVE;
	}
	
	public void DrawBuilding()
	{
		image.draw(x,y);
	}
	
	/**
		The UpdateDeliveryStatus method -- do I want one of these for all updates
		or one for checking complete and one for randomly turning certain buildings
		"on" -- making them eligible delivery targets?
	 */
	
	public void UpdateDeliveryStatus(Status newStatus)
	{
		// TODO: write this function
	}
			
	public abstract void Score();
	
	// all buildings must be able to create themselves?
	
}