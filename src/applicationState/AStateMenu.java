package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;
import gui.*;
import org.lwjgl.util.vector.Vector2f;

public class AStateMenu extends ApplicationState
{
	private Button button;
	
	public void Init()
	{
		System.out.println("Menu");
		button = new Button("button.png", new Vector2f(270, 190), new Vector2f(100, 100), Void -> { UfoTron.SetCurrentState(new AStateGame());});
	}
	
	public void Update(Input input)
	{		
		button.Update(input);
	}
	
	public void Render()
	{
		button.Render();
	}
}
