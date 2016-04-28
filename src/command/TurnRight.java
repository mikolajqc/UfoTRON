package command;

import java.io.IOException;
import ufotron.*;

public class TurnRight extends PlayerCommand
{	
	@Override
	public void Execute(Player player)
	{
		player.TurnRight();
		
		try 
		{
			UfoTron.Write(2);
		} 
		catch (IOException ex) 
		{
			System.out.println("Not saved (1)");
		}
		
	}
}
