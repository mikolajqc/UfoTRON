package ufotron;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class UfoTron extends BasicGame
{
	private static int width = 640;
	private static int height = 480;
	private static boolean isFullscreen = false;
	private static ArrayList<Player> players = new ArrayList<Player>();
	
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
		players.add(new Player(0));
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
		Timer.updateTime();
		
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Update(container.getInput());
		
    }
 
	@Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
		for(int i = 0; i < players.size(); ++i)
			players.get(i).Render();
    }
	
	public static int getHeight() {return height;}
	public static int getWidth() {return width;}
	public static ArrayList<Player> getPlayers() {return players;}
}
