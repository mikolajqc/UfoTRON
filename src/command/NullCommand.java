package command;
import applicationState.AStateGame;
import ufotron.*;

public class NullCommand extends PlayerCommand
{
	@Override
	public void Execute(Player player, int myPlayerID, AStateGame currentState)
	{
	}
}
