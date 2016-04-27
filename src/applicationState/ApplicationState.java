package applicationState;

import command.PlayerCommand;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.slick.Input;

public abstract class ApplicationState
{	
	protected HashMap<Integer, PlayerCommand> commands;
	protected Queue<Integer> eventQueue;
	
	public ApplicationState()
	{
		eventQueue = new LinkedList<Integer>();
		commands = new HashMap<Integer, PlayerCommand>();
		Init();
	}
	
	public abstract void Init();
	
	public abstract void Update(Input input);
	
	public void Render() {}
	
	public void HandleInput(int keycode){}
	
	public HashMap<Integer, PlayerCommand> GetCommands() {return commands;}
	public Queue<Integer> GetEvents() {return eventQueue;}
	
	public void PushEvent(Integer keycode)
	{
		eventQueue.add(keycode);
	}
}
