

public class DListNode {
	
	public int data;
	public DListNode next;
	public DListNode back;
	
	public DListNode(int data)
	{
		this(data,null,null);
	}
	
	public DListNode(int data,DListNode next,DListNode back)
	{
		this.data = data;
		this.next = next;
		this.back = back;
	}

	public int getData() {
		return data;
	}

	public DListNode getNext() {
		return next;
	}
	
	public DListNode getBack()
	{
		return back;
	}
}
