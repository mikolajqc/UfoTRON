package applicationState;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import org.newdawn.slick.Input;

import ufotron.*;

public class AStateJoinGame extends ApplicationState
{
	public static final int PORT=50007;

	public static final String HOST = "192.168.0.103";

	
	@Override
	public void Init()
	{
		System.out.println("Join");
		
		try 
		{
			UfoTron.SetSocket(new Socket(HOST,PORT));
			
			UfoTron.SetInputBuffer(new DataInputStream(UfoTron.GetSocket().getInputStream()));
			
			UfoTron.SetOutputBuffer(new DataOutputStream(UfoTron.GetSocket().getOutputStream()));
			
		}
		catch (ConnectException e)
		{
			System.out.println("Host not found. Single game started.");
		}
		
		
		
		catch (IOException ex)
		{
			System.out.println("Error with connection to the host");
		}
		
		try
		{
			UfoTron.GetOutputBuffer().write(10);
		}
		catch(IOException e)
		{
			System.out.println("Negative test connection result");
		}
		
		//UfoTron.outputBuffer;
		//System.out.println("Sent start package");
		
	}
	
	@Override
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
