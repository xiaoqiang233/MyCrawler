import java.util.*;

public class LinkQueue {

	private static Set<String> visitedUrl = new HashSet<String>();
	private static Queue unVisitedUrl = new Queue();
	
	public static Queue getUnvisitedUrl()
	{
		return unVisitedUrl;
	}
	
	public static void addVisitedUrl(String url)
	{
		visitedUrl.add(url);
	}
	
	public static void removeVisitedUrl(String url)
	{
		visitedUrl.remove(url);
	}
	
	public static String unVisitedUrlDeQueue()
	{
		return unVisitedUrl.deQueue();
	}
	
	public static void addUnvisitedUrl(String url)
	{
		if(url!=null&&!url.trim().equals("")&&!visitedUrl.contains(url)&&!unVisitedUrl.contains(url))
		{
			unVisitedUrl.enQueue(url);
		}
	}
	
	public static int getVisitedUrlNum()
	{
		return visitedUrl.size();
	}
	
	public static boolean unVisitedUrlIsEmpty(){
		return unVisitedUrl.empty();
	}
}
