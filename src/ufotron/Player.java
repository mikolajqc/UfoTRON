package ufotron;

import java.sql.Time;
import org.lwjgl.util.vector.Vector;
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
			velocity = new Vector2f(0.1F,0);
			sprite = new Image("crate.png");
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Update(Input input)
	{
		if(position.x < 0 || position.x + size.x > UfoTron.getWidth() || position.y < 0 || position.y + size.y > UfoTron.getHeight())
			UfoTron.getPlayers().remove(this);
		
		if(input.isKeyPressed(Input.KEY_RIGHT))
		{
			velocity = new Vector2f(-velocity.y, velocity.x);
		}
		else if(input.isKeyPressed(Input.KEY_LEFT))
		{
			velocity = new Vector2f(velocity.y, -velocity.x);
		}
		
		position.x += velocity.x;
		position.y += velocity.y;
	}
	
	public void Render()
	{
		sprite.draw(position.x, position.y, size.x, size.y);
	}
}
