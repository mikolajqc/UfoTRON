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
	
	private Image titleImage;
	
	public static TextField iPServer;
	
	
	@Override
	public void Init()
	{
		try {
			System.out.println("Menu");
			UfoTron.isSingleGame = false;
			hostButton = new Button("host_UfoTron_off.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.4), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.2), Void -> { UfoTron.SetCurrentState(new AStateHostGame());});
			joinButton = new Button("join_UfoTron_off.png", new Vector2f(UfoTron.GetWidth()*(float)0.25, UfoTron.GetHeight()*(float)0.8), new Vector2f(UfoTron.GetWidth()*(float)0.5, UfoTron.GetHeight()*(float)0.2), Void -> { UfoTron.SetCurrentState(new AStateJoinGame());});
			titleImage = new Image("title.png");
		} catch (SlickException ex) {
			Logger.getLogger(AStateMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void Update(GameContainer container)
	{		
		hostButton.Update(container.getInput());
		joinButton.Update(container.getInput());
	}
	
	@Override
	public void Render(GameContainer container, Graphics g)
	{
		hostButton.Render();
		joinButton.Render();
		titleImage.draw(0, 0, container.getWidth(), container.getHeight()*(float)0.4);
		iPServer.render(container, g);
	}
}
