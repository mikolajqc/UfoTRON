package ufotron;

import command.KillPlayer;
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
	
	KillPlayer killMe;
	
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
			
			killMe = new KillPlayer();
			sprite = new Image("lightcycle.png");

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
			killMe.Execute(this, playerID);//isAlive = false;
		
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
	
	public void Die()
	{
		isAlive = false;
	}
	
	
	public int GetPlayerID() {return playerID;}
	public Vector2f GetPosition() {return position;}
	public Vector2f GetVelocity() {return velocity;}
	public Vector2f GetSize() {return size;};
	public boolean GetIsAlive() {return isAlive;}
}
