

import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntDoubleEndedQueueImpl implements IntDoubleEndedQueue{

	private DListNode firstNode;
	private DListNode lastNode;
	public String name;
	
	
	public IntDoubleEndedQueueImpl()
	{
		this("");
	}
	
	public IntDoubleEndedQueueImpl(String name)
	{
		firstNode = lastNode = null;
		this.name = name;
	}
	
	@Override
	public boolean isEmpty() {
		return firstNode == null && lastNode == null;
	}

	@Override
	public void addFirst(int item) {
		if(isEmpty())
		{
			firstNode = lastNode = new DListNode(item);
		}
		else
		{
			firstNode = new DListNode(item,firstNode,null);
			firstNode.next.back = firstNode;
		}
	}

	@Override
	public int removeFirst() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			int x = firstNode.data;
			firstNode = firstNode.next;
			return x;
		}
	}

	@Override
	public void addLast(int item) {
		if(isEmpty())
		{
			firstNode = lastNode = new DListNode(item);
		}
		else
		{
			lastNode = new DListNode(item,null,lastNode);
			lastNode.back.next = lastNode;
		}
		
	}

	@Override
	public int removeLast() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			int x = lastNode.data;
			lastNode = lastNode.back;
			return x;
		}
	}

	@Override
	public int getFirst() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			return firstNode.data;
		}
	}

	@Override
	public int getLast() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			return lastNode.data;
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
		  
		  DListNode temp=firstNode;
		  
		  while (temp!=null)
		  {
		   stream.print(temp.data);
		   temp=temp.next;
		   
		  }
		  
		
	}

	@Override
	public int size() 
	{
		 if (isEmpty()) return 0;
		  
		  int i=1;
		  DListNode node_temp = firstNode.getNext();
			  while (node_temp!=null)
			  {
			   i++;
			   node_temp=node_temp.getNext();
			  }
		  return i;
		 
	}

}
