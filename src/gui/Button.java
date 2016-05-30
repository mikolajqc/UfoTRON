package gui;

import java.util.function.Consumer;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import ufotron.*;

/**
 * Class representing button objects
 */
public class Button
{
	private Image sprite;
	private Vector2f position;
	private Vector2f size;
	private Consumer<Void> function;
	private String imagePath, imagePathActive;
	private boolean isActive;
	

	/**
	 * Construct a button and define its behavior
	 * @param imagePath path to sprite used when button is inactive
	 * @param imagePathActive path to sprite used when button is active
	 * @param position button position (in pixels)
	 * @param size button size (in pixels)
	 * @param function defines button behavior
	 */
	public Button(String imagePath, String imagePathActive, Vector2f position, Vector2f size, Consumer<Void> function)
	{
		this.position = position;
		this.size = size;
		this.function = function;
		this.imagePath = imagePath;
		this.imagePathActive = imagePathActive;
		isActive = false;
		
		try
		{
			sprite = new Image(imagePath);
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Update button object 
	 * @param input object provided by slick - handles user input
	 * @throws SlickException 
	 */
	public void Update(Input input) throws SlickException
	{
		if(Mouse.getX() > position.x && Mouse.getX() < position.x + size.x && UfoTron.GetHeight() - Mouse.getY() > position.y && UfoTron.GetHeight() - Mouse.getY() < position.y + size.y)
		{
			if(isActive == false)
			{
				sprite = new Image(imagePathActive);
				isActive = true;
			}
			if(input.isMousePressed(0))
			{
				System.out.println(Mouse.getX() + " " + Mouse.getY());
				function.accept(null);
			}
		}
		else if(isActive == true)
		{
			sprite = new Image(imagePath);
			isActive = false;
		}
	}
	
	/**
	 * Render button
	 */
	public void Render()
	{
		sprite.draw(position.x, position.y, size.x, size.y);
	}
	
	
}
