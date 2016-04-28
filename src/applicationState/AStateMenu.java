package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;
import gui.*;
import org.lwjgl.util.vector.Vector2f;

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
	public void Update(Input input)
	{		
		hostButton.Update(input);
		joinButton.Update(input);
	}
	
	@Override
	public void Render()
	{
		hostButton.Render();
		joinButton.Render();
	}
}
