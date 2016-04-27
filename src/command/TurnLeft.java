package command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufotron.UfoTron;

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
		try {
			UfoTron.Write(1);
		} catch (IOException ex) {
			System.out.println("Not saved (1)");
		}
		
	}
}
