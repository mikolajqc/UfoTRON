package command;

import java.io.IOException;
import ufotron.UfoTron;

public class TurnRight extends PlayerCommand
{
	public TurnRight(int playerID)
	{
		super(playerID);
	}
	
	@Override
	public void Execute()
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
