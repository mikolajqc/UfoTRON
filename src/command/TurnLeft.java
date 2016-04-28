package command;

import java.io.IOException;
import ufotron.*;

public class TurnLeft extends PlayerCommand
{	
	@Override
	public void Execute(Player player)
	{
		player.TurnLeft();
		try 
		{
			UfoTron.Write(1);
		} 
		catch (IOException ex) 
		{
			System.out.println("Not saved (1)");
		}
		
	}
}
