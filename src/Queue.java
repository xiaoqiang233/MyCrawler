
import java.util.*;

public class Queue {
	
	private LinkedList<String> queue = new LinkedList<String>();

	public void enQueue(String s)
	{
		queue.addLast(s);
	}
	
	public String deQueue()
	{
		return queue.removeFirst();
	}
	
	public boolean isQueueEmpty()
	{
		return queue.isEmpty();
	}
	
	public boolean contains(String s)
	{
		return queue.contains(s);
	}
	
	public boolean empty()
	{
		return queue.isEmpty();
	}
}
