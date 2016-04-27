package applicationState;

import org.newdawn.slick.Input;
import ufotron.*;
import gui.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.lwjgl.util.vector.Vector2f;

public class AStateMenu extends ApplicationState
{
	private Button hostButton;
	private Button joinButton;
	
	@Override
	public void Init()
	{
		System.out.println("Menu");
		
		try
		{
		if(UfoTron.GetServerSocket()!= null) UfoTron.GetServerSocket().close();
		if(UfoTron.GetSocket() != null) UfoTron.GetSocket().close();
		if(UfoTron.GetInputBuffer() != null) UfoTron.GetInputBuffer().close();
		if(UfoTron.GetOutputBuffer() != null) UfoTron.GetOutputBuffer().close();
		}
		catch(IOException e)
		{
			System.out.println("Cannot to delete connection");
		}
		
		//UfoTron.SetServerSocket(null);
		//UfoTron.SetSocket(null);
		//UfoTron.SetInputBuffer(null);
		//UfoTron.SetOutputBuffer(null);
		
		hostButton = new Button("button.png", new Vector2f(270, 190), new Vector2f(100, 100), Void -> { UfoTron.SetCurrentState(new AStateHostGame());});
		joinButton = new Button("button.png", new Vector2f(270, 290), new Vector2f(100, 100), Void -> { UfoTron.SetCurrentState(new AStateJoinGame());});
	}
	
	@Override
	public void Update(Input input)
	{		
		hostButton.Update(input);
		joinButton.Update(input);
	}
	
	@Override
	public void Render()
	{
		hostButton.Render();
		joinButton.Render();
	}
}
