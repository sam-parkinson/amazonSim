
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SetupClass extends BasicGame
{

	public SetupClass(String title)
	{
		super(title);
	
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{

	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		g.drawString("Hello World!", 0, 0);
	}

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new SetupClass("Setup Test"));
		
		app.setDisplayMode(800, 600, false);
		
		app.start();
	}
}
