package applicationState;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import ufotron.*;

public class AStateMenu extends ApplicationState
{
	Image playButton;
	
	public void Init()
	{
		System.out.println("Menu");
		try
		{
			playButton = new Image("button.png");
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Update(Input input)
	{		
		if(input.isMousePressed(0) && Mouse.getX() > 270 && Mouse.getX() < 370 && Mouse.getY() > 190 && Mouse.getY() < 290)
			UfoTron.SetCurrentState(new AStateGame());
	}
	
	public void Render()
	{
		playButton.draw(270, 190, 100, 100);
	}
}
