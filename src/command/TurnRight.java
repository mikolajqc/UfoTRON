package command;

import applicationState.AStateGame;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import ufotron.*;

public class TurnRight extends PlayerCommand
{	
	@Override
	public void Execute(Player player, int myPlayerID, AStateGame currentState)
	{
		player.TurnRight();
		currentState.GetWalls().get(player.GetPlayerID()).add(new Vector2f(0,0));
		
		if(player.GetPlayerID() == myPlayerID)
		{
			System.out.println("Sending - Player ID: " + myPlayerID + " Command: " + 1);
			try 
			{
				if(UfoTron.isSingleGame == false) UfoTron.Write(new byte[]{(byte)myPlayerID, (byte)availableCommands.TURN_RIGHT.ordinal()});//2);
			} 
			catch (IOException ex) 
			{
				System.out.println("Not saved (2)");
			}
		}
	}
}
