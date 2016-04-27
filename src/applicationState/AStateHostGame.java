package applicationState;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import org.newdawn.slick.Input;
import ufotron.*;

public class AStateHostGame extends ApplicationState
{
	public static final int PORT=50007;
	public static final String HOST = "127.0.0.1";
	
	
	@Override
	public void Init()// throws IOException
	{
		System.out.println("Host");
		
		try
		{
			UfoTron.SetServerSocket(new ServerSocket(PORT));
			
			System.out.println("Waiting for a Client");
			
			UfoTron.SetSocket(UfoTron.GetServerSocket().accept());
			System.out.println("Client connected");
			
			UfoTron.SetInputBuffer(new DataInputStream(UfoTron.GetSocket().getInputStream()));
			UfoTron.SetOutputBuffer(new DataOutputStream(UfoTron.GetSocket().getOutputStream()));
			
			
			int i = UfoTron.GetInputBuffer().read();
			//System.out.println("Wynik: " + i);
			
			if(i == 10) System.out.println("Connection: OK");
		} 
		catch (IOException ex)
		{
			System.out.println("Error: Client not found");
		}
		
	}
	
	@Override
	public void Update(Input input)
	{
		UfoTron.SetCurrentState(new AStateGame());
	}
}
