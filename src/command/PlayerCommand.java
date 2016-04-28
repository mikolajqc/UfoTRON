package command;

import ufotron.*;

public abstract class PlayerCommand
{
	public PlayerCommand()
	{
	}
	
	public abstract void Execute(Player player);
	
}
