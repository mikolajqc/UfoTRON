package ufotron;
import java.time.Duration;
import java.time.Instant;

public class Timer
{
	private static Instant lastFrameTime = Instant.now();
	private static double deltaTime;
	private static double totalTime = 0; 
	
	static void updateTime()
	{
		Instant newFrameTime = Instant.now();
		deltaTime = ((double)(Duration.between(lastFrameTime, newFrameTime).toNanos())/1000000000.0);
		totalTime += deltaTime;
		lastFrameTime = newFrameTime;
		//System.out.println(totalTime);
	}
	
	public static double getDeltaTime() {return deltaTime;}
	public static double getTotalTime() {return totalTime;}
}
