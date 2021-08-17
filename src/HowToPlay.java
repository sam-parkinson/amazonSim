import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * The HowToPlay class is the state that describes how to play the game.
 **/
public class HowToPlay extends BasicGameState
{
	
	
	/**
	 * Constructor
	 * @param menu
	 */
	public HowToPlay(int menu) {
		
	}
	
	/**
	 * The init method initialize all staff that it needs.
	 */
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		
	    
	}
	/**
	 * The render method draw how to play text on the HowToPlay game state.
	 */
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
	{
		
		g.setColor(Color.green);
        g.drawString("Welcome to Amazon Stimulator Game!" , 500, 80);
        g.setColor(Color.white);
        g.drawString("In this game you can enjoy riding throughout the city and dropping "
        		+ "certain amount of packages by the places need it until time runs out.", 20, 130);
        g.drawString("\ncreated by:\nSam Parkinson\nJair "
      		+ "Anlas\nNataliya Storozhuk\nDmytro Bondarchuk", 1050, 550);
        g.setColor(Color.orange);
        g.drawString("Instruction: \n\r\n"
        		+ "Use W, A, S, D for movement \n\r\nUse Space for package dropping \n\r\nPress B to return", 100, 250);
        
	}
		
	/**
	 * The update method updating states.
	 */
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException
	{     // pressing B will return you to the game
		  if (container.getInput().isKeyPressed(Input.KEY_B)) {
				sbg.enterState(1);
			}
		 else if(container.getInput().isKeyPressed(Input.KEY_E)) {
				sbg.enterState(4);
			   		   
			}
		}
	
	/**
	 * The getID method represents the state ID.
	 * @return returns state's ID
	 */
	@Override
	public int getID()
	{
		return 3;
	}

}
