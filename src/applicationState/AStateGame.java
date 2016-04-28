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
	private static ArrayList<Player> players = new ArrayList<>();
	
	int numberOfPlayers;
	int myPlayerID;
	
	
	@Override
	public void Init()
	{	
		players.clear();
		numberOfPlayers = 1;
		
		try
		{
			if(UfoTron.GetServerSocket() != null)
			{
				myPlayerID = 0;
				UfoTron.Write(numberOfPlayers++);
				UfoTron.Write(numberOfPlayers);
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
		Vector2f initialVelocity[] = {new Vector2f(0, -UfoTron.GetHeight()*3/4), new Vector2f(0, UfoTron.GetHeight()*3/4), new Vector2f(UfoTron.GetHeight()*3/4,0), new Vector2f(-UfoTron.GetHeight()*3/4, 0)};
		
		for(int i = 0; i < numberOfPlayers; ++i)
		{
			players.add(new Player(i, initialPosition[i], initialSize, initialVelocity[i]));
		}
		
		commands.put(Input.KEY_LEFT, new TurnLeft(myPlayerID));
		commands.put(Input.KEY_RIGHT, new TurnRight(myPlayerID));
	}
	
	@Override
	public void Update(GameContainer container)
	{
		if(players.isEmpty())
			UfoTron.SetCurrentState(new AStateDisconnect());
		
		for(Integer currentEvent = eventQueue.poll(); currentEvent != null; currentEvent = eventQueue.poll())
			HandleInput(currentEvent);
		
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
		commands.get(keycode).Execute();
	}
	
	public static ArrayList<Player> GetPlayers() {return players;}
	
	public int WaitForReading() throws IOException
	{
		int readValue = -1;
		while(readValue == -1)
			readValue = UfoTron.Read();
		return readValue;
	}

}
