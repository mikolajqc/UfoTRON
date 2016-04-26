package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;

public class AStateHostGame extends ApplicationState
{
	public void Init()
	{
		System.out.println("Host");
	}
	
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
