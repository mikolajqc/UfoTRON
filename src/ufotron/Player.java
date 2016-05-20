package ufotron;

import applicationState.AStateGame;
import command.KillPlayer;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Input;

public class Player
{
	int playerID;
	boolean isAlive;
	Vector2f position;
	Vector2f size;
	Vector2f velocity;
	
	KillPlayer killMe;
	
	AStateGame currentState;
	
	public Player(int playerID, Vector2f position, Vector2f size, Vector2f velocity, AStateGame currentState)
	{
			isAlive = true;
			this.playerID = playerID;
			this.position = position;
			//this.size = size;
			this.velocity = velocity;
			this.currentState = currentState;
			
			killMe = new KillPlayer();
	}
	
	public void Update(Input input)
	{		
		if(!isAlive)
			return;
		
		if(position.x < 0 || position.x + 10 > UfoTron.GetWidth() || position.y < 0 || position.y + 10 > UfoTron.GetHeight())
			killMe.Execute(this, playerID, currentState);//isAlive = false;
		
		position.x += velocity.x * Timer.getDeltaTime();
		position.y += velocity.y * Timer.getDeltaTime();
	}
	
	public void TurnLeft()
	{
		if(!isAlive)
			return;
		
		velocity = new Vector2f(velocity.y, -velocity.x);
	}
	
	public void TurnRight()
	{
		if(!isAlive)
			return;
		
		velocity = new Vector2f(-velocity.y, velocity.x);
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
