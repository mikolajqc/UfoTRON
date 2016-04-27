package ufotron;

import applicationState.AStateGame;
import applicationState.AStateMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player
{
	int playerID;
	Vector2f position;
	Vector2f size;
	Vector2f velocity;
	
	Image sprite;
	
	public Player(int playerID)
	{
		try
		{
			this.playerID = playerID;
			
			position = new Vector2f(320,240);
			size = new Vector2f(30, 30);
			velocity = new Vector2f(290.0F,0);
			sprite = new Image("crate.png");
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Update(Input input)
	{

		/*	try
			{
				System.out.println("Receive: " + UfoTron.GetInputBuffer().read());
			}
			catch(IOException e)
			{
				System.out.println("Sth Wrong with read()");
			}*/

		
		if(position.x < 0 || position.x + size.x > UfoTron.GetWidth() || position.y < 0 || position.y + size.y > UfoTron.GetHeight())
		{
			System.out.println(Timer.getTotalTime());
			AStateGame.GetPlayers().remove(this);
			UfoTron.SetCurrentState(new AStateMenu());
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT))
		{

			
				velocity = new Vector2f(-velocity.y, velocity.x);
				
			try
			{
				UfoTron.Write(2);
			}
			catch (IOException ex)
			{
				System.out.println("Niezapisano");
			}
			
		}
		else if(input.isKeyPressed(Input.KEY_LEFT))
		{
			velocity = new Vector2f(velocity.y, -velocity.x);
			
			try
			{
				UfoTron.Write(1);
			}
			catch (IOException ex)
			{
				System.out.println("Niezapisano");
			}
		}
		
		
		
		position.x += velocity.x * Timer.getDeltaTime();
		position.y += velocity.y * Timer.getDeltaTime();
	}
	
	public void Render()
	{
		sprite.draw(position.x, position.y, size.x, size.y);
	}
}
