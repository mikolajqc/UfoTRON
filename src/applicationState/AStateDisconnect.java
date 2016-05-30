package applicationState;

import java.io.IOException;
import org.newdawn.slick.GameContainer;
import ufotron.UfoTron;

public class AStateDisconnect extends ApplicationState
{
	@Override
	public void Init()
	{
		
		System.out.println("Server disconnecting");
		try
		{
			if(UfoTron.GetServerSocket()!= null) 
				UfoTron.GetServerSocket().close();
			if(UfoTron.GetSocket() != null) 
				UfoTron.GetSocket().close();
			if(UfoTron.GetInputBuffer() != null) 
				UfoTron.GetInputBuffer().close();
			if(UfoTron.GetOutputBuffer() != null) 
			{
				UfoTron.GetOutputBuffer().flush();
				UfoTron.GetOutputBuffer().close();
			}
		
			UfoTron.SetServerSocket(null);
			UfoTron.SetSocket(null);
			UfoTron.SetInputBuffer(null);
			UfoTron.SetOutputBuffer(null);
			
			assert(UfoTron.GetInputBuffer() == null && UfoTron.GetOutputBuffer() == null && UfoTron.GetServerSocket() == null && UfoTron.GetSocket() == null);
		}
		catch(IOException e)
		{
			System.out.println("Cannot disconnect");
		}
	}
	
	@Override
	public void Update(GameContainer container)
	{
		UfoTron.SetCurrentState(new AStateMenu());
	}
}
