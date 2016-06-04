package applicationState;

import command.PlayerCommand;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * State Pattern implementation - abstract class
 */
public abstract class ApplicationState
{	
	protected HashMap<Integer, PlayerCommand> commands;
	protected Queue<Integer> eventQueue;
	
	/**
	 * Constructs new state along with its own event queue and command hashmap
	 */
	public ApplicationState()
	{
		eventQueue = new LinkedList<Integer>();
		commands = new HashMap<Integer, PlayerCommand>();
		Init();
	}
	
	/**
	 * Handles state initialization
	 */
	public abstract void Init();
	
	/**
	 * Updates state
	 * @param container main slick tool 
	 */
	public abstract void Update(GameContainer container);
	
	/**
	 * Renders state
	 * @param container main slick tool
	 * @param g object containing various graphics methods
	 */
	public void Render(GameContainer container, Graphics g) {}
	
	/**
	 * Handle user input
	 * @param keycode key code of pressed key
	 */
	public void HandleInput(int keycode){}
	
	public HashMap<Integer, PlayerCommand> GetCommands() {return commands;}
	public Queue<Integer> GetEvents() {return eventQueue;}
	
	/**
	 * Add new event on event queue
	 * @param keycode key code defining event
	 */
	public void PushEvent(Integer keycode)
	{
		eventQueue.add(keycode);
	}
}
