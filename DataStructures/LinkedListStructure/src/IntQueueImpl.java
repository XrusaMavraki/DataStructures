
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue {
	
	
	private ListNode firstNode;
	private ListNode lastNode;
	public String name;

	
	public IntQueueImpl(String name)
	{
		firstNode = lastNode = null;
		this.name = name;
	}
	
	public IntQueueImpl()
	{
		this("");
	}
	 
	@Override
	public boolean isEmpty() {
		 return firstNode == null;
	}

	@Override
	public void put(int item) {
		if(isEmpty())
		{
			firstNode = new ListNode(item);
			lastNode=firstNode;
		}
		else
		{
			lastNode.next = new ListNode(item);
			lastNode = lastNode.next;
		}
	}

	@Override
	public int get() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			ListNode node_temp = firstNode;
			firstNode = node_temp.next;
			return node_temp.getData();
		}
	}

	@Override
	public int peek() throws NoSuchElementException {
		
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			return firstNode.getData();
		}
	}

	@Override
	 public void printQueue(PrintStream stream)
	{
		  if (isEmpty()) 
		  {
		   System.out.println("Empty Queue");
		   return;
		  }
		  
		  ListNode temp=firstNode;
		  
		  while (temp!=null)
		  {
		   stream.println(temp.getData());
		   temp=temp.getNext();
		  }
		  
		 }

	@Override
	 public int size(){
		
		  if (isEmpty()) return 0;
		  
		  int i=1;
		  ListNode node_temp = firstNode.getNext();
			  while (node_temp!=null)
			  {
			   i++;
			   node_temp=node_temp.getNext();
			  }
		  return i;
		 }
	

}
