package linked_list;

public class LinkedList 
{
	private Node root;
	
	public LinkedList()
	{
		root = null;
	}
	
	private Node getRoot()
	{
		return root;
	}
	
	private void setRoot(Node root)
	{
		this.root = root;
	}
	
	private boolean isEmpty()
	{
		return root == null;
	}
	
	public void add(String name, String major, int id)
	{
		Node newNode = new Node(name, major, id);
		Node spot;
		
		// Empty list
		if(isEmpty())
		{
			setRoot(newNode);
		}
		else
		{
			spot = search(newNode);
			
			// Special case insertion at beginning of the list
			if(spot == getRoot())
			{
				setRoot(newNode);
				newNode.setNext(spot);
				newNode.setPrevious(null);
				spot.setPrevious(newNode);
			}
			// Special case insertion at end of the list
			else if(spot.getNext() == null)
			{
				if(newNode.getData().getName().compareToIgnoreCase(spot.getData().getName()) > 0)
				{
					newNode.setNext(null);
					newNode.setPrevious(spot);
					spot.setNext(newNode);
				}
				else
				{
					newNode.setNext(spot);
					newNode.setPrevious(spot.getPrevious());
					spot.getPrevious().setNext(newNode);
					spot.setPrevious(newNode);
				}
			}
			// Anywhere else in the list
			else
			{
				newNode.setNext(spot);
				newNode.setPrevious(spot.getPrevious());
				spot.getPrevious().setNext(newNode);
				spot.setPrevious(newNode);
			}
			
		}
	}
	
	public Node search(Node reference)
	{
		Node traverse = getRoot();
		
		while(traverse.getNext() != null && (reference.getData().getName().compareToIgnoreCase(traverse.getData().getName())) > 0)
		{
			traverse = traverse.getNext();
		}
		
			return traverse;
		
	}
	
	public boolean search(String name)
	{
		if(isEmpty())
		{
			return false;
		}
		
		Node traverse = getRoot();
		
		while(traverse != null)
		{
			if(traverse.getData().getName().equalsIgnoreCase(name))
			{
				return true;
			}
			
			traverse = traverse.getNext();
		}
		
		return false;
	}
	
	public boolean delete(String name)
	{
		if(isEmpty())
		{
			return false;
		}
		
		Node traverse = getRoot();
		
		while(traverse != null)
		{
			if(traverse.getData().getName().equalsIgnoreCase(name))
			{
				// Special case first node in the list
				if(traverse.getPrevious() == null)
				{
					setRoot(traverse.getNext());
				}
				// Special case last node in the list
				else if(traverse.getNext() == null)
				{
					traverse.getPrevious().setNext(null);
				}
				// The rest of the list
				else
				{
					traverse.getPrevious().setNext(traverse.getNext());
					traverse.getNext().setPrevious(traverse.getPrevious());
				}
				
				return true;
			}
			// Keep checking through the list
			traverse = traverse.getNext();
		}
		
		return false;
	}
	
	public String print()
	{
		Node reference = getRoot();
		String theList = "";
		
		while(reference != null)
		{
			theList += reference.getData().getName() + " ";
			theList += reference.getData().getMajor() + " ";
			theList += reference.getData().getID() + "\n";
			
			reference = reference.next;
		}
		
		return theList;
	}
	
	// Node class
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
		
		public Data getData()
		{
			return data;
		}
		
		public Node getNext()
		{
			return next;
		}
		
		public void setNext(Node next)
		{
			this.next = next;
		}
		
		public Node getPrevious()
		{
			return previous;
		}
		
		public void setPrevious(Node previous)
		{
			this.previous = previous;
		}
	}		
}
