package ufotron;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import applicationState.*;

import java.io.*;
import java.net.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;

/**
Main application class
*/

public class UfoTron extends BasicGame
{
	private static int width = 800;
	private static int height = 640;
	private static boolean isFullscreen = false;

	private static ServerSocket serverSocket = null;
	private static Socket socket = null;
	private static DataInputStream inputBuffer = null;
	private static DataOutputStream outputBuffer = null;
	
	
	static ApplicationState currentState = null;
	
	public static boolean isSingleGame = false;
	
	/**
	Slick requires this initialization.
	*/
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
 
	/**
	Init function provided by slick.
	@param container main slick tool
	*/
    @Override
    public void init(GameContainer container) throws SlickException
    {
		currentState = new AStateMenu();
		container.getInput().addKeyListener(new KeyboardInput());
		container.setVSync(true);
		container.setTargetFrameRate(60);
		AStateMenu.iPServer = new TextField(container, container.getDefaultFont(), UfoTron.GetWidth()*390/640, UfoTron.GetHeight()*10/480, UfoTron.GetWidth()*200/640, UfoTron.GetHeight()*40/480);
		AStateMenu.iPServer.setText("127.0.0.1");
		container.setMouseCursor(new Image("lightcycle.png"), 0, 0);
		Test();
		
		
	}
 
	/**
	Few examples testing segment intersection
	<p>
	Should show: false, false, true, false
	*/
	public void Test()
	{
		System.err.println(SegmentItersection.DoIntersect(new Vector2f(1.01f, 1.01f), new Vector2f(10.01f, 1.0f), new Vector2f(1.0f, 2.01f), new Vector2f(10.01f, 2.01f)));
		System.err.println(SegmentItersection.DoIntersect(new Vector2f(1, 1), new Vector2f(10, 1), new Vector2f(1, 2), new Vector2f(10, 2)));
		System.err.println(SegmentItersection.DoIntersect(new Vector2f(10, 0), new Vector2f(0, 10), new Vector2f(0, 0), new Vector2f(10, 10)));
		System.err.println(SegmentItersection.DoIntersect(new Vector2f(-5, -5), new Vector2f(0, 0), new Vector2f(1, 1), new Vector2f(10, 10)));
		
	}
	
	/**
	Update function provided by slick.
	@param container main slick tool
	@param delta amount of time passed since last update (in ms)
	*/
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
		Timer.updateTime();
		currentState.Update(container);
    }
 
	/**
	Render function provided by slick.
	@param container main slick tool
	@param g object containing method used to render and display objects
	*/
	@Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
		g.setBackground(new Color(16, 16, 32));
		currentState.Render(container, g);
    }
	
	public static int GetHeight() {return height;}
	public static int GetWidth() {return width;}
	public static void SetCurrentState(ApplicationState newState) { currentState = newState; }
	public static ApplicationState GetCurrentState() { return currentState; }
	public static ServerSocket GetServerSocket() {return serverSocket;}
	public static Socket GetSocket() {return socket;}
	public static DataInputStream GetInputBuffer() {return inputBuffer;}
	public static DataOutputStream GetOutputBuffer() {return outputBuffer;}
	
	public static void SetServerSocket(ServerSocket source){serverSocket = source;}
	public static void SetSocket(Socket source) {socket = source;}
	public static void SetInputBuffer(DataInputStream source) {inputBuffer = source;}
	public static void SetOutputBuffer(DataOutputStream source) {outputBuffer = source;}
	
	/**
	 * Read data from input buffer to local array
	 * @param buffer local array which can be processed further
	 * @return number of read bytes, -1 if failed to read
	 * @throws IOException 
	 */
	public static int Read(byte[] buffer) throws IOException 
	{	
		if(inputBuffer == null)
			return -1;
		
		if(inputBuffer.available() > 0)
			return inputBuffer.read(buffer);
		else return -1;
	}
	
	/**
	 * Writes data to output stream
	 * @param data data we want to send to other peers
	 * @throws IOException 
	 */
	public static void Write(byte[] data) throws IOException 
	{
		if(outputBuffer != null)
			outputBuffer.write(data);
	}
}