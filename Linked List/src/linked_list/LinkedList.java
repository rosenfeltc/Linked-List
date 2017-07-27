package linked_list;

public class LinkedList 
{
	private Node head;
	
	public LinkedList()
	{
		this.head = null;
	}
	
	public void add(String name, String major, int id)
	{
		Node newNode = new Node(name, major, id);
		Node spot;
		
		// Empty list
		if(head == null)
		{
			head = newNode;
		}
		else
		{
			spot = search(newNode);
			newNode.next = spot.next;
			newNode.previous = spot;
		}
	}
	
	public Node search(Node reference)
	{
		reference = head;
		while(reference != null && (reference.data.getName().compareToIgnoreCase(reference.next.data.getName())) > 0)
		{
			reference = reference.next;
		}
		
		return reference;
	}
	private class Node
	{
		private Data data;
		private Node next;
		private Node previous;
		
		public Node(String name, String major, int id)
		{
			data = new Data(name, major, id);
			next = null;
			previous = null;
		}
	}		
}
