import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
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
	private Music music;
	private Sound exitSound;


	public Menu(int menu) {
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException
	{
		
		music = new Music("sounds/background1.wav");
		music.setVolume(0.2f);
		music.loop();
		exitSound = new Sound("sounds/exit.wav");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
        g.drawString("Press S for start\nPress Enter for exit", 50, 50);
		
		Image map = new Image("Sprites/startMenu.png");
		g.drawImage(map,10,10);
		
	}


	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{

		if(container.getInput().isKeyPressed(Input.KEY_S)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			music.loop();
			
		}
		
		
		else if(container.getInput().isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(4);
		   		   
		}
		
	}

	@Override
	public int getID()
	{
		return 0;
	}

}
