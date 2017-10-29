

import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue{

	private DListNode firstNode;
	private DListNode lastNode;
	public String name;
	
	
	public StringDoubleEndedQueueImpl()
	{
		this("");
	}
	
	public StringDoubleEndedQueueImpl(String name)
	{
		firstNode = lastNode = null;
		this.name = name;
	}
	
	@Override
	public boolean isEmpty() {
		return firstNode == null && lastNode == null;
	}

	@Override
	public void addFirst(String item) {
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
	public String removeFirst() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			String x = firstNode.data;
			firstNode = firstNode.next;
			firstNode.back=null;
			return x;
		}
	}
	
	public void remove(String w){
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else {
			boolean flag=false;
			DListNode temp= firstNode;
			while(temp.next!=null){
				if (temp.data.equalsIgnoreCase(w)){
					flag=true;
					break;	
				}
				temp=temp.next;
			}
			if (flag==true){
				temp.back.next=temp.next;
				temp.next.back=temp.back;
			}
			
			
		
		}
	}

	@Override
	public void addLast(String item) {
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
	public String removeLast() throws NoSuchElementException {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			String x = lastNode.data;
			lastNode = lastNode.back;
			lastNode.next=null;
			return x;
		}
	}
	

	@Override
	public String getFirst() throws NoSuchElementException {
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
	public String getLast() throws NoSuchElementException {
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
	public boolean contains(String word){
		DListNode checkNode=firstNode;
		while(checkNode!=null){
			if(checkNode.data.equals(word)) return true;
			checkNode=firstNode.next;
		}
		return false;
	}

}
