package ufotron;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import applicationState.*;

import java.io.*;
import java.net.*;

public class UfoTron extends BasicGame
{
	private static int width = 640;
	private static int height = 480;
	private static boolean isFullscreen = false;

///Server
	private static ServerSocket serverSocket = null;
	private static Socket socket = null;
	//public static BufferedInputStream inputBuffer = null;
	//public static BufferedOutputStream outputBuffer = null;
	private static DataInputStream inputBuffer = null;
	private static DataOutputStream outputBuffer = null;
	
	
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
		container.setVSync(true);
		container.setTargetFrameRate(60);
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
		Timer.updateTime();
		
		currentState.Update(container);
    }
 
	@Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
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
	
	public static int Read() throws IOException {
		
		if(inputBuffer.available() > 0) 
			return inputBuffer.read();
		else return -1;
	}
	public static void Write(int data) throws IOException {outputBuffer.write(data);};


	

}
