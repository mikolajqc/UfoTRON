package applicationState;

import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.Input;
import ufotron.Player;
import ufotron.UfoTron;

public class AStateGame extends ApplicationState
{
	private static ArrayList<Player> players = new ArrayList<>();	
	
	@Override
	public void Init()
	{
		System.out.println("Game");
		players.add(new Player(0));
	}
	
	@Override
	public void Update(Input input)
	{
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Update(input);
		
	}
	
	@Override
	public void Render()
	{
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Render();
	}
	
	public static ArrayList<Player> GetPlayers() {return players;}
	
	
}
