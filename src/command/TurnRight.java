package command;

public class TurnRight extends PlayerCommand
{
	public TurnRight(int playerID)
	{
		super(playerID);
	}
	
	@Override
	public void Execute()
	{
		//player.SetVelocity(new Vector2f(player.GetVelocity().y, -player.GetVelocity().x));
		player.TurnRight();
	}
}
