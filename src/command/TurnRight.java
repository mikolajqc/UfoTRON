package command;

import java.io.IOException;
import ufotron.*;

public class TurnRight extends PlayerCommand
{	
	@Override
	public void Execute(Player player, int myPlayerID)
	{
		player.TurnRight();
		
		if(player.GetPlayerID() == myPlayerID)
		{
			System.out.println("Sending - Player ID: " + myPlayerID + " Command: " + 1);
			try 
			{
				UfoTron.Write(new byte[]{(byte)myPlayerID, (byte)availableCommands.TURN_RIGHT.ordinal()});//2);
			} 
			catch (IOException ex) 
			{
				System.out.println("Not saved (2)");
			}
		}
	}
}
