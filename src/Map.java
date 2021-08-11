import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

/**
 * The Map class defines the main game state
 */

public class Map extends BasicGameState
{
	private Image map = null;
	
	/**
	 *	The buildings field contains an array consisting of all of the buildings to be
	 *	placed on the map.
	 *
	 *	There are currently 22 buildings
	 */
	private Building[] buildings = new Building[3];
	
	/**
	 * 	The roads field contains an array consisting of all of the edges of the roads on which
	 *  vehicle objects can drive.
	 */
	private Polygon[] roads = new Polygon[12];
	
	private Player player;
	private Rectangle deliveryZone = null;
	private float movementX, movementY;


	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException
	{
		
		GameMusic.mapMusic().setVolume(0.1f);
		GameMusic.mapMusic().loop();
		
		
		map = new Image("Sprites/map.png");							// 1280 by 720
		
		// TODO: Put building generation in its own function called here
		
		// running total of buildings: 21
		// hitboxes are 32 pixels away from border of buildings
		buildings[0] = new House("Sprites/buildings/HOUSE.png", 368, 272);
		buildings[1] = new House("Sprites/buildings/HOUSE.png", 736, 128);
		buildings[2] = new Store("Sprites/buildings/Shoppe.png", 128, 498);  // use this height for top of lowest horizontal road
		
		player = new Player(475, 200, 23, 25);
		
		/*
		 	Below are the polygons defining the wall edges, which are used to bound the car's movement
		*/
		roads[0] = new Polygon(new float[]{0, 0, 1280, 0, 1280, 720, 0, 720});		// This is the map edge
		roads[1] = new Polygon(new float[] {118, 124, 471, 124, 471, 339, 273, 339, 273, 396, 471, 396, 471, 565, 118, 565});
		roads[2] = new Polygon(new float[] {0, 0, 62, 0, 62, 68, 0, 68});
		roads[3] = new Polygon(new float[] {117, 0, 117, 68, 527, 68, 527, 196, 1076, 196, 1076, 0});
		roads[4] = new Polygon(new float[] {1132, 0, 1132, 70, 1280, 70, 1280, 0});
		roads[5] = new Polygon(new float[] {0, 124, 61, 124, 61, 565, 0, 565});
		roads[6] = new Polygon(new float[] {528, 254, 597, 254, 597, 565, 528, 565});
		roads[7] = new Polygon(new float[] {654, 252, 1075, 252, 1075, 565, 900, 565, 900, 403, 843, 403, 843, 565, 654, 565});
		roads[8] = new Polygon(new float[] {1132, 127, 1280, 127, 1280, 565, 1132, 565});
		roads[9] = new Polygon(new float[] {0, 622, 61, 622, 61, 720, 0, 720});
		roads[10] = new Polygon(new float[] {118, 622, 1075, 622, 1075, 720, 118, 720});
		roads[11] = new Polygon(new float[] {1132, 622, 1280, 622, 1280, 720, 1132, 720});
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException
	{
		// Draw the roads
		for (int i = 0; i < roads.length; i++)
		{
			g.draw(roads[i]);
		}
		
		// Draw the outlines of the buildings
		for (int i = 0; i < buildings.length; i++)
		{
			g.draw(buildings[i].getZone());
			g.draw(buildings[i].getHitbox());
		}
		
		// Draw the player and map, and animate the player
		g.draw(player.getHitbox());
		map.draw();
		player.sprite(container).draw(player.getX(), player.getY());
		
		// Draw the images associated with the buildings
		for (int i = 0; i < buildings.length; i++)
		{
			buildings[i].drawBuilding();
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		
		if(container.getInput().isKeyDown(Input.KEY_D))
			movementX = .25f;

		else if(container.getInput().isKeyDown(Input.KEY_A))
			movementX = -.25f;
		
		else
			movementX = 0;
		
		player.setX(movementX);
		
		if(collision())
		{
			player.setX(-movementX);
		}
		
		
		if(container.getInput().isKeyDown(Input.KEY_W))
			movementY = -.25f;

		else if(container.getInput().isKeyDown(Input.KEY_S))
			movementY = .25f;
		
		else
			movementY = 0;
		
		player.setY(movementY);
		
		if(collision())
		{
			player.setY(-movementY);
		}
		
		if(container.getInput().isKeyPressed(Input.KEY_SPACE))
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition()); 
		
	}
	
	/**
	 * The collision method checks each road and building to see if the player character
	 * is contacting or overlapping with any of them.
	 * @return Whether or not the player character is colliding with a road or building
	 */
	
	{	
		// Check to see if car colliding with edge of road
		for (int i = 0; i < roads.length; i++)
		{
			if (player.getHitbox().intersects(roads[i])) {
				
				GameSounds.collisionSound().play();		
				
				return true;}
		}
		
		for (int i = 0; i < buildings.length; i++)
		{
			if (player.getHitbox().intersects(buildings[i].getHitbox()))
				return true;
		}
		
		return false;
	
		
	}

	@Override
	public int getID()
	{
		return 1;
	}
}
