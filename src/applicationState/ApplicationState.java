package applicationState;

import org.newdawn.slick.Input;

public abstract class ApplicationState
{
	public ApplicationState()
	{
		Init();
	}
	
	public abstract void Init();
	
	public abstract void Update(Input input);
	
	public void Render() {}
}
