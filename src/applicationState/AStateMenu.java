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
import org.newdawn.slick.gui.TextField;

public class AStateMenu extends ApplicationState
{
	private Button hostButton;
	private Button joinButton;
	private Button creditsButton;
	private Button exitButton;
	private Image titleImage;
	
	public static TextField iPServer;
	
	@Override
	public void Init()
	{
		try {
			System.out.println("Menu");
			UfoTron.isSingleGame = false;
			
			hostButton = new Button("host_UfoTron_off.png","host_UfoTron_on.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.35), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.12), Void -> { UfoTron.SetCurrentState(new AStateHostGame());});
			joinButton = new Button("join_UfoTron_off.png","join_UfoTron_on.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.50), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.12), Void -> { UfoTron.SetCurrentState(new AStateJoinGame());});
			creditsButton = new Button("credits_UfoTron_off.png","credits_UfoTron_on.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.65), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.12), Void -> { UfoTron.SetCurrentState(new AStateCredits());});
			exitButton = new Button("exit_UfoTron_off.png","exit_UfoTron_on.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.80), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.12), Void -> { UfoTron.SetCurrentState(new AStateExit());});

			titleImage = new Image("title.png");
			
		} catch (SlickException ex) {
			Logger.getLogger(AStateMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void Update(GameContainer container)
	{		
		try {
			hostButton.Update(container.getInput());
			joinButton.Update(container.getInput());
			creditsButton.Update(container.getInput());
			exitButton.Update(container.getInput());
		} catch (SlickException ex) {
			Logger.getLogger(AStateMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void Render(GameContainer container, Graphics g)
	{
		hostButton.Render();
		joinButton.Render();
		creditsButton.Render();
		exitButton.Render();
		
		titleImage.draw(container.getWidth()*(float)0.05, container.getHeight()*(float)0.05, container.getWidth()*(float)0.9, container.getHeight()*(float)0.2);
		
		iPServer.render(container, g);
	}
}
