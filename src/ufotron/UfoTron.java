package ufotron;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import applicationState.*;

public class UfoTron extends BasicGame
{
	private static int width = 640;
	private static int height = 480;
	private static boolean isFullscreen = false;
	
	static ApplicationState currentState = null;
	
    public UfoTron()
    {
		super("UfoTron");
    }
 
    public static void main(String[] arguments)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new UfoTron());
            app.setDisplayMode(width, height, isFullscreen);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
 
    @Override
    public void init(GameContainer container) throws SlickException
    {
		currentState = new AStateMenu();
		container.getInput().addKeyListener(new KeyboardInput());
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
		Timer.updateTime();
		
		currentState.Update(container.getInput());
    }
 
	@Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
		currentState.Render();
    }
	
	public static int GetHeight() {return height;}
	public static int GetWidth() {return width;}
	
	public static void SetCurrentState(ApplicationState newState) { currentState = newState; }
	public static ApplicationState GetCurrentState() { return currentState; }
	
}
