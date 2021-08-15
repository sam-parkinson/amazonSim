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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * The Map class defines the main game state
 */

public class Map extends BasicGameState
{
	private Image map;
	private SpriteSheet coin;
	private Animation coinAni;
	
	/**
	 *	The buildings field contains an array consisting of all of the buildings to be
	 *	placed on the map.
	 *
	 *	There are currently 32 buildings
	 */
	private Building[] buildings = new Building[32];
	
	/**
	 * 	The roads field contains an array consisting of all of the edges of the roads on which
	 *  vehicle objects can drive.
	 */
	private Polygon[] roads = new Polygon[11];
	
	private Player player;
	private float movementX, movementY;
	
	private int time1 = 60;
	private int time2 = 25;
	
	private int score = 20;

	public Map(int map) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException
	{
		// plays the map background music
		GameMusic.mapMusic().setVolume(0.02f);
		GameMusic.mapMusic().loop();
	
		coin = new SpriteSheet("Sprites/bezoBucks.png", 32, 32);
		coinAni = new Animation(coin, 100);
		
		map = new Image("Sprites/map.png");							// 1280 by 720
		
		fillBuildings();
		
		player = new Player(300, 450, 23, 25);
		
		/*
			TODO: Potentially put road generation in its own function
		 	Below are the polygons defining the wall edges, which are used to bound the car's movement
		*/
		roads[0] = new Polygon(new float[] {0, 0, 1280, 0, 1280, 720, 0, 720});		// This is the map edge
		roads[1] = new Polygon(new float[] {1200, 640, 1280, 640, 1280, 720, 1200, 720});
		roads[2] = new Polygon(new float[] {0, 640, 1152, 640, 1152, 720, 0, 720});
		roads[3] = new Polygon(new float[] {256, 384, 256, 432, 272, 432, 272, 384});
		roads[4] = new Polygon(new float[] {256, 480, 272, 480, 272, 576, 640, 576, 640, 592, 256, 592});	
		roads[5] = new Polygon(new float[] {256, 272, 512, 272, 512, 336, 256, 336});
		roads[6] = new Polygon(new float[] {352, 384, 704, 384, 704, 464, 496, 464, 496, 416, 352, 416});
		roads[7] = new Polygon(new float[] {688, 512, 704, 512, 704, 592, 688, 592});
		roads[8] = new Polygon(new float[] {560, 272, 752, 272, 752, 224, 928, 224, 928, 272, 1040, 272, 
				1040, 384, 960, 384, 960, 336, 848, 336, 848, 416, 752, 416, 752, 336, 560, 336});
		roads[9] = new Polygon(new float[] {960, 432, 1040, 432, 1040, 592, 752, 592, 752, 464, 
				848, 464, 848, 480, 960, 480});
		roads[10] = new Polygon(new float[] {0, 592, 0, 0, 1280, 0, 1280, 592, 1088, 592, 1088, 224,
				976, 224, 976, 144, 1184, 144, 1184, 96, 928, 96, 928, 176, 704, 176, 704, 224, 144, 224, 
				144, 144, 688, 144, 688, 128, 704, 128, 704, 112, 720, 112, 720, 96, 96, 96, 96, 272, 208, 
				272, 208, 384, 46, 384, 46, 432, 208, 432, 208, 496, 32, 496, 32, 544, 208, 544, 208, 592});
		
		/*houseDropZones[0] = new Rectangle(160, 96, 32, 8);
		houseDropZones[1] = new Rectangle(224, 96, 32, 8);
		houseDropZones[2] = new Rectangle(288, 96, 32, 8);
		houseDropZones[3] = new Rectangle(352, 96, 32, 8);
		houseDropZones[4] = new Rectangle(416, 96, 32, 8);
		houseDropZones[5] = new Rectangle(480, 96, 32, 8);
		houseDropZones[6] = new Rectangle(544, 96, 32, 8);
		houseDropZones[7] = new Rectangle(608, 96, 32, 8);
		houseDropZones[8] = new Rectangle(992, 96, 32, 8);
		houseDropZones[9] = new Rectangle(1152, 96, 32, 8);
		houseDropZones[10] = new Rectangle(752, 176, 32, 8);
		houseDropZones[11] = new Rectangle(1008, 224, 32, 8);
		houseDropZones[12] = new Rectangle(672, 256, 32, 8);
		houseDropZones[13] = new Rectangle(80, 384, 32, 8);
		houseDropZones[14] = new Rectangle(160, 384, 32, 8);
		houseDropZones[15] = new Rectangle(48, 384, 4, 48);
		houseDropZones[16] = new Rectangle(80, 496, 32, 8);
		houseDropZones[17] = new Rectangle(160, 496, 32, 8);
		houseDropZones[18] = new Rectangle(48, 592, 32, 8);
		houseDropZones[19] = new Rectangle(128, 592, 32, 8);
			
		storeDropZones[0] = new Rectangle(96, 96, 18, 8);
		storeDropZones[1] = new Rectangle(416, 416, 64, 56);
		storeDropZones[2] = new Rectangle(272, 512, 64, 64);
		storeDropZones[3] = new Rectangle(368, 512, 64, 64);
		storeDropZones[4] = new Rectangle(464, 512, 64, 64);
		storeDropZones[5] = new Rectangle(560, 512, 64, 64);
		
		aptDropZones[0] = new Rectangle(96, 208, 8, 64);
		aptDropZones[1] = new Rectangle(272, 336, 32, 8);
		aptDropZones[2] = new Rectangle(336, 336, 32, 8);
		aptDropZones[3] = new Rectangle(400, 336, 32, 8);
		aptDropZones[4] = new Rectangle(464, 336, 32, 8);
		aptDropZones[5] = new Rectangle(576, 384, 32, 8);
		aptDropZones[6] = new Rectangle(640, 384, 32, 8);
		aptDropZones[7] = new Rectangle(704, 384, 32, 8);*/
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
			g.draw(buildings[i].getHitbox());
			g.draw(buildings[i].getDropZone());
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
		
		// Draw the timer and score
		g.drawString(timer(container, arg1) + "", 600, 10);
		coinAni.draw(650, 5);
		g.drawString(": " + score, 685, 10);	
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{  

		if (container.getInput().isKeyPressed(Input.KEY_B)) 
		{
			sbg.enterState(1);
		}
		if(container.getInput().isKeyPressed(Input.KEY_ENTER)) 
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
					
		if(container.getInput().isKeyDown(Input.KEY_D))
			movementX = 4f;

		else if(container.getInput().isKeyDown(Input.KEY_A))
			movementX = -4f;
		
		else
			movementX = 0;
		
		player.setX(movementX);
		
		if(collision())
			player.setX(-movementX);
			
		if(container.getInput().isKeyDown(Input.KEY_W))
			movementY = -4f;

		else if(container.getInput().isKeyDown(Input.KEY_S))
			movementY = 4f;
		
		else
			movementY = 0;
		
		player.setY(movementY);
		
		if(collision())
			player.setY(-movementY);
		
		dropPackage(container);	
	}
	
	@Override
	public int getID()
	{
		return 1;
	}
	
	/**
	 * 
	 * @param container
	 * @param sbg
	 * @return The time remaining in the game
	 * @throws SlickException
	 */
	
	private int timer(GameContainer container, StateBasedGame sbg) throws SlickException
	{
		time1--;
		if(time1 == 0)
		{
			time1 = 60;
			time2--;
		}
		
		else if(time2 == 0) 
		{
			sbg.enterState(2);		
			GameMusic.mapMusic().pause();
		}
		       	
		return time2;
	}
	
	/**
	 * The collision method checks each road and building to see if the player character
	 * is contacting or overlapping with any of them.
	 * @return Whether or not the player character is colliding with a road or building
	 */
	private boolean collision() throws SlickException
	{	
		// Check to see if car colliding with edge of road
		for (int i = 0; i < roads.length; i++)
		{
			if (player.getHitbox().intersects(roads[i])) 
			{
				
				GameSounds.collisionSound().play();		
				return true;
			}
		}
		
		// Check to see if car colliding with edge of building
		for (int i = 0; i < buildings.length; i++)
		{
			if (player.getHitbox().intersects(buildings[i].getHitbox()))
			{
				GameSounds.collisionSound().play();		
				return true;
			}
		}
		
		return false;	
	}
	
	/**
	 * The dropPackage function checks to see if the player is in a drop zone and pressing space,
	 * then delivers packages and scores them accordingly.
	 * @param container The container in which the game is hosted.
	 * @throws SlickException
	 */
	
	// TODO: Update this function to be more feature-rich
	
	private void dropPackage(GameContainer container) throws SlickException
	{
		for (int i = 0; i < buildings.length; i++)
			if (player.getHitbox().intersects(buildings[i].getDropZone()) && container.getInput().isKeyPressed(Input.KEY_SPACE))
			{
				score += buildings[i].score();
			}
	}
	
	/**
	 * The fillBuildings method populates the buildings array.
	 * @throws SlickException 
	 */
	
	private void fillBuildings() throws SlickException 
	{
		int i = 0;
		int width, height;
		
		width = 160;
		height = 32;
		for (int j = 0; j < 8; j++)
		{
			buildings[i] = new House("Sprites/buildings/HOUSE.png", width, height);
			width += 64;
			i++;
		}
		
		width = 960;
		for (int j = 0; j < 4; j++)
		{
			buildings[i] = new House("Sprites/buildings/HOUSE.png", width, height);
			width += 64;
			i++;
		}
		
		width = 80;
		height = 320;
		for (int j = 0; j < 4; j++)
		{
			buildings[i] = new House("Sprites/buildings/HOUSE.png", width, height);			
			width = (j) % 2 == 0 ? width + 64 : width - 64;
			height = (j) % 2 != 0 ? height + 112 : height;
			i++;
		}
		
		width = 272;
		height = 272;
		for (int j = 0; j < 7; j++)
		{
			buildings[i] = new Apartment("Sprites/buildings/apartment.png", width, height);
			width = j == 3 ? width + 112 : width + 64;
			i++;
		}
		
		width = 272;
		height = 520;
		for (int j = 0; j < 4; j++)
		{
			buildings[i] = new Store("Sprites/buildings/shoppe.png", width, height);
			width += 96;
			i++;
		}
		
		buildings[i] = new Store("Sprites/buildings/shoppe.png", 64, 40);
		i++;
		
		buildings[i] = new Store("Sprites/buildings/shoppe.png", 416, 408);
		i++;
		
		buildings[i] = new Apartment("Sprites/buildings/apartment.png", 64, 208);
		i++;
		
		buildings[i] = new House("Sprites/buildings/HOUSE.png", 752, 112);
		i++;
		
		buildings[i] = new House("Sprites/buildings/HOUSE.png", 1008, 160);

	}
}
