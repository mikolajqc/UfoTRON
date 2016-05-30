package command;

import applicationState.AStateGame;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import ufotron.*;

/**
 * Command Pattern implementation - abstract class
 */
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
	
	/**
	 * Executes command on given player
	 * @param player player which is supposed to take action
	 * @param myPlayerID id of this device's own player
	 * @param currentState current application state
	 */
	public abstract void Execute(Player player, int myPlayerID, AStateGame currentState);
	
}
