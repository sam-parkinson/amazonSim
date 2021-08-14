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
	
	private SpriteSheet coin;
	private Animation coinAni;
	
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
	private Polygon[] roads = new Polygon[7];
	
	private Rectangle[] houseDropZones = new Rectangle[20];
	private Rectangle[] storeDropZones = new Rectangle[6];
	private Rectangle[] aptDropZones = new Rectangle[8];
	
	private Player player;
	private float movementX, movementY;
	
	private int time1 = 60;
	private int time2 = 10;
	
	private int score = 20;
	
	private boolean inHouseZone;
	private boolean inStoreZone;
	private boolean inAptZone;

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException
	{
		
		GameMusic.mapMusic().setVolume(0.02f);
		GameMusic.mapMusic().loop();
		
		coin = new SpriteSheet("Sprites/bezoBucks.png", 32, 32);
		coinAni = new Animation(coin, 100);
		
		map = new Image("Sprites/map2.png");							// 1280 by 720
		
		// TODO: Put building generation in its own function called here
		
		// running total of buildings: 21
		// hitboxes are 32 pixels away from border of buildings
		buildings[0] = new House("Sprites/buildings/HOUSE.png", 368, 272);
		buildings[1] = new House("Sprites/buildings/HOUSE.png", 736, 128);
		buildings[2] = new Store("Sprites/buildings/Shoppe.png", 128, 498);  // use this height for top of lowest horizontal road
		
		player = new Player(300, 450, 23, 25);
		
		/*
			TODO: Potentially put road generation in its own function
		 	Below are the polygons defining the wall edges, which are used to bound the car's movement
		*/
		roads[0] = new Polygon(new float[] {0, 0, 1280, 0, 1280, 720, 0, 720});		// This is the map edge
		roads[1] = new Polygon(new float[] {1200, 640, 1280, 640, 1280, 720, 1200, 720});
		roads[2] = new Polygon(new float[] {0, 640, 1152, 640, 1152, 720, 0, 720});
		roads[3] = new Polygon(new float[] {256, 384, 511, 384, 511, 432, 703, 432, 703, 591, 688, 591, 688, 463, 496, 463, 496, 
				415, 271, 415, 271, 577, 655, 576, 655, 591, 256, 592});
		roads[4] = new Polygon(new float[] {256, 272, 511, 272, 511, 335, 256, 335});
		roads[5] = new Polygon(new float[] {560, 304, 768, 304, 768, 224, 927, 224, 927, 272, 1039, 272, 1039, 383, 960, 383, 960, 
				351, 944, 351, 944, 335, 863, 335, 863, 351, 847, 351, 847, 464, 863, 464, 863, 480, 944, 480, 944, 464, 960, 464, 
				960, 432, 1039, 432, 1039, 591, 752, 591, 752, 383, 560, 383});
		roads[6] = new Polygon(new float[] {0, 591, 0, 0, 1280, 0, 1280, 591, 1088, 591, 1088, 223, 976, 223, 976, 144, 1184, 144, 1184, 
				95, 927, 95, 927, 175, 719, 175, 719, 255, 560, 255, 560, 223, 144, 223, 144, 144, 688, 144, 688, 128, 704, 128, 
				704, 112, 720, 112, 720, 95, 95, 95, 95, 272, 207, 272, 207, 383, 47, 383, 47, 432, 207, 432, 207, 495, 31, 495, 
				31, 544, 207, 544, 207, 591});
		
		
		/**houseDropZones[0] = new Rectangle(160, 96, 32, 8);
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
		
		g.drawString(timer(container, arg1) + "", 600, 10);
		
		coinAni.draw(650, 5);
		
		g.drawString(": " + score, 685, 10);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		
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
		
		
		/**if(inHouseDropZone(container))
			house.score();
				
		if(inStoreDropZone(container))
			store.score();
		
		if(inAptDropZone(container))
			apt.score();*/
	}
	
	public int timer(GameContainer container, StateBasedGame sbg)
	{
		time1--;
		if(time1 == 0)
		{
			time1 = 60;
			time2--;
		}
		
		if(time2 == 0)
			sbg.enterState(2);
		
		return time2;
	}
	
	/**
	 * The collision method checks each road and building to see if the player character
	 * is contacting or overlapping with any of them.
	 * @return Whether or not the player character is colliding with a road or building
	 */
	public boolean collision() throws SlickException
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
	
	
	/**public boolean inHouseDropZone(GameContainer container) throws SlickException
	{
		for (int i = 0; i < houseDropZones.length; i++)
			if (player.getHitbox().intersects(houseDropZones[i]) && container.getInput().isKeyPressed(Input.KEY_SPACE))
			{
				inHouseZone = true;
				house[i].score();
			}
		return inHouseZone;
	}
	
	
	public boolean inStoreDropZone(GameContainer container) throws SlickException
	{
		for (int i = 0; i < storeDropZones.length; i++)
			if (player.getHitbox().intersects(storeDropZones[i]) && container.getInput().isKeyPressed(Input.KEY_SPACE))
				inStoreZone = true;
		
		return inStoreZone;
	}
	
	
	public boolean inAptDropZone(GameContainer container) throws SlickException
	{
		for (int i = 0; i < aptDropZones.length; i++)
			if (player.getHitbox().intersects(aptDropZones[i]) && container.getInput().isKeyPressed(Input.KEY_SPACE))
				inAptZone = true;
		
		return inAptZone;
	}
*/
	@Override
	public int getID()
	{
		return 1;
	}
}
