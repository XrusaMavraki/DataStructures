

public class ListNode {
	
	public int data;
	public ListNode next;
	
	public ListNode(int data)
	{
		this(data,null);
	}
	
	public ListNode(int data,ListNode next)
	{
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public ListNode getNext() {
		return next;
	}
}
