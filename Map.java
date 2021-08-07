import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Map extends BasicGameState
{
	
	
	private Image map = null;
	private Image car = null;
	private Image house = null;
	private Rectangle carBox = null;
	private Rectangle deliveryZone = null;
	private Polygon mapBorder;
	private Polygon wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10, wall11;
	private float movementX, movementY;
	private boolean collision;



	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		map = new Image("Sprites/map.png");
		car = new Image("Sprites/car.png");
		house = new Image("Sprites/HOUSE.png");
		carBox = new Rectangle(475, 200, 32, 32);
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

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
		
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
		car.draw(carBox.getX(), carBox.getY());
		//house.draw(100,100);

		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException
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

			
	}
	
	public boolean collision()
	{	
		if(carBox.intersects(wall1))
			collision = true;
		else if(carBox.intersects(wall2))
			collision = true;
		else if(carBox.intersects(wall3))
			collision = true;
		else if(carBox.intersects(wall4))
			collision = true;
		else if(carBox.intersects(wall5))
			collision = true;
		else if(carBox.intersects(wall6))
			collision = true;
		else if(carBox.intersects(wall7))
			collision = true;
		else if(carBox.intersects(wall8))
			collision = true;
		else if(carBox.intersects(wall9))
			collision = true;
		else if(carBox.intersects(wall10))
			collision = true;
		else if(carBox.intersects(wall11))
			collision = true;
		else if(carBox.intersects(mapBorder))
			collision = true;
		else
			collision = false;
		return collision;
		
	}

	@Override
	public int getID()
	{
		return 0;
	}
}
