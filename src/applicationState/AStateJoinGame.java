package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;

public class AStateJoinGame extends ApplicationState
{
	public void Init()
	{
		System.out.println("Join");
	}
	
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
