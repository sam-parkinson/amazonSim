import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

public class Map extends BasicGameState
{
	
	
	private Image map = null;
	private Image car = null;
	private Image house = null;



	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		map = new Image("Sprites/map.png");
		car = new Image("Sprites/car.png");
		house = new Image("Sprites/HOUSE.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
		
		
		map.draw();
		car.draw(500, 450);
		house.draw(100,100);

		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException
	{
		if(container.getInput().isKeyPressed(Input.KEY_SPACE))
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID()
	{
		return 0;
	}
}
