package command;

import applicationState.AStateGame;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import ufotron.*;

/**
 * Command ordering player to turn left and sends itself to other peers
 */
public class TurnLeft extends PlayerCommand
{	
	@Override
	public void Execute(Player player, int myPlayerID, AStateGame currentState)
	{
		player.TurnLeft();
		currentState.GetWalls().get(player.GetPlayerID()).add(new Vector2f(0,0));
		
		if(player.GetPlayerID() == myPlayerID)
		{
			System.out.println("Sending - Player ID: " + myPlayerID + " Command: " + 0);
			try 
			{
				if(UfoTron.isSingleGame == false) UfoTron.Write(new byte[]{(byte)myPlayerID, (byte)availableCommands.TURN_LEFT.ordinal()});//1);
			} 
			catch (IOException ex)
			{
				System.out.println("Not saved (1)");
			}
		}
	}
}
