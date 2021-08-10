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



public class GameOver extends BasicGameState
{

	private Sound sound;

	public GameOver(int menu) {
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		sound = new Sound("sounds/gover.wav");
		
		       }

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
      g.drawString("Game Over!", 600, 340);
	}
		

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException
	{
//		if(container.getInput().isKeyPressed(Input.KEY_S))
//			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
//		
//		else if(container.getInput().isKeyPressed(Input.KEY_E))
//			sbg.enterState(3);
		if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sound.play();
		}
	}

	@Override
	public int getID()
	{
		return 2;
	}

}
