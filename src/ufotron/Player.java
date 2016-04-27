package ufotron;

import applicationState.AStateGame;
import applicationState.AStateMenu;
import java.io.IOException;
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
	
	public Player(int playerID, Vector2f position, Vector2f size, Vector2f velocity)
	{
		try
		{
			this.playerID = playerID;
			
			this.position = position;//new Vector2f(320,240);
			this.size = size;//new Vector2f(30, 30);
			this.velocity = velocity;//new Vector2f(0,-100);
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
			AStateGame.GetPlayers().remove(this);

		
		position.x += velocity.x * Timer.getDeltaTime();
		position.y += velocity.y * Timer.getDeltaTime();
	}
	
	public void Render()
	{
		sprite.draw(position.x, position.y, size.x, size.y);
	}
	
	public void TurnLeft()
	{
		velocity = new Vector2f(velocity.y, -velocity.x);
		sprite.setCenterOfRotation(size.x/2, size.y/2);
		sprite.rotate(-90);
	}
	
	public void TurnRight()
	{
		velocity = new Vector2f(-velocity.y, velocity.x);
		sprite.setCenterOfRotation(size.x/2, size.y/2);
		sprite.rotate(90);
	}
	
	public int GetPlayerID() {return playerID;}
	public Vector2f GetPosition() {return position;}
	public Vector2f GetVelocity() {return velocity;}	
}
