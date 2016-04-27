package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;

public class AStateHostGame extends ApplicationState
{
	@Override
	public void Init()
	{
		System.out.println("Host");
	}
	
	@Override
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
