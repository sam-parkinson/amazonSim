import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * 
 * The Menu class is opened by default when the game is started, and allows the user to play the game,
 * exit the game, or learn more about how to play the game.
 *
 */

// TODO: Flesh out menu screen, make menu first thing that appears

public class Menu extends BasicGameState
{

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
		g.drawString("This will be the menu state", 50, 50);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException
	{
		if(container.getInput().isKeyPressed(Input.KEY_SPACE))
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID()
	{
		return 1;
	}

}
