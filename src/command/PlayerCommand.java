package command;

import applicationState.AStateGame;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import ufotron.*;

public abstract class PlayerCommand
{
	public static enum availableCommands
	{
		TURN_LEFT,
		TURN_RIGHT,
		KILL_PLAYER,
	}
	
	
	public PlayerCommand()
	{
		
	}
	
	public abstract void Execute(Player player, int myPlayerID, AStateGame currentState);
	
}
