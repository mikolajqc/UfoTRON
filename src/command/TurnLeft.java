package command;

import java.io.IOException;
import ufotron.*;

public class TurnLeft extends PlayerCommand
{	
	@Override
	public void Execute(Player player, int myPlayerID)
	{
		player.TurnLeft();
		
		if(player.GetPlayerID() == myPlayerID)
		{
			System.out.println("Sending - Player ID: " + myPlayerID + " Command: " + 0);
			try 
			{
				UfoTron.Write(new byte[]{(byte)myPlayerID, (byte)availableCommands.TURN_LEFT.ordinal()});//1);
			} 
			catch (IOException ex)
			{
				System.out.println("Not saved (1)");
			}
		}
	}
}
