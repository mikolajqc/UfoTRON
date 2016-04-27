package command;

import ufotron.*;
import applicationState.AStateGame;

public abstract class PlayerCommand
{
	public PlayerCommand(int playerID)
	{
		assert(AStateGame.GetPlayers().size() > playerID);
		player = AStateGame.GetPlayers().get(playerID);
	}
	
	public abstract void Execute();
	
	Player player;
}
