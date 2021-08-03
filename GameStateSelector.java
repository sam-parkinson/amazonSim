import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateSelector extends StateBasedGame
{

	public GameStateSelector(String title)
	{
		super(title);
	}

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new GameStateSelector("Game State Selector"));
		
		app.setDisplayMode(1280, 720, false);
		app.setAlwaysRender(true);
		
		app.start();
	}
	
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		this.addState(new Map());
		this.addState(new Menu());
	}

}
