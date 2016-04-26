package applicationState;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import ufotron.Player;

public class AStateGame extends ApplicationState
{
	private static ArrayList<Player> players = new ArrayList<Player>();	
	
	public void Init()
	{
		System.out.println("Game");
		players.add(new Player(0));
	}
	
	public void Update(Input input)
	{
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Update(input);
		
	}
	
	public void Render()
	{
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Render();
	}
	
	public static ArrayList<Player> GetPlayers() {return players;}
	
}
