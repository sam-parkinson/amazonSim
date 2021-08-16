import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * The GameStateSelector class changes the active game screen based on predefined conditions
 * between the main menu screen, the game screen, and the between-rounds screen.
 *
 */

public class GameStateSelector extends StateBasedGame
{
	public static final String title = "Amazon Simulator!";// Title for a game


	/**
	 * The one-arg constructor calls the StateBasedGame constructor
	 * @param title The game's title.
	 */
	 public GameStateSelector(String title)
		{
			super(title);
					
		}

	public static void main(String[] args) throws SlickException
	{
		try 
		{
		// create a window that will hold computer game 
		AppGameContainer app = new AppGameContainer(new GameStateSelector(title));
		
		app.setDisplayMode(1280, 720, false);
		app.setAlwaysRender(true);
		
		app.setTargetFrameRate(60);
		
		app.start();}
		catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The initStatesList function tracks all existing game states. States are switched between based on
	 * conditions specified within the states.
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{

		this.addState(new Menu(0));
     	this.addState(new Map(1));
		this.addState(new GameOver(2));
		this.addState(new HowToPlay(3)); 
		
	}

}
