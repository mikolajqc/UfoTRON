package command;
import applicationState.AStateGame;
import ufotron.*;

/**
 * Command ordering player to do nothing
 */
public class NullCommand extends PlayerCommand
{
	@Override
	public void Execute(Player player, int myPlayerID, AStateGame currentState)
	{
	}
}
