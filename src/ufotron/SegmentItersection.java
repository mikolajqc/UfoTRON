package ufotron;

import org.lwjgl.util.vector.Vector2f;

/**
 * Class handling collision detection via segment intersection
 * 
 */
public class SegmentItersection
{
	/**
	 * Modified product for 2 dimensional vectors
	 * @param begin beginning of first segment
	 * @param end ending of first segment
	 * @param point arbitrary point
	 * @return product
	 */
	public static float Product(Vector2f begin, Vector2f end, Vector2f point)
	{
		return (end.x - begin.x)*(point.y - begin.y) - (point.x - begin.x)*(end.y - begin.y);
	}
	
	/**
	 * Check if point lies on segment specified by its ends
	 * @param begin beginning of segment
	 * @param end ending of segment
	 * @param point point we want to check
	 * @return is point lying on segment?
	 */
	public static boolean IsOnSegment(Vector2f begin, Vector2f end, Vector2f point)
	{
		return (Math.min(begin.x, end.x) < point.x && point.x < Math.max(begin.x, end.x));
	}
	
	/**
	 * Check if 2 segments intersect
	 * @param p1 beginning of first segment
	 * @param p2 ending of first segment
	 * @param p3 beginning of second segment
	 * @param p4 ending of second segment
	 * @return do two segments intersect?
	 */
	public static boolean DoIntersect(Vector2f p1, Vector2f p2, Vector2f p3, Vector2f p4)
	{
		float s1 = Product(p1, p3, p2);
		float s2 = Product(p1, p4, p2);
		float s3 = Product(p3, p1, p4);
		float s4 = Product(p3, p2, p4);
		
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
