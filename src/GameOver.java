import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * The GameOver class defines the game state.
 */

public class GameOver extends BasicGameState
{

	

	public GameOver(int menu) {
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		
		       }

	/**
	 * The render method draw text and image on the screen
	 */

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
	  Image gameOver = new Image("sprites/gameOver.png");
	  g.drawImage(gameOver,10,10);
      g.drawString("Game Over!", 600, 75);
      g.setColor(Color.red);
         
           
			}
		

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException
	{
		
		GameSounds.gameOverSound().play();	
		   
	}

	/**
	 * The getID method represents the state ID.
	 * @return returns state's ID
	 */
	@Override
	public int getID()
	{
		return 2;
	}

}
