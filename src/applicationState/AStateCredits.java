package applicationState;

import ufotron.*;
import gui.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class AStateCredits extends ApplicationState
{

	private Button exitButton;
	private Image titleImage;
	private Image creditsImage;
	
	
	@Override
	public void Init()
	{
		try {
			System.out.println("Credits");

			exitButton = new Button("back_UfoTron_off.png", "back_UfoTron_on.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.80), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.12), Void -> { UfoTron.SetCurrentState(new AStateMenu());});
			creditsImage = new Image("credits.png");
			titleImage = new Image("title.png");
			
		} catch (SlickException ex) {
			Logger.getLogger(AStateMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void Update(GameContainer container)
	{		
		try {
			exitButton.Update(container.getInput());
		} catch (SlickException ex) {
			Logger.getLogger(AStateCredits.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void Render(GameContainer container, Graphics g)
	{
		exitButton.Render();
		titleImage.draw(container.getWidth()*(float)0.05, container.getHeight()*(float)0.05, container.getWidth()*(float)0.9, container.getHeight()*(float)0.2);
		creditsImage.draw(container.getWidth()*(float)0.05, container.getHeight()*(float)0.3, container.getWidth()*(float)0.9, container.getHeight()*(float)0.2);
	}
}
