package applicationState;


import org.newdawn.slick.GameContainer;

/**
 * Exit state
 */
public class AStateExit extends ApplicationState
{
	/**
	 * Exit application
	 */
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