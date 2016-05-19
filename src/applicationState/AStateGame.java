package applicationState;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import ufotron.Player;
import command.*;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ufotron.Timer;
import ufotron.UfoTron;


public class AStateGame extends ApplicationState
{
	private ArrayList<Player> players;
	
	int numberOfPlayers;
	int myPlayerID;
	double gameTime;
	
	Image background;
	
	
	@Override
	public void Init()
	{	
		players = new ArrayList<>();
		numberOfPlayers = 1;
		
		try
		{
			background = new Image("grid.png");
			if(UfoTron.isSingleGame != true)
			{
				if(UfoTron.GetServerSocket() != null)
				{
					myPlayerID = 0;
					UfoTron.Write(new byte[]{(byte)numberOfPlayers++});
					UfoTron.Write(new byte[]{(byte)numberOfPlayers});
				}
				else
				{
					System.out.println("Waiting for reading");
					myPlayerID = WaitForReading();
					numberOfPlayers = WaitForReading();
				}
			}
			else
			{
				myPlayerID = 0;
				numberOfPlayers = 1;
			}
			
		}
		catch(Exception e) { e.printStackTrace(); }
		System.out.println("My ID " + myPlayerID);
		
		System.out.println("Game");
		
		Vector2f initialSize = new Vector2f(UfoTron.GetHeight()/10, UfoTron.GetHeight()/20);
		Vector2f initialPosition[] = 
		{
			new Vector2f((UfoTron.GetWidth() - UfoTron.GetHeight())/2 + UfoTron.GetHeight()/8 - initialSize.x/2, UfoTron.GetHeight()/2 - initialSize.y/2)
		  , new Vector2f((UfoTron.GetWidth() - UfoTron.GetHeight())/2 + UfoTron.GetHeight()*7/8 - initialSize.x/2, UfoTron.GetHeight()/2 - initialSize.y/2)
		  ,	new Vector2f(UfoTron.GetWidth()/2 - initialSize.x/2, UfoTron.GetHeight()*7/8 - initialSize.y/2)
		  , new Vector2f(UfoTron.GetWidth()/2 - initialSize.x/2, UfoTron.GetHeight()/8 - initialSize.y/2)

		};
		Vector2f initialVelocity[] = 
		{
			new Vector2f(UfoTron.GetHeight()*3/4/10,0)
		  , new Vector2f(-UfoTron.GetHeight()*3/4/10, 0)
		  ,	new Vector2f(0, -UfoTron.GetHeight()*1/4/10)
		  , new Vector2f(0, UfoTron.GetHeight()*1/4/10)
		};

		
		for(int i = 0; i < numberOfPlayers; ++i)
		{
			players.add(new Player(i, initialPosition[i], initialSize, initialVelocity[i]));
		}
		
		commands.put(Input.KEY_LEFT, new TurnLeft());
		commands.put(Input.KEY_RIGHT, new TurnRight());
		commands.put(Input.KEY_ESCAPE, new KillPlayer());
		
		gameTime = 0;
	}
	
	@Override
	public void Update(GameContainer container)
	{
		gameTime += Timer.getDeltaTime();
		
		if(IsGameOver())
		{
			System.err.println("Game Total time " + gameTime);
			UfoTron.SetCurrentState(new AStateDisconnect());
		}
		
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
		
		Colisions();
	}
	
	@Override
	public void Render(GameContainer container, Graphics g)
	{
		background.draw(0, 0, UfoTron.GetWidth(), UfoTron.GetHeight());
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
		return buffer[0];
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
				case 2:
					currentCommand = new KillPlayer(); break;
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
	
	
	private void Colisions()
	{
		for(int i = 0; i < numberOfPlayers-1; ++i)
		{
			if(!players.get(i).GetIsAlive())
				continue;
			
			for(int j = i+1; j< numberOfPlayers; ++j)
			{
				if(!players.get(j).GetIsAlive())
					continue;
		
				if(CheckColision(players.get(i), players.get(j)))
				{
					System.out.println("Collision");
					PlayerCommand currentCommand;
				
					currentCommand = new KillPlayer();
					currentCommand.Execute(players.get(i), players.get(i).GetPlayerID());
					currentCommand.Execute(players.get(j), players.get(j).GetPlayerID());
				}
			}
		}
	}
	
	private boolean CheckColision(Player first, Player second)
	{
		if(Math.abs(first.GetPosition().x + 1/2*(first.GetSize().x)  - second.GetPosition().x - 1/2*(second.GetSize().x)) < (first.GetSize().x + second.GetSize().x)/2)
		{
			if(Math.abs(first.GetPosition().y + 1/2*(first.GetSize().y)  - second.GetPosition().y - 1/2*(second.GetSize().y)) < (first.GetSize().y + second.GetSize().y)/2)
			{
				return true;
			}
		}
		return false;
	}
	
}
