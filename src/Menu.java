import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
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

public class Menu extends BasicGameState
{
	

	public Menu(int menu) {
		
	}

	private Polygon start;
	private Polygon howToPlay;
	private Polygon exit;
	private Circle mouse;
	
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException
	{
		// plays the menu background music
		GameMusic.menuMusic().setVolume(0.1f);
		GameMusic.menuMusic().loop();
		
		
		
		start = new Polygon(new float[] {472, 273, 813, 273, 813, 350, 473, 350});
		howToPlay = new Polygon(new float[] {472, 353, 813, 353, 813, 425, 472, 425});
		exit = new Polygon(new float[] {472, 429, 813, 429, 813, 513, 472, 513});
		mouse = new Circle(0, 0, 1);		
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
        g.draw(start);
        g.draw(howToPlay);
        g.draw(exit);
        g.draw(mouse);
		
						
		Image map = new Image("Sprites/startMenu.png");
		g.drawImage(map,10,10);
			
	}


	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{

		Input input = container.getInput();
				
		mouse.setCenterX(container.getInput().getMouseX());
		mouse.setCenterY(container.getInput().getMouseY());
		
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && start.contains(mouse)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			GameMusic.menuMusic().loop();			
		}
		
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && exit.contains(mouse)) {
			sbg.enterState(4);
		}
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && howToPlay.contains(mouse)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}
		
		if(container.getInput().isKeyPressed(Input.KEY_L)) 
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
	}
			
	/**
	 * The getID method represents the state ID.
	 * @return returns state's ID
	 */
	@Override
	public int getID()
	{
		return 0;
	}

}
