import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Building
{
	// how to implement this?
	private enum Status {INACTIVE, NEEDED, PROGRESS, COMPLETED} // potentially expand with statuses knowing their images?
	
	protected int x;					// the x position of the top left corner;
	protected int y;					// the y position of the top left corner;
	private Image image;			// all buildings must have a picture indicating which building they are
	
	/*
	 * Do the below characteristics need to be added? If so, how? Static variables in subclasses?
	 */
	
	private int deliveryTime;		// how long it takes for a package to be delivered (what unit?)
	private int multiplier;			// used in score function, how much score is multiplied
	private int packagesDelivered;	// how many packages are dropped off at once
	private Status status;	// whether or not the building wants a package
	
	/**
	 * The Building constructor takes the location of the image depicting the building and creates
	 * a new building set by default to inactive for delivery purposes
	 * 
	 * @param imageLocation The location where the image for the building is stored
	 * @throws SlickException
	 */
	
	public Building(String imageLocation, int x, int y) throws SlickException
	{
		this.x = x;
		this.y = y;
		this.image = new Image(imageLocation);
		this.status = Status.INACTIVE;
	}
	
	/**
	 * The drawBuilding method calls the draw method of the Building's Image provided by the Slick
	 * library using the x and y coordinates provided when the Building was defined.
	 */
	
	public void drawBuilding()
	{
		image.draw(x,y);
	}
	
	/**
		The UpdateDeliveryStatus method -- do I want one of these for all updates
		or one for checking complete and one for randomly turning certain buildings
		"on" -- making them eligible delivery targets?
		// TODO: fix this documentation
	 */
	
	public void updateDeliveryStatus(Status newStatus)
	{
		// TODO: write this function
	}
	
	
	/**
	 * The score method must be implemented by any subclass of a building, what distinguishes building
	 * subclasses is the differences that occur when package delivery is completed.
	 */
	public abstract void score();
	
	// all buildings must be able to create themselves?
	
}