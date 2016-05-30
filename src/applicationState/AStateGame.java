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
import ufotron.SegmentItersection;
import ufotron.Timer;
import ufotron.UfoTron;

/**
 * Game state/scene
 */
public class AStateGame extends ApplicationState
{
	private ArrayList<Player> players;
	
	int numberOfPlayers;
	int myPlayerID;
	double gameTime;
	
	Image background;
	Image wallSprites[];
	
	ArrayList< ArrayList< Vector2f > > walls;
	PlayerCommand killMe;
	
	/**
	 * Load wall sprites and set up players
	 */
	@Override
	public void Init()
	{	
		players = new ArrayList<>();
		numberOfPlayers = 1;
		walls = new ArrayList<>();
		killMe = new KillPlayer();
		
		try
		{
			background = new Image("grid.png");
			wallSprites = new Image[]{new Image("wallSprite0.png"), new Image("wallSprite1.png")};
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
			new Vector2f(UfoTron.GetHeight()/4,0)
		  , new Vector2f(-UfoTron.GetHeight()/4, 0)
		  ,	new Vector2f(0, -UfoTron.GetHeight()*1/4)
		  , new Vector2f(0, UfoTron.GetHeight()*1/4)
		};

		
		for(int i = 0; i < numberOfPlayers; ++i)
		{
			players.add(new Player(i, initialPosition[i], initialSize, initialVelocity[i], this));
			walls.add(new ArrayList<>());
			walls.get(i).add(new Vector2f(initialPosition[i]));
			walls.get(i).add(new Vector2f(initialPosition[i]));
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
		container.setMouseGrabbed(true);
		
		if(IsGameOver())
		{
			System.err.println("Game Total time " + gameTime);
			
			UfoTron.SetCurrentState(new AStateDisconnect());
			container.setMouseGrabbed(false);
				
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
		{
			players.get(i).Update(container.getInput());

			if(walls.get(i).size()-1 >= 0)
			{
				walls.get(i).get(walls.get(i).size()-1).x = players.get(i).GetPosition().x;
				walls.get(i).get(walls.get(i).size()-1).y = players.get(i).GetPosition().y;
			}
		}
		
		WallCollisions();
	}
	
	/**
	 * Collision detection
	 */
	public void WallCollisions()
	{
		//Choose a player
		for(int player = 0; player < players.size(); ++player)
		{
			//if this player is dead there is no point to check its collisions
			if(!players.get(player).GetIsAlive())
				continue;
			
			//Choose set of walls with which player could collide
			for(int playerWalls = 0; playerWalls < players.size(); ++playerWalls)
			{
				//if this is player's own set of walls do not consider most recent wall - it is used as player collider
				int size  = (playerWalls == player) ? walls.get(playerWalls).size() - 1 : walls.get(playerWalls).size();
				//Choose exact wall
				for(int wall = 0; wall < size-1; ++wall)
				{
					//If there are less then 2 walls do not consider this set of walls
					if(walls.get(player).size()-2 < 0)
						break;
					
					Vector2f playerBegin = walls.get(player).get(walls.get(player).size()-2);
					Vector2f playerEnd = walls.get(player).get(walls.get(player).size()-1);
					Vector2f wallBegin = walls.get(playerWalls).get(wall);
					Vector2f wallEnd = walls.get(playerWalls).get(wall+1);
					//if player collided with another wall
					if(SegmentItersection.DoIntersect(playerBegin, playerEnd, wallBegin, wallEnd))
					{
						playerEnd.x -= players.get(player).GetVelocity().x*Timer.getDeltaTime();
						playerEnd.y -= players.get(player).GetVelocity().y*Timer.getDeltaTime();
						
						//if we revert this player move done during last frame will it still cause collision? If so kill this player
						if(!SegmentItersection.DoIntersect(playerBegin, playerEnd , wallBegin, wallEnd))
						{
							System.err.println("Intersection - Player: " + player + " Wall of player: " + playerWalls + " wall: " + wall );
							System.err.println(playerBegin + " " + playerEnd + " " + wallBegin + " " + wallEnd);
							killMe.Execute(players.get(player), players.get(player).GetPlayerID(), this);
						}
						//otherwise kill player who owns the considered set of walls
						else
						{
							killMe.Execute(players.get(playerWalls), players.get(playerWalls).GetPlayerID(), this);
						}
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void Render(GameContainer container, Graphics g)
	{
		background.draw(0, 0, UfoTron.GetWidth(), UfoTron.GetHeight());
		for(int i = 0; i < players.size(); ++i)
		{
			for(int j = 0; j < walls.get(i).size()-1; ++j)
			{
				wallSprites[i].draw
		        (
				walls.get(i).get(j).x
				, walls.get(i).get(j).y
				, walls.get(i).get(j+1).x - walls.get(i).get(j).x + UfoTron.GetWidth()/100
				, walls.get(i).get(j+1).y - walls.get(i).get(j).y + UfoTron.GetHeight()/100
				);
			}
		}
	}
	
	/**
	 * Execute command specified by occurring event
	 * @param keycode key code representing occurring event
	 */
	@Override
	public void HandleInput(int keycode)
	{
		commands.get(keycode).Execute(players.get(myPlayerID), myPlayerID, this);
	}
	
	/**
	 * Reads data from input buffer without failure
	 * @return read data
	 * @throws IOException 
	 */
	public int WaitForReading() throws IOException
	{
		int readValue = -1;
		byte[] buffer = new byte[1];
		while(readValue == -1)
			readValue = UfoTron.Read(buffer);
		return buffer[0];
	}

	/**
	 * Handles input obtained from other players
	 * @throws IOException 
	 */
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
				currentCommand.Execute(players.get(buffer[0]), myPlayerID, this);
			}
	}
	
	/**
	 * Check if everyone is dead
	 * @return is game over?
	 */
	private boolean IsGameOver()
	{
		boolean isGameOver = true;
		for(int i = 0; i < numberOfPlayers; ++i)
			if(players.get(i).GetIsAlive())
				isGameOver = false;
		
		return isGameOver;
	}
	
	public ArrayList< ArrayList< Vector2f > > GetWalls() { return walls; }
}
