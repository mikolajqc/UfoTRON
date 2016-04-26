package gui;

import java.util.function.Consumer;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

public class Button
{
	private Image sprite;
	private Vector2f position;
	private Vector2f size;
	private Consumer<Void> function;

	public Button(String imagePath, Vector2f position, Vector2f size, Consumer<Void> function)
	{
		this.position = position;
		this.size = size;
		this.function = function;
		
		try
		{
			sprite = new Image(imagePath);
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Update(Input input)
	{
		if(input.isMousePressed(0) && Mouse.getX() > position.x && Mouse.getX() < position.x + size.x && Mouse.getY() > position.y && Mouse.getY() < position.y + size.y)
			function.accept(null);
	}
	
	public void Render()
	{
		sprite.draw(position.x, position.y, size.x, size.y);
	}
	
	
}
