import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
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
	
	private Music music; 
	private Sound collisionSound;
	private Sound packageDroppedSound;
	private Sound pointRewardSound;
	private Sound GameOverSound;
	
		
	private Image map = null;
	
	private Building[] buildings = new Building[3];
	
	private Rectangle carBox = null;
	private Rectangle deliveryZone = null;
	private Polygon mapBorder;
	private Polygon wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10, wall11;
	private float movementX, movementY;
	private boolean collision;
	private SpriteSheet truckRight, truckLeft, truckUp, truckDown, truckRightStill, truckLeftStill, truckUpStill, truckDownStill;
	private Animation truckRightAni, truckLeftAni, truckUpAni, truckDownAni, truckRightStillAni, truckLeftStillAni, truckUpStillAni, truckDownStillAni;
	private Animation correctAnimation;
	private char lastKeyPressed;


	public Map(int map) {
		super();
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException
	{
		music = new Music("sounds/background2.wav");
		music.setVolume(0.1f); 
		music.loop(); 
					
		
		collisionSound = new Sound("sounds/collision.wav");
		packageDroppedSound = new Sound ("sounds/packagedropped.wav");
		pointRewardSound = new Sound ("sounds/pointReward.wav");
		GameOverSound = new Sound ("sounds/gover.wav");
		
		map = new Image("Sprites/map.png");							// 1280 by 720
		
		// TODO: Put building generation in its own function called here
		
		// hit boxes are 32 pixels away from border of buildings
		buildings[0] = new House("Sprites/buildings/HOUSE.png", 368, 286);
		buildings[1] = new House("Sprites/buildings/HOUSE.png", 736, 148);
		buildings[2] = new Store("Sprites/buildings/Shoppe.png", 128, 498);  // use this height for top of lowest horizontal road
		
		carBox = new Rectangle(475, 200, 23, 25);
		
		// TODO: Break out into separate function (initRoads()?)
		float[] mapBorderPoints = new float[]{0, 0, 1280, 0, 1280, 720, 0, 720};
		mapBorder = new Polygon(mapBorderPoints);
		float[] wall1Points = new float[] {118, 124, 471, 124, 471, 339, 273, 339, 273, 396, 471, 396, 471, 565, 118, 565};		
		wall1 = new Polygon(wall1Points);
		float[] wall2Points = new float[] {0, 0, 62, 0, 62, 68, 0, 68};
		wall2 = new Polygon(wall2Points);
		float[] wall3Points = new float[] {117, 0, 117, 68, 527, 68, 527, 196, 1076, 196, 1076, 0};
		wall3 = new Polygon(wall3Points);
		float[] wall4Points = new float[] {1132, 0, 1132, 70, 1280, 70, 1280, 0};
		wall4 = new Polygon(wall4Points);
		float[] wall5Points = new float[] {0, 124, 61, 124, 61, 565, 0, 565};
		wall5 = new Polygon(wall5Points);
		float[] wall6Points = new float[] {528, 254, 597, 254, 597, 565, 528, 565};
		wall6 = new Polygon(wall6Points);
		float[] wall7Points = new float[] {654, 252, 1075, 252, 1075, 565, 900, 565, 900, 403, 843, 403, 843, 565, 654, 565};
		wall7 = new Polygon(wall7Points);
		float[] wall8Points = new float[] {1132, 127, 1280, 127, 1280, 565, 1132, 565};
		wall8 = new Polygon(wall8Points);
		float[] wall9Points = new float[] {0, 622, 61, 622, 61, 720, 0, 720};
		wall9 = new Polygon(wall9Points);
		float[] wall10Points = new float[] {118, 622, 1075, 622, 1075, 720, 118, 720};
		wall10 = new Polygon(wall10Points);
		float[] wall11Points = new float[] {1132, 622, 1280, 622, 1280, 720, 1132, 720};
		wall11 = new Polygon(wall11Points);
		
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
		// TODO: break out into renderRoads()
		g.draw(wall1);
		g.draw(wall2);
		g.draw(wall3);
		g.draw(wall4);
		g.draw(wall5);
		g.draw(wall6);
		g.draw(wall7);
		g.draw(wall8);
		g.draw(wall9);
		g.draw(wall10);
		g.draw(wall11);
		
		
		g.draw(mapBorder);
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
		if(carBox.intersects(wall1)) {
			collision = true;
		collisionSound.play();
		}
		else if(carBox.intersects(wall2)) {
			collision = true;
			collisionSound.play();
			}
		else if(carBox.intersects(wall3)) {
			collision = true;
			collisionSound.play();
		}
		else if(carBox.intersects(wall4)) {
			collision = true;
			collisionSound.play();
		}
		else if(carBox.intersects(wall5)) {
			collision = true;
			collisionSound.play();
		}
		else if(carBox.intersects(wall6)) {
			collision = true;
			collisionSound.play();
		}
		else if(carBox.intersects(wall7)) {
			collision = true;
			collisionSound.playAt(-1,0,0);
		}
		else if(carBox.intersects(wall8)) {
			collision = true;
			collisionSound.play();
		}
		else if(carBox.intersects(wall9)) {
			collision = true;
			collisionSound.play();}
		else if(carBox.intersects(wall10)) {
			collision = true;
			collisionSound.play();}
		else if(carBox.intersects(wall11)) {
			collision = true;
			collisionSound.play();}
		else if(carBox.intersects(mapBorder)) {
			collision = true;
			collisionSound.play();}
		else
			collision = false;
		return collision;
		
	}

	@Override
	public int getID()
	{
		return 1;
	}
}
