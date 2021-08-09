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
	
	private Building[] buildings = new Building[3];
	
	/**
	 * The roads field contains all of the boxes defining the roads on which the car can drive
	 */
	private Polygon[] roads = new Polygon[12];
	
	private Rectangle carBox = null;
	private Rectangle deliveryZone = null;
	private float movementX, movementY;
	private SpriteSheet truckRight, truckLeft, truckUp, truckDown, truckRightStill, truckLeftStill, truckUpStill, truckDownStill;
	private Animation truckRightAni, truckLeftAni, truckUpAni, truckDownAni, truckRightStillAni, truckLeftStillAni, truckUpStillAni, truckDownStillAni;
	private Animation correctAnimation;
	private char lastKeyPressed;


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		map = new Image("Sprites/map.png");							// 1280 by 720
		
		// TODO: Put building generation in its own function called here
		
		// hitboxes are 32 pixels away from border of buildings
		buildings[0] = new House("Sprites/buildings/HOUSE.png", 368, 272);
		buildings[1] = new House("Sprites/buildings/HOUSE.png", 736, 128);
		buildings[2] = new Store("Sprites/buildings/Shoppe.png", 128, 498);  // use this height for top of lowest horizontal road
		
		carBox = new Rectangle(475, 200, 23, 25);
		

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
		
		truckUp = new SpriteSheet("Sprites/vehicles/truckUp.png", 20, 25);
		truckDown = new SpriteSheet("Sprites/vehicles/truckDown.png", 20, 25);
		truckLeft = new SpriteSheet("Sprites/vehicles/truckLeft.png", 23, 23);
		truckRight = new SpriteSheet("Sprites/vehicles/truckRight.png", 23, 23);
		truckUpStill = new SpriteSheet("Sprites/vehicles/truckUpStill.png", 20, 24);
		truckDownStill = new SpriteSheet("Sprites/vehicles/truckDownStill.png", 20, 24);
		truckLeftStill = new SpriteSheet("Sprites/vehicles/truckLeftStill.png", 23, 22);
		truckRightStill = new SpriteSheet("Sprites/vehicles/truckRightStill.png", 23, 22);
		
		truckUpAni = new Animation(truckUp, 150);
		truckDownAni = new Animation(truckDown, 150);
		truckLeftAni = new Animation(truckLeft, 150);
		truckRightAni = new Animation(truckRight, 150);
		truckUpStillAni = new Animation(truckUpStill, 150);
		truckDownStillAni = new Animation(truckDownStill, 150);
		truckLeftStillAni = new Animation(truckLeftStill, 150);
		truckRightStillAni = new Animation(truckRightStill, 150);
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException
	{
		
		for (int i = 0; i < roads.length; i++)
		{
			g.draw(roads[i]);
		}
	
		g.draw(carBox);
		map.draw();
		directionDirector(container).draw(carBox.getX(), carBox.getY());
		
		for (int i = 0; i < buildings.length; i++)
		{
			// TODO: When drawing buildings, also draw delivery zone around building
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
		
		carBox.setX(carBox.getX() + movementX);
		
		if(collision())
		{
			carBox.setX(carBox.getX() - movementX);
		}
		
		
		if(container.getInput().isKeyDown(Input.KEY_W))
			movementY = -.25f;

		else if(container.getInput().isKeyDown(Input.KEY_S))
			movementY = .25f;
		
		else
			movementY = 0;
		
		carBox.setY(carBox.getY() + movementY);
		
		if(collision())
		{
			carBox.setY(carBox.getY() - movementY);
		}
		
		truckUpAni.update(delta);
		truckDownAni.update(delta);
		truckLeftAni.update(delta);
		truckRightAni.update(delta);
		
		if(container.getInput().isKeyPressed(Input.KEY_SPACE))
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
	}
	
	public Animation directionDirector(GameContainer container)
	{
		if(container.getInput().isKeyDown(Input.KEY_D))
		{
			correctAnimation = truckRightAni;
			lastKeyPressed = 'd';
		}
		else if(container.getInput().isKeyDown(Input.KEY_A))
		{
			correctAnimation = truckLeftAni;
			lastKeyPressed = 'a';
		}
		else if(container.getInput().isKeyDown(Input.KEY_W))
		{
			correctAnimation = truckUpAni;
			lastKeyPressed = 'w';
		}
		else if(container.getInput().isKeyDown(Input.KEY_S))
		{
			correctAnimation = truckDownAni;
			lastKeyPressed = 's';
		}
		else if(lastKeyPressed == 'd')
			correctAnimation = truckRightStillAni;
		
		else if(lastKeyPressed == 'a')
			correctAnimation = truckLeftStillAni;
		
		else if(lastKeyPressed == 'w')
			correctAnimation = truckUpStillAni;
		
		else if(lastKeyPressed == 's')
			correctAnimation = truckDownStillAni;
		
		else
			correctAnimation = truckRightStillAni;
		
		return correctAnimation;
	}	
	
	public boolean collision()
	{	
		for (int i = 0; i < roads.length; i++)
		{
			if (carBox.intersects(roads[i]))
				return true;
		}
		
		return false;
	}

	@Override
	public int getID()
	{
		return 0;
	}
}
