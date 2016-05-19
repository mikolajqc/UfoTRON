package applicationState;


import org.newdawn.slick.GameContainer;

public class AStateExit extends ApplicationState
{
	
	@Override
	public void Init()
	{
		System.out.println("Exit");
	}
	
	@Override
	public void Update(GameContainer container)
	{
		container.exit();
	}
}