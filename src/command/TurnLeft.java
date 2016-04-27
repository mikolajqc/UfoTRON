package command;

public class TurnLeft extends PlayerCommand
{
	public TurnLeft(int playerID)
	{
		super(playerID);
	}
	
	@Override
	public void Execute()
	{
		//player.SetVelocity(new Vector2f(player.GetVelocity().y, -player.GetVelocity().x));
		player.TurnLeft();
	}
}
