package command;

import java.io.IOException;
import ufotron.*;

public class KillPlayer extends PlayerCommand
{	
	@Override
	public void Execute(Player player, int myPlayerID)
	{
		player.Die();
		
		if(player.GetPlayerID() == myPlayerID)
		{
			System.out.println("Sending - Player: " + myPlayerID + " Command: " + 2);
			try 
			{
				if(UfoTron.isSingleGame == false) UfoTron.Write(new byte[]{(byte)player.GetPlayerID(), (byte)PlayerCommand.availableCommands.KILL_PLAYER.ordinal()});//1);
			} 
			catch (IOException ex)
			{
				System.out.println("Not saved (1)");
			}
		}
	}
}
