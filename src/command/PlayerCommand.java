package command;

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
	
	public abstract void Execute(Player player, int myPlayerID);
	
}
