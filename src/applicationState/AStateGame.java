package applicationState;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import ufotron.Player;
import command.*;
import ufotron.UfoTron;

public class AStateGame extends ApplicationState
{
	private static ArrayList<Player> players = new ArrayList<>();
	
	int numberOfPlayers;
	int myPlayer;
	
	@Override
	public void Init()
	{	
		numberOfPlayers = 1;
		myPlayer = 0; //TODO: Get this information via server connection
		
		System.out.println("Game");
		
		for(int i = 0; i < numberOfPlayers; ++i)
			players.add(new Player(i));
		
		commands.put(Input.KEY_LEFT, new TurnLeft(myPlayer));
		commands.put(Input.KEY_RIGHT, new TurnRight(myPlayer));
	}
	
	@Override
	public void Update(Input input)
	{
		if(players.size() == 0)
			UfoTron.SetCurrentState(new AStateMenu());
		
		for(Integer currentEvent = eventQueue.poll(); currentEvent != null; currentEvent = eventQueue.poll())
			HandleInput(currentEvent);
		
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Update(input);
		
	}
	
	@Override
	public void Render()
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
}
