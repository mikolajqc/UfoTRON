package applicationState;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import org.newdawn.slick.GameContainer;

import ufotron.*;

public class AStateJoinGame extends ApplicationState
{
	public static final int PORT=50007;

	public static String HOST;

	
	@Override
	public void Init()
	{
		System.out.println("Join");
		
		UfoTron.isSingleGame = false;
		
		HOST = AStateMenu.iPServer.getText();
		
		System.out.println("Searching: " + HOST);
		
		try 
		{
			UfoTron.SetSocket(new Socket(HOST,PORT));
			System.out.println("asdasd");
			UfoTron.SetInputBuffer(new DataInputStream(UfoTron.GetSocket().getInputStream()));
			
			UfoTron.SetOutputBuffer(new DataOutputStream(UfoTron.GetSocket().getOutputStream()));
			
		}
		catch (ConnectException e)
		{
			System.out.println("Host not found. Single game started.");
			UfoTron.isSingleGame = true;
		}
		catch (IOException ex)
		{
			System.out.println("Error with connection to the host");
			UfoTron.isSingleGame = true;
		}
	}
	
	@Override
	public void Update(GameContainer container)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
