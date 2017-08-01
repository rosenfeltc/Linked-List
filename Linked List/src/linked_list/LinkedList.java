/* This is the LinkedList class which just has the field root which is a reference object to a Node. The private Node class is also located inside
 * of the LinkedList class and a Node consists of three references, a reference to a Data object, and two Node references: next, and previous
 * which will be used to link the Nodes in the List. The LinkedList class has several methods that are used to set and get the root, check if
 * the list is empty, add a Node, search for a Node, delete a Node and print the List
 * Coded by Christopher Rosenfelt for CSI 213
 */
package linked_list;

public class LinkedList 
{
	// Field that will always reference the beginning of the list
	private Node root;
	
	// Initial no parameter constructor - not necessary but I like to have it explicit
	public LinkedList()
	{
		root = null;
	}
	
	// Getter
	private Node getRoot()
	{
		return root;
	}
	
	// Setter
	private void setRoot(Node root)
	{
		this.root = root;
	}
	
	// Check if the list is empty
	private boolean isEmpty()
	{
		return root == null;
	}
	
	// Method to add a Student node to the list in alphabetical order
	public void add(String name, String major, int id)
	{
		// Create the Student node
		Node newNode = new Node(name, major, id);
		// Declare a Node reference variable
		Node spot;
		
		// Empty list
		if(isEmpty())
		{
			// Start the list by having the root reference the new Student Node
			setRoot(newNode);
		}
		// List is not empty
		else
		{
			// Use the search Method to obtain the Node reference to the 'correct' spot
			// based on alphabetical order of the where the new Node should go in the list
			spot = search(newNode);
			
			// Special case insertion on a list containing one Node
			if(spot == getRoot() && spot.getNext() == null)
			{
				// We have to check to see if the new student Node should go before or after the only node in the list
				
				// New Node goes after the first Node
				if(name.compareToIgnoreCase(spot.getData().getName()) > 0)
				{
					newNode.setPrevious(spot);
					spot.setNext(newNode);
				}
				// New Node goes at the beginning of the list
				else
				{
					setRoot(newNode);
					newNode.setNext(spot);
					spot.setPrevious(newNode);
				}
			}
			// Special case insertion at beginning of the list
			else if(spot == getRoot())
			{
				setRoot(newNode);
				newNode.setNext(spot);
				spot.setPrevious(newNode);
			}
			// Special case insertion at end of the list
			else if(spot.getNext() == null)
			{
				// Because search only reaches the last Node of the list (otherwise one spot further would return null
				// which wouldn't be helpful) then we have to check to see if the new student Node should go before or after
				// the last Node of the list
				
				// New Node goes to the end of the list
				if(name.compareToIgnoreCase(spot.getData().getName()) > 0)
				{
					newNode.setPrevious(spot);
					spot.setNext(newNode);
				}
				// New Node goes right before the last Node of the list
				else
				{
					newNode.setNext(spot);
					newNode.setPrevious(spot.getPrevious());
					spot.getPrevious().setNext(newNode);
					spot.setPrevious(newNode);
				}
			}
			// Anywhere else in the list besides special cases
			else
			{
				newNode.setNext(spot);
				newNode.setPrevious(spot.getPrevious());
				spot.getPrevious().setNext(newNode);
				spot.setPrevious(newNode);
			}
		}
	}
	
	// Method that traverses through the linked list trying to find the correct placement of the Node reference parameter passed into it
	// by using alphabetical order through a Node by Node comparison of the data-field name
	public Node search(Node reference)
	{
		// Declare and initialize the Node reference that will be used to traverse the Linked-List while searching for the correct placement
		Node traverse = getRoot();
		
		// Stopping before reaching traverse == null because otherwise returning null wouldn't help the method that called it
		// this will require an additional comparison step for the special end of the list case that will be handled by the add method
		// Continue to traverse the LinkedList while the Name of the new Node is lexicographically higher than the current Node being
		// referenced.
		while(traverse.getNext() != null && (reference.getData().getName().compareToIgnoreCase(traverse.getData().getName())) > 0)
		{
			traverse = traverse.getNext();
		}
			// Return the reference to the Node of which the newNode should go directly before it
			return traverse;
	}
	
	// Different search method that takes in as a parameter a String name and searches the list Node-by-Node to see if the Node
	// with the name being search for exists in the Linked List
	public boolean search(String name)
	{
		// List is empty so the name won't be there
		if(isEmpty())
		{
			return false;
		}
		
		// Node reference used to traverse through the Linked-List
		Node traverse = getRoot();
		
		// More efficient than just traversing the entire list since the list is in alphabetical order
		// we can just stop when we've passed the spot in the list that should have had the Node
		while(traverse != null && name.compareToIgnoreCase(traverse.getData().getName()) >= 0)
		{
			// We found a match so return true
			if(traverse.getData().getName().equalsIgnoreCase(name))
			{
				return true;
			}
			// Keep traversing through the Linked-List
			traverse = traverse.getNext();
		}
		
		// The Node with the matching name was not found so return false
		return false;
	}
	
	// Method that traverse through the LinkedList looking for the Node with the inputted Name in order to delete it from the LinkedList
	public boolean delete(String name)
	{
		// The list is empty so unable to delete the Node with the given Name since it doesn't exist in the list
		if(isEmpty())
		{
			return false;
		}
		
		// A Node reference variable used to traverse the list
		
		Node traverse = getRoot();
		
		// More efficient than just traversing the entire list since the list is in alphabetical order
		// we can just stop when we've passed the spot in the list that should have had the Node
		while(traverse != null && name.compareToIgnoreCase(traverse.getData().getName()) >= 0)
		{
			if(traverse.getData().getName().equalsIgnoreCase(name))
			{
				// Special case first node in the list
				if(traverse.getPrevious() == null)
				{
					// Just move the root reference to the second Node in the list
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
	
	// Traverse through the entire linked list, creating a String that contains the Data fields of each Node as a newline for each
	// Node and then return the String so that it can be printed
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
		// A reference to a Data object that contains the Student information
		private Data data;
		// A double linked-list contains two Node references so that each Node has the ability to reference
		// its successor and predecessor if possible
		private Node next;
		private Node previous;
		
		// Node Constructor that takes the three parameters passed in and uses them to call the Data contructor
		private Node(String name, String major, int id)
		{
			data = new Data(name, major, id);
			next = null;
			previous = null;
		}
		
		// Getter
		private Data getData()
		{
			return data;
		}
		
		// Getter
		private Node getNext()
		{
			return next;
		}
		
		// Setter
		private void setNext(Node next)
		{
			this.next = next;
		}
		
		// Getter
		private Node getPrevious()
		{
			return previous;
		}
		
		// Setter
		private void setPrevious(Node previous)
		{
			this.previous = previous;
		}
	}		
}
