package command;

import java.io.IOException;
import ufotron.UfoTron;

public class TurnLeft extends PlayerCommand
{
	public TurnLeft(int playerID)
	{
		super(playerID);
		
	}
	
	@Override
	public void Execute()
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
