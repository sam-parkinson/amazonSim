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
		AppGameContainer app = new AppGameContainer(new GameStateSelector("Amazon Simulator"));
		
		app.setDisplayMode(1280, 720, false);
		app.setAlwaysRender(true);
		
		app.start();
	}
	
	/**
	 * The initStatesList function tracks all existing game states. States are switched between based on
	 * conditions specified within the states.
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		this.addState(new Map());
		this.addState(new Menu());
		// TODO: add update screen state
		// TODO: make menu screen first thing that shows up
	}

}
