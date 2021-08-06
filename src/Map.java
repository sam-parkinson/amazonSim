import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

/**
 * The Map class defines the main game state
 */

public class Map extends BasicGameState
{
	
	
	private Image map = null;
	private Image car = null;
	private Building[] buildings = new Building[5];


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		map = new Image("Sprites/map.png");							// 1280 by 720
		car = new Image("Sprites/vehicles/car.png");
		buildings[0] = new House("Sprites/buildings/HOUSE.png", 0, 0);
		buildings[1] = new Store("Sprites/buildings/Shoppe.png", 150, 498); // use this height for top of lowest horizontal road
		buildings[2] = new Store("Sprites/buildings/Shoppe.png", 350, 200);
		buildings[3] = new House("Sprites/buildings/HOUSE.png", 960, 320);
		buildings[4] = new House("Sprites/buildings/HOUSE.png", 670, 498); // this one in a reasonable location?
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
		
		
		map.draw();
		car.draw(500, 450);
		for (int i = 0; i < buildings.length; i++)
		{
			buildings[i].DrawBuilding();
		}
		
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
