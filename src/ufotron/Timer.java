package ufotron;
import java.time.Duration;
import java.time.Instant;

/**
 * Class handling time connected issues
 * 
 */
public class Timer
{
	private static Instant lastFrameTime = Instant.now();
	private static double deltaTime;
	private static double totalTime = 0; 
	
	/**
	 * Update timers
	 */
	static void updateTime()
	{
		Instant newFrameTime = Instant.now();
		deltaTime = ((double)(Duration.between(lastFrameTime, newFrameTime).toNanos())/1000000000.0);
		totalTime += deltaTime;
		lastFrameTime = newFrameTime;
	}
	
	/**
	 * 
	 * @return deltaTime - amount of time passed since last frame (in seconds)
	 */
	public static double getDeltaTime() {return deltaTime;}
	
	/**
	 * 
	 * @return total application running time
	 */
	public static double getTotalTime() {return totalTime;}
}
