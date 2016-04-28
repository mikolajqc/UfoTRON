package applicationState;

import ufotron.*;
import gui.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AStateMenu extends ApplicationState
{
	private Button hostButton;
	private Button joinButton;
	
	@Override
	public void Init()
	{
		System.out.println("Menu");
		
		hostButton = new Button("button.png", new Vector2f(270, 190), new Vector2f(100, 100), Void -> { UfoTron.SetCurrentState(new AStateHostGame());});
		joinButton = new Button("button.png", new Vector2f(270, 290), new Vector2f(100, 100), Void -> { UfoTron.SetCurrentState(new AStateJoinGame());});
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
	}
}
