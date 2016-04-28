package applicationState;

import java.io.IOException;
import org.newdawn.slick.Input;
import ufotron.UfoTron;

public class AStateDisconnect extends ApplicationState
{
	@Override
	public void Init()
	{
		
		System.out.println("Server disconnecting");
		try
		{
		if(UfoTron.GetServerSocket()!= null) UfoTron.GetServerSocket().close();
		if(UfoTron.GetSocket() != null) UfoTron.GetSocket().close();
		if(UfoTron.GetInputBuffer() != null) UfoTron.GetInputBuffer().close();
		if(UfoTron.GetOutputBuffer() != null) UfoTron.GetOutputBuffer().close();
		}
		catch(IOException e)
		{
			System.out.println("Cannot disconnect");
		}
	}
	
	@Override
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateMenu());
	}
}
