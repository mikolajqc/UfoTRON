package applicationState;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import ufotron.UfoTron;
import ufotron.Timer;

/**
 * Disconnecting state
 */
public class AStateDisconnect extends ApplicationState
{
	/**
	 * Close sockets and input/output buffers 
	 */
	byte buffer[];
	float timer;
	@Override
	public void Init()
	{
		buffer = new byte[2];
		timer = 0;
	}
	
	@Override
	public void Update(GameContainer container)
	{
		try
		{
			if(UfoTron.GetInputBuffer() != null && UfoTron.GetInputBuffer().available() > 0)
				UfoTron.GetInputBuffer().read(buffer);
		}
		catch (IOException ex)
		{
			Logger.getLogger(AStateDisconnect.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		if(timer >= 0.2)
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
			UfoTron.SetCurrentState(new AStateMenu());
		}
		timer += Timer.getDeltaTime();
	}
}
