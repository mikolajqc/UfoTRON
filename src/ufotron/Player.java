package ufotron;

import applicationState.AStateGame;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player
{
	int playerID;
	boolean isAlive;
	Vector2f position;
	Vector2f size;
	Vector2f velocity;
	
	Image sprite;
	
	public Player(int playerID, Vector2f position, Vector2f size, Vector2f velocity)
	{
		try
		{
			isAlive = true;
			this.playerID = playerID;
			
			this.position = position;
			this.size = size;
			this.velocity = velocity;
			sprite = new Image("crate.png");
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Update(Input input)
	{		
		if(!isAlive)
			return;
		
		if(position.x < 0 || position.x + size.x > UfoTron.GetWidth() || position.y < 0 || position.y + size.y > UfoTron.GetHeight())
			isAlive = false;
		
		try
		{
			int readData = UfoTron.Read();
			
			if(readData > 0)
				System.out.println(readData);
		}
		catch(IOException e)
		{
			System.out.println("Cannot read");
		}
		
		position.x += velocity.x * Timer.getDeltaTime();
		position.y += velocity.y * Timer.getDeltaTime();
	}
	
	public void Render()
	{
		if(!isAlive)
			return;
		
		sprite.draw(position.x, position.y, size.x, size.y);
	}
	
	public void TurnLeft()
	{
		if(!isAlive)
			return;
		
		velocity = new Vector2f(velocity.y, -velocity.x);
		sprite.setCenterOfRotation(size.x/2, size.y/2);
		sprite.rotate(-90);
	}
	
	public void TurnRight()
	{
		if(!isAlive)
			return;
		
		velocity = new Vector2f(-velocity.y, velocity.x);
		sprite.setCenterOfRotation(size.x/2, size.y/2);
		sprite.rotate(90);
	}
	
	public int GetPlayerID() {return playerID;}
	public Vector2f GetPosition() {return position;}
	public Vector2f GetVelocity() {return velocity;}
	public boolean GetIsAlive() {return isAlive;}
}
