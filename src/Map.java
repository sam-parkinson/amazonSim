import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
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
	private Image parcel;
	private SpriteSheet coin;
	private Animation coinAni;
	
	/**
	 *	The buildings field contains an array consisting of all of the buildings to be
	 *	placed on the map.
	 *
	 *	There are currently 33 buildings
	 */
	private Building[] buildings = new Building[33];
	
	/**
	 * 	The roads field contains an array consisting of all of the edges of the roads on which
	 *  vehicle objects can drive.
	 */
	private Polygon[] roads = new Polygon[11];
	
	private static Player player;
	private float movementX, movementY;
	
	private static ArrayList<NonPlayerCar> cars;
	private static NonPlayerCar npc1;
	private static NonPlayerCar npc2;
	private static NonPlayerCar npc3;
	private static NonPlayerCar npc4;
	private static NonPlayerCar npc5;
	private static NonPlayerCar npc6;
	private static NonPlayerCar npc7;
	
	private static boolean inAccident = false;
	
	private boolean startRouteBoolean;
	
	private static int time1 = 60;
	private static int time2 = 72;
	
	private static int score = 0;
	private static int parcelCapacity = 20;			// stretch goal to add upgrades means this might increase
	private static int parcels = parcelCapacity;
	
	private int activeHouses = 0;
	private final int MAX_ACTIVE_HOUSES = 12; 	// This can be set based on game balance

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
		parcel = new Image("Sprites/package.png");
		
		fillBuildings();
		
		player = new Player(876, 434, 23, 25);
		
		npc1 = new NonPlayerCar(91, 384, 18, 18, new int[] {91, 412, 208, 412, 208, 496, 154, 496});
		npc2 = new NonPlayerCar(705, 339, 18, 18, new int[] {705, 594, 667, 594, 667, 465, 442, 465});
		npc3 = new NonPlayerCar(650, 339, 18, 18, new int[] {280, 339});
		npc4 = new NonPlayerCar(91, 498, 18, 18, new int[] {91, 525, 237, 525, 237, 456, 280, 456, 280, 502});
		npc5 = new NonPlayerCar(100, 249, 18, 18, new int[] {125, 249, 125, 126, 424, 126, 424, 96});
		npc6 = new NonPlayerCar(100, 96, 18, 18, new int[] {362, 96});
		npc7 = new NonPlayerCar(1162, 96, 18, 18, new int[] {928, 96, 928, 254, 1041, 254, 1041, 592, 1280, 592});
		cars = new ArrayList<NonPlayerCar>();
		cars.add(npc1);
		cars.add(npc2);
		cars.add(npc3);
		cars.add(npc4);
		cars.add(npc5);
		cars.add(npc6);
		cars.add(npc7);
		
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
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException
	{
		g.setColor(Color.black);
		// Draw the roads
		for (int i = 0; i < roads.length; i++)
		{
			g.draw(roads[i]);
		}
		
		// Draw the outlines of the non-player cars
		if(startRouteBoolean == true)
			g.draw(cars.get(0).getHitbox());

		
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
		
		// Draw the images associated with the non-player cars
		
		if(time2 % 10 == 0 && time1 == 60)
			startRouteBoolean = true;
		
		if(startRouteBoolean == true)
			startRoute(container, g);
			
		// Draw the images associated with the buildings
		for (int i = 0; i < buildings.length; i++)
		{
			buildings[i].drawBuilding();
		}
		
		displayUserInterface(container, arg1, g);
		drawParcelMeter();
	}
	
	public void startRoute(GameContainer container, Graphics g)
	{
		cars.get(0).sprite(container).draw(cars.get(0).getX(), cars.get(0).getY());
		cars.get(0).movement();
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{  

		if (container.getInput().isKeyPressed(Input.KEY_B)) 
			sbg.enterState(1);
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
		
		carAccident();
		askForDelivery();
		dropPackage(container);	
		
		if (time2 % 5 == 0)
			resetHouses();

		for(int i = 0; i < cars.size(); i++)
		{
			if(cars.get(i).routeFinished() && cars.size() > 0)
			{
				startRouteBoolean = false;
				cars.remove(i);			
			}

		}
		
		
	}
	
	@Override
	public int getID()
	{
		return 1;
	}
	/**
	 * The reset method returns game information to its starting values
	 * @throws SlickException 
	 */
	
	public static void reset() throws SlickException
	{
		score = 0;
		time1 = 60;
		time2 = 72;
		
		parcels = parcelCapacity;
		
		player.resetVehicle(876, 434);
		
		cars.add(npc1);
		cars.add(npc2);
		cars.add(npc3);
		cars.add(npc4);
		cars.add(npc5);
		cars.add(npc6);
		cars.add(npc7);
		GameMusic.mapMusic().loop();
	}
	
	public static int getScore()
	{
		return score;
	}
	
	/**
	 * The timer method tracks the amount of time remaining in the game.
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
	 * The displayUserInterface method displays elements associated with the user interface.
	 * @param container The container in which the game is hosted
	 * @param arg1
	 * @param g 
	 * @throws SlickException
	 */
	
	private void displayUserInterface(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException
	{
		g.drawString("Time: " + timer(container, arg1), 1120, 176);
		coinAni.draw(1120, 362);
		g.drawString(": " + score, 1145, 368);
		g.drawString("Parcels: " + parcels, 40, 662);
	}
	
	private void drawParcelMeter()
	{
		int width = 168, height = 656;
		for (int i = 0; i < parcels; i++)
		{
			parcel.draw(width, height);
			width += parcel.getWidth();
		}
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
				if(time1 % 30 == 0)
					GameSounds.collisionSound().play();
				
				return true;					
			}
		}
		
		// Check to see if car colliding with edge of building
		for (int i = 0; i < buildings.length; i++)
		{
			if (player.getHitbox().intersects(buildings[i].getHitbox()))
			{
				if(time1 % 30 == 0)
					GameSounds.collisionSound().play();
				
				return true;
			}
		}
		
		return false;	
	}
	
	/**
	 * The carAccident function checks to see if the player car has
	 * crashed into another car, then plays the collision sound
	 * and deducts score accordingly.
	 * @return Whether or not the player is colliding with an NPC
	 * @throws SlickException
	 */
	
	private boolean carAccident() throws SlickException
	{
		if(player.getHitbox().intersects(cars.get(0).getHitbox()))
		{
			GameSounds.collisionSound().play();
			score = score < 10 ? 0 : score - 10;
			inAccident = true;
			player.resetVehicle(876, 434);
		}
		else
			inAccident = false;
			
		return inAccident;	
	}
	
	public static boolean getInAccident()
	{
		return inAccident;
	}
	
	/**
	 * The askForDelivery method randomly switches buildings' statuses
	 * from INACTIVE to NEEDED so that packages can be delivered to them.
	 */
	
	private void askForDelivery()
	{
		for (int i = 0; i < buildings.length; i++)
		{
			int number = (int) Math.floor(Math.random() * 1000);
			
			if (number < 20 
					&& buildings[i].isInactive()
					&& activeHouses <= MAX_ACTIVE_HOUSES)
			{
				buildings[i].setDeliveryStatus(1);
				activeHouses++;
			}
		}
	}
	
	/**
	 * The dropPackage function checks to see if the player is in a drop zone, has packages able 
	 * to be delivered, and pressing space, then delivers packages and scores them accordingly.
	 * 
	 * If the player is in the Warehouse drop zone, it refills packages instead
	 * @param container The container in which the game is hosted.
	 * @throws SlickException
	 */
	
	private void dropPackage(GameContainer container) throws SlickException
	{
		for (int i = 0; i < buildings.length; i++)
			if (player.getHitbox().intersects(buildings[i].getDropZone()) 
					&& container.getInput().isKeyPressed(Input.KEY_SPACE)
					&& parcels >= buildings[i].parcels()
					&& (buildings[i].awaitingDelivery() || buildings[i].parcels() == -1))
			{
				GameSounds.packagedroppedSound().play();
				score += buildings[i].score() * buildings[i].parcels();
				parcels = buildings[i].parcels() == -1 ? parcelCapacity : parcels - buildings[i].parcels();
				buildings[i].setDeliveryStatus(3);
				activeHouses--;
			}
	}
	
	private void resetHouses()
	{
		for (int i = 0; i < buildings.length; i++)
		{
			if (buildings[i].delivered())
			{
				buildings[i].setDeliveryStatus(0);
			}
		}
	}
	
	
	/**
	 * The fillBuildings method populates the buildings array.
	 * TODO: add Warehouse to this
	 * @throws SlickException 
	 */
	
	private void fillBuildings() throws SlickException 
	{
		buildings[0] = new Warehouse("Sprites/buildings/warehouse.png", 768, 488);
		int i = 1;
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
