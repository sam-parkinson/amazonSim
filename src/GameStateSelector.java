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
//	public static final int menu = 0; // menu state
//	public static final int map = 1; // map state
//	public static final int gameOver = 2; // game over state

	/**
	 * The one-arg constructor calls the StateBasedGame constructor
	 * @param title The game's title.
	 */
	 public GameStateSelector(String title)
		{
			super(title);
//			this.addState(new Menu(menu));
//			this.addState(new Map(map));
//			this.addState(new GameOver(gameOver));
			
			
		}

	public static void main(String[] args) throws SlickException
	{
		try {
			// create a window that will hold computer game 
		AppGameContainer app = new AppGameContainer(new GameStateSelector(title));
		
		app.setDisplayMode(1280, 720, false);
		app.setAlwaysRender(true);
		
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
//		this.getState(menu).init(container, this);
//		this.getState(map).init(container, this);
//		this.getState(gameOver).init(container, this);
//		this.enterState(menu);
		this.addState(new Menu(0));
		this.addState(new Map(1));
		this.addState(new GameOver(2));
		
		
		// TODO: add update screen state
		// TODO: make menu screen first thing that shows up
	}

}
