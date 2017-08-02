/* This is the SearchNode class for the Bonus part of the homework. Very similar in structure to the regular homework except for the addition of
 * 4 different Node pointers that help improve the speed/efficiency of the search process.
 * Coded by Christopher Rosenfelt for CSI 213
 */
package linked_list;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SearchNode 
{
	// Global variables
	public static SearchNode list = new SearchNode();
	public static String[] options = {"Scroll Pane", "Search", "Exit"};
	
	// Main method to allow the user to open the file
	public static void main(String[] args)
	{
		JOptionPane.showMessageDialog(null, "Please select the studentinfo.csv file from the correct directory");
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Value", "csv");
		fileChooser.setFileFilter(filter);
		fileChooser.showOpenDialog(fileChooser);
		File toOpen = fileChooser.getSelectedFile();
		String[] data = new String[3];
		
		try
		{
			Scanner fileScanner = new Scanner(toOpen);
			while(fileScanner.hasNextLine())
			{
				data = fileScanner.nextLine().split(",");
				list.add(data[0], data[1], Integer.parseInt(data[2]));
			}
			fileScanner.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		mainMenu();
	}// End of Main
	
	public static void mainMenu()
	{
		// Display the three different options to the user and execute the selection
		int selection = JOptionPane.showOptionDialog(null, "Please select an option", "Linked List", 0, 2, null, options, 0);
		
		// Display list in a ScrollPane window
		if(selection == 0)
		{
			SearchNodeWindow newWindow = new SearchNodeWindow(list.display());
		}
		// Allow the user to search by last name
		else if(selection == 1)
		{
			// Obtain the last name of the student we want to search for
			JOptionPane.showMessageDialog(null, "Let's search for a student by last name!");
			String name = JOptionPane.showInputDialog("Please provide the student's last name: ");
			
			// Use the search method from LinkedList class to search for the student, the method is not case sensitive	
			if(list.search(name))
			{
				JOptionPane.showMessageDialog(null, "The student by the last name " + name + " was found in the list!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The student by the last name " + name + " was not found in the list!");
			}
				
				mainMenu();
		}
		// Exit
		else
		{
			System.exit(0);
		}
	}// End of mainMenu method
	
	// SearchNode fields
	// The four different Node references and an int variable to keep track of the size of the Linked-List
	private Node first, second, third, last;
	private int size;
	
	// Constructor
	public SearchNode()
	{
		this.first = null;
		this.second = null;
		this.third = null;
		this.last = null;
		this.size = 0;
	}
	
	// Getter
	private int getSize()
	{
		return this.size;
	}
	
	// Setter
	private void setSize(int size)
	{
		this.size = size;
	}
	
	// Is the list empty?
	private boolean isEmpty()
	{
		return getSize() == 0;
	}
	
	// Getter
	private Node getFirst()
	{
		return this.first;
	}
	
	// Setter
	private void setFirst(Node first)
	{
		this.first = first;
	}
	
	// Getter
	private Node getSecond()
	{
		return this.second;
	}
	
	// Setter
	private void setSecond(Node second)
	{
		this.second = second;
	}
	
	// Getter
	private Node getThird()
	{
		return this.third;
	}
	
	// Setter
	private void setThird(Node third)
	{
		this.third = third;
	}
	
	// Getter
	private Node getLast()
	{
		return this.last;
	}
	
	// Setter
	private void setLast(Node last)
	{
		this.last = last;
	}
	
	// Add method that takes as input the string name, string major and int id, creates a new Student Node with it
	// and adds it to the list in alphabetical order, uses the faster search method when the size of the list is 4 or more
	public void add(String name, String major, int id)
	{
		Node newNode = new Node(name, major, id);
		
		// List is empty so add the new Student Node
		if(isEmpty())
		{
			setFirst(newNode);
			setSize(getSize() + 1); // Keep track of the size of the list
		}
		// Size of the linked list is less than 4 so use the regular linear search
		else if(getSize() < 4)
		{
			Node traverse = search(newNode);
			setSize(getSize() + 1);
			
			// Special case scenario when the list contains one Student Node
			if(traverse == getFirst() && traverse.getNext() == null)
			{
				// We have to check to see if the new student Node should go before or after the only node in the list
				
				// New Node goes after the first Node
				if(name.compareToIgnoreCase(traverse.getName()) > 0)
				{
					newNode.setPrevious(traverse);
					traverse.setNext(newNode);
				}
				// New Node goes at the beginning of the list
				else
				{
					setFirst(newNode);
					newNode.setNext(traverse);
					traverse.setPrevious(newNode);
				}
			}
			// Special case of insertion at the beginning of the list
			else if(traverse == getFirst())
			{
				newNode.setNext(first);
				first.setPrevious(newNode);
				setFirst(newNode);
			}
			// Special case insertion potentially at the end of the list
			else if(traverse.getNext() == null)
			{
				// Insertion at end of the list
				if(newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
				{
					newNode.setPrevious(traverse);
					traverse.setNext(newNode);
				}
				// Insertion before the last node in the list
				else
				{
					newNode.setPrevious(traverse.getPrevious());
					newNode.setNext(traverse);
					traverse.getPrevious().setNext(newNode);
					traverse.setPrevious(newNode);
				}
			}
			// Insertion anywhere else in the list
			else
			{
				newNode.setNext(traverse);
				newNode.setPrevious(traverse.getPrevious());
				traverse.getPrevious().setNext(newNode);
				traverse.setPrevious(newNode);
			}
			
			// Special case in which we have exactly 4 Nodes and can have each reference point to each respective Node
			if(getSize() == 4)
			{
				setSecond(first.getNext());
				setThird(second.getNext());
				setLast(third.getNext());
			}
		}
		// Size is greater than 4 with the current addition
		else
		{
			Node traverse = search(newNode);
			setSize(getSize() + 1);
			
			// Special case insertion at the beginning of the list
			if(traverse == getFirst())
			{
				newNode.setNext(first);
				first.setPrevious(newNode);
				setFirst(newNode); // make sure to update the first Node reference accordingly
			}
			// Special case insertion potentially at end of the list
			else if(traverse.getNext() == null)
			{
				// Insertion at the end of the list
				if(newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
				{
					newNode.setPrevious(traverse);
					traverse.setNext(newNode);
					setLast(newNode); // make sure to update the last Node reference accordingly
				}
				// Insertion right before the last node in the list
				else
				{
					newNode.setPrevious(traverse.getPrevious());
					newNode.setNext(traverse);
					traverse.getPrevious().setNext(newNode);
					traverse.setPrevious(newNode);
				}
			}
			// Insertion anywhere else in the list
			else
			{
				newNode.setNext(traverse);
				newNode.setPrevious(traverse.getPrevious());
				traverse.getPrevious().setNext(newNode);
				traverse.setPrevious(newNode);
			}
			
			// Updating the reference variables depending on where the new node was inserted so
			// as to have an approximately equal spacing between the reference Nodes keeping in mind
			// that the first and last nodes are previously updated if necessary
			//Insertion happened between the first and second reference nodes
			if(traverse.getName().compareToIgnoreCase(second.getName()) < 0)
			{
				setSecond(second.getPrevious());
				setThird(third.getPrevious());
			}
			// Insertion happened between the second and third reference nodes
			else if(traverse.getName().compareToIgnoreCase(third.getName()) < 0)
			{
				setSecond(second.getNext());
			}
			// Insertion happened between the third and last reference nodes
			else if(traverse.getName().compareToIgnoreCase(last.getName()) < 0)
			{
				setThird(third.getNext());
			}
		}
	}
	
	// Search method that uses 4 reference variables once the size of the list is 4 or more, this search method is used in conjunction
	// with the add method to specifically add new Student Nodes in alphabetical order
	public Node search(Node newNode)
	{
		Node traverse;
		
		// If the size of the list is less than 4 then just find the position with regular linear search from the beginning of the list
		if(getSize() < 4)
		{
			traverse = getFirst();
			
			while(traverse.getNext() != null && newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
			{
				traverse = traverse.getNext();
			}
			
			return traverse;
		}
		// Size of the list is 4 or greater so incorporate all 4 reference nodes in order to speed up the search
		else
		{
			// Student node should be inserted between first and second reference nodes (inclusive)
			if(newNode.getName().compareToIgnoreCase(second.getName()) <= 0)
			{
				// Checking to see if it's faster to start at the first node going left to right
				if((Math.abs(newNode.getName().compareToIgnoreCase(first.getName()))) <=
						(Math.abs(newNode.getName().compareToIgnoreCase(second.getName()))))
				{
					traverse = getFirst();
					
					while(traverse.getNext() != null && newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
					{
						traverse = traverse.getNext();
					}
				}
				// Faster to start at the second node going right to left
				else
				{
					traverse = getSecond();
					
					while(traverse.getPrevious() != null && newNode.getName().compareToIgnoreCase(traverse.getPrevious().getName()) < 0)
					{
						traverse = traverse.getPrevious();
					}
				}
				
				return traverse;
			}
			// Student node should be inserted between second and third reference nodes (inclusive)
			else if(newNode.getName().compareToIgnoreCase(third.getName()) <= 0)
			{
				// Check to see if faster to start at second Node going left to right
				if((Math.abs(newNode.getName().compareToIgnoreCase(second.getName()))) <=
						(Math.abs(newNode.getName().compareToIgnoreCase(third.getName()))))
				{
					traverse = getSecond();
					
					while(traverse.getNext() != null && newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
					{
						traverse = traverse.getNext();
					}
				}
				// faster to start at third node going right to left
				else
				{
					traverse = getThird();
					
					while(traverse.getPrevious() != null && newNode.getName().compareToIgnoreCase(traverse.getPrevious().getName()) < 0)
					{
						traverse = traverse.getPrevious();
					}
				}
				
				return traverse;
			}
			// Student node should be inserted between third and last reference nodes (inclusive)
			else if(newNode.getName().compareToIgnoreCase(last.getName()) <= 0)
			{
				// Faster to start at third refernce node going left to right
				if((Math.abs(newNode.getName().compareToIgnoreCase(third.getName()))) <=
						(Math.abs(newNode.getName().compareToIgnoreCase(last.getName()))))
				{
					traverse = getThird();
					
					while(traverse.getNext() != null && newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
					{
						traverse = traverse.getNext();
					}
				}
				// Faster to start at last reference node going right to left
				else
				{
					traverse = getLast();
					
					while(traverse.getPrevious() != null && newNode.getName().compareToIgnoreCase(traverse.getPrevious().getName()) < 0)
					{
						traverse = traverse.getPrevious();
					}
				}
				
				// Return the Node reference to which the new Node should be inserted right before of
				return traverse;
			}
			else
			{
				return getLast();
			}
		}
	}
	
	// Search method that is very similar to the prior search method except that it takes in a String name
	// that is used to search the Linked List and returns a boolean as a way to let the user know if the
	// Node with the passed in name was found or not found in the list
	public boolean search(String name)
	{
		Node traverse;
		
		// Size is less than 4 so just use the regular way of traversing through the list
		if(getSize() < 4)
		{
			traverse = getFirst();
			
			while(traverse != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
			{
				if(name.equalsIgnoreCase(traverse.getName()))
				{
					return true;
				}
				traverse = traverse.getNext();
			}
			
			return false;
		}
		// Size of the list is bigger than 4 so check through the 4 reference nodes to find
		// the most efficient way to search for the name in the list
		else
		{
			// Name would lexicographically be located between the first and second reference nodes (inclusive)
			if(name.compareToIgnoreCase(second.getName()) <= 0)
			{
				// Faster to start at the first node and search left to right
				if((Math.abs(name.compareToIgnoreCase(first.getName()))) <= (Math.abs(name.compareToIgnoreCase(second.getName()))))
				{
					traverse = getFirst();
					
					while(traverse != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
					{
						// name found in the list return true
						if(name.equalsIgnoreCase(traverse.getName()))
						{
							return true;
						}
						traverse = traverse.getNext();
					}
				}
				// Faster to start at the second node and search right to left
				else
				{
					traverse = getSecond();
					
					while(traverse != null && name.compareToIgnoreCase(traverse.getName()) <= 0)
					{
						// Name found in the list return true
						if(name.equalsIgnoreCase(traverse.getName()))
						{
							return true;
						}
						traverse = traverse.getPrevious();
					}
				}
				// Name not found return false
				return false;
			}
			// Name would lexicographically be located between the second and third reference nodes (inclusive)
			else if(name.compareToIgnoreCase(third.getName()) <= 0)
			{
				// Faster to start at the second node and search left to right
				if((Math.abs(name.compareToIgnoreCase(second.getName()))) <= (Math.abs(name.compareToIgnoreCase(third.getName()))))
				{
					traverse = getSecond();
					
					while(traverse != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
					{
						// Name found in the list return true
						if(name.equalsIgnoreCase(traverse.getName()))
						{
							return true;
						}
						traverse = traverse.getNext();
					}
				}
				// Faster to start at the third node and search right to left
				else
				{
					traverse = getThird();
					
					while(traverse != null && name.compareToIgnoreCase(traverse.getName()) <= 0)
					{
						// Name found in the list return true
						if(name.equalsIgnoreCase(traverse.getName()))
						{
							return true;
						}
						traverse = traverse.getPrevious();
					}
				}
				// Name not found return false
				return false;
			}
			else
			{
				// Name would lexicographically be located between the third and last reference nodes (inclusive)
				if((Math.abs(name.compareToIgnoreCase(third.getName()))) <= (Math.abs(name.compareToIgnoreCase(last.getName()))))
				{
					traverse = getThird();
	
					while(traverse != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
					{
						// Name found in the list return true
						if(name.equalsIgnoreCase(traverse.getName()))
						{
							return true;
						}
						traverse = traverse.getNext();
					}
				}
				else
				{
					traverse = getLast();
					
					while(traverse != null && name.compareToIgnoreCase(traverse.getName()) <= 0)
					{
						// Name found in the list return true
						if(name.equalsIgnoreCase(traverse.getName()))
						{
							return true;
						}
						traverse = traverse.getPrevious();
					}
				}
				// Name not found in the list return false
				return false;
			}
		}
	}
	
	// Method that traverse through the entire list, adding a newline string out of each Node's fields to ultimately return
	// a String that could be then used to display the entire list
	public String display()
	{
		Node traverse = getFirst();
		String content = new String();
		
		while(traverse != null)
		{
			content += traverse.getName() + " " + traverse.getMajor() + " " + traverse.getID() + "\n";
			traverse = traverse.getNext();
		}
		
		return content;
	}
	
	// Private node class
	private class Node
	{
		// Node variables, doubly linked list so both a next and previous Node reference
		private String name, major;
		private int id;
		Node next, previous;
		
		// Constructor
		private Node(String name, String major, int id)
		{
			this.name = name;
			this.major = major;
			this.id = id;
			this.next = null;
			this.previous = null;
		}
		
		// Getter
		private String getName()
		{
			return this.name;
		}
		
		// Getter
		private String getMajor()
		{
			return this.major;
		}
		
		// Getter
		private int getID()
		{
			return this.id;
		}
		
		// Setter
		private void setNext(Node next)
		{
			this.next = next;
		}
		
		// Getter
		private Node getNext()
		{
			return this.next;
		}
		
		// Setter
		private void setPrevious(Node previous)
		{
			this.previous = previous;
		}
		
		// Getter
		private Node getPrevious()
		{
			return this.previous;
		}
	}
}
