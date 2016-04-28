package applicationState;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import ufotron.Player;
import command.*;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import ufotron.UfoTron;

public class AStateGame extends ApplicationState
{
	private ArrayList<Player> players;
	
	int numberOfPlayers;
	int myPlayerID;
	
	
	@Override
	public void Init()
	{	
		players = new ArrayList<>();
		numberOfPlayers = 1;
		
		try
		{
			if(UfoTron.GetServerSocket() != null)
			{
				myPlayerID = 0;
				UfoTron.Write(new byte[]{(byte)numberOfPlayers++});
				UfoTron.Write(new byte[]{(byte)numberOfPlayers});
			}
			else
			{
				myPlayerID = WaitForReading();
				numberOfPlayers = WaitForReading();
			}
		}
		catch(Exception e) { e.printStackTrace(); }
		System.out.println("My ID " + myPlayerID);
		
		System.out.println("Game");
		
		Vector2f initialSize = new Vector2f(UfoTron.GetHeight()/20, UfoTron.GetHeight()/20);
		Vector2f initialPosition[] = {new Vector2f(UfoTron.GetWidth()/2 - initialSize.x/2, UfoTron.GetHeight()*7/8 - initialSize.y/2)
		  , new Vector2f(UfoTron.GetWidth()/2 - initialSize.x/2, UfoTron.GetHeight()/8 - initialSize.y/2)
		  , new Vector2f((UfoTron.GetWidth() - UfoTron.GetHeight())/2 + UfoTron.GetHeight()/8 - initialSize.x/2, UfoTron.GetHeight()/2 - initialSize.y/2)
		  , new Vector2f((UfoTron.GetWidth() - UfoTron.GetHeight())/2 + UfoTron.GetHeight()*7/8 - initialSize.x/2, UfoTron.GetHeight()/2 - initialSize.y/2)};
		Vector2f initialVelocity[] = {new Vector2f(0, -50/*-UfoTron.GetHeight()*3/4*/), new Vector2f(0, 50/*UfoTron.GetHeight()*3/4*/), new Vector2f(UfoTron.GetHeight()*3/4,0), new Vector2f(-UfoTron.GetHeight()*3/4, 0)};
		
		for(int i = 0; i < numberOfPlayers; ++i)
		{
			players.add(new Player(i, initialPosition[i], initialSize, initialVelocity[i]));
		}
		
		commands.put(Input.KEY_LEFT, new TurnLeft());
		commands.put(Input.KEY_RIGHT, new TurnRight());
	}
	
	@Override
	public void Update(GameContainer container)
	{
		if(IsGameOver())
			UfoTron.SetCurrentState(new AStateDisconnect());
		
		for(Integer currentEvent = eventQueue.poll(); currentEvent != null; currentEvent = eventQueue.poll())
			HandleInput(currentEvent);
		
		try
		{
			while(UfoTron.GetInputBuffer() != null && UfoTron.GetInputBuffer().available() > 0)
				HandleOpponentsInput();
		}
		catch(IOException e)
		{
			System.out.println("Cannot read");
		}
			
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Update(container.getInput());
		
	}
	
	@Override
	public void Render(GameContainer container, Graphics g)
	{
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Render();
	}
	
	@Override
	public void HandleInput(int keycode)
	{
		commands.get(keycode).Execute(players.get(myPlayerID), myPlayerID);
	}
	
	public int WaitForReading() throws IOException
	{
		int readValue = -1;
		byte[] buffer = new byte[1];
		while(readValue == -1)
			readValue = UfoTron.Read(buffer);
		return buffer[0];//readValue;
	}

	private void HandleOpponentsInput() throws IOException
	{
			byte[] buffer = new byte[2];
			int readData = UfoTron.Read(buffer);
			
			if(readData >= 0)
			{
				System.out.println("Read - Player ID: " + buffer[0] + " Command: " + buffer[1]);
				PlayerCommand currentCommand = new NullCommand();
				
				switch(buffer[1])
				{
				case 0:
					currentCommand = new TurnLeft(); break;
				case 1:
					currentCommand = new TurnRight(); break;
				default:
					System.out.println("Wrong command index"); break;
				}
				currentCommand.Execute(players.get(buffer[0]), myPlayerID);
			}
	}
	
	
	private boolean IsGameOver()
	{
		boolean isGameOver = true;
		for(int i = 0; i < numberOfPlayers; ++i)
			if(players.get(i).GetIsAlive())
				isGameOver = false;
		
		return isGameOver;
	}
}
