package ufotron;

import org.lwjgl.util.vector.Vector2f;

public class SegmentItersection
{
	public static float CrossProduct(Vector2f begin, Vector2f end, Vector2f point)
	{
		return (end.x - begin.x)*(point.y - begin.y) - (point.x - begin.x)*(end.y - begin.y);
	}
	
	public static boolean IsOnSegment(Vector2f begin, Vector2f end, Vector2f point)
	{
		return (Math.min(begin.x, end.x) < point.x && point.x < Math.max(begin.x, end.x));
	}
	
	public static boolean DoIntersect(Vector2f p1, Vector2f p2, Vector2f p3, Vector2f p4)
	{
		float s1 = CrossProduct(p1, p3, p2);
		float s2 = CrossProduct(p1, p4, p2);
		float s3 = CrossProduct(p3, p1, p4);
		float s4 = CrossProduct(p3, p2, p4);
		
		if(((s1 > 0 && s2 < 0) || (s1 < 0 && s2 > 0)) && ((s3 > 0 && s4 < 0 ) || (s3 < 0 && s4 > 0)))
			return true;
		else if(s1 == 0  && IsOnSegment(p1, p2, p3))
			return true;
		else if(s2 == 0 && IsOnSegment(p1, p2, p4))
			return true;
		else if(s3 == 0 && IsOnSegment(p3, p4, p1))
			return true;
		else if(s4 == 0 && IsOnSegment(p3, p4, p2))
			return true;
		else return false;
	}
}
