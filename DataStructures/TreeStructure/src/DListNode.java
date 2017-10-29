

public class DListNode {
	
	public String data;
	public DListNode next;
	public DListNode back;
	
	public DListNode(String data)
	{
		this(data,null,null);
	}
	
	public DListNode(String data,DListNode next,DListNode back)
	{
		this.data = data;
		this.next = next;
		this.back = back;
	}

	public String getData() {
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
