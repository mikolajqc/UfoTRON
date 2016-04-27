package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;

public class AStateJoinGame extends ApplicationState
{
	@Override
	public void Init()
	{
		System.out.println("Join");
	}
	
	@Override
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
