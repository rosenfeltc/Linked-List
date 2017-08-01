package linked_list;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SearchNode 
{
	public static void main(String[] args)
	{
		SearchNode list = new SearchNode();
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
		
		list.print();
	}
	private Node first, second, third, last;
	private int size;
	
	public SearchNode()
	{
		this.first = null;
		this.second = null;
		this.third = null;
		this.last = null;
		this.size = 0;
	}
	
	private int getSize()
	{
		return this.size;
	}
	
	private void setSize(int size)
	{
		this.size = size;
	}
	
	private boolean isEmpty()
	{
		return getSize() == 0;
	}
	
	private Node getFirst()
	{
		return this.first;
	}
	
	private void setFirst(Node first)
	{
		this.first = first;
	}
	
	private Node getSecond()
	{
		return this.second;
	}
	
	private void setSecond(Node second)
	{
		this.second = second;
	}
	
	private Node getThird()
	{
		return this.third;
	}
	
	private void setThird(Node third)
	{
		this.third = third;
	}
	
	private Node getLast()
	{
		return this.last;
	}
	
	private void setLast(Node last)
	{
		this.last = last;
	}
	
	public void add(String name, String major, int id)
	{
		Node newNode = new Node(name, major, id);
		
		if(isEmpty())
		{
			setFirst(newNode);
			setSize(getSize() + 1);
		}
		else if(getSize() <= 4)
		{
			Node traverse = getFirst();
			setSize(getSize() + 1);
			
			while(traverse.getNext() != null && newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
			{
				traverse = traverse.getNext();
			}
			
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
			else if(traverse == getFirst())
			{
				newNode.setNext(first);
				first.setPrevious(newNode);
				setFirst(newNode);
			}
			else if(traverse.getNext() == null)
			{
				if(newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
				{
					newNode.setPrevious(traverse);
					traverse.setNext(newNode);
				}
				else
				{
					newNode.setPrevious(traverse.getPrevious());
					newNode.setNext(traverse);
					traverse.getPrevious().setNext(newNode);
					traverse.setPrevious(newNode);
				}
			}
			else
			{
				newNode.setNext(traverse);
				newNode.setPrevious(traverse.getPrevious());
				traverse.getPrevious().setNext(newNode);
				traverse.setPrevious(newNode);
			}
			
			if(size == 4)
			{
				setSecond(first.getNext());
				setThird(second.getNext());
				setLast(third.getNext());
			}
		}
		else
		{
			Node traverse = search(name);
			setSize(getSize() + 1);
			
			if(traverse == getFirst())
			{
				newNode.setNext(first);
				first.setPrevious(newNode);
				setFirst(newNode);
			}
			else if(traverse.getNext() == null)
			{
				if(newNode.getName().compareToIgnoreCase(traverse.getName()) >= 0)
				{
					newNode.setPrevious(traverse);
					traverse.setNext(newNode);
					setLast(newNode);
				}
				else
				{
					newNode.setPrevious(traverse.getPrevious());
					newNode.setNext(traverse);
					traverse.getPrevious().setNext(newNode);
					traverse.setPrevious(newNode);
				}
			}
			else
			{
				newNode.setNext(traverse);
				newNode.setPrevious(traverse.getPrevious());
				traverse.getPrevious().setNext(newNode);
				traverse.setPrevious(newNode);
			}
			
			if(traverse.getName().compareToIgnoreCase(second.getName()) < 0)
			{
				setSecond(second.getPrevious());
				setThird(third.getPrevious());
			}
			else if(traverse.getName().compareToIgnoreCase(third.getName()) < 0)
			{
				setSecond(second.getNext());
			}
			else if(traverse.getName().compareToIgnoreCase(last.getName()) < 0)
			{
				setThird(third.getNext());
			}
		}
	}
	
	public Node search(String name)
	{
		Node traverse;
		
		if(size < 4)
		{
			traverse = getFirst();
			
			while(traverse.getNext() != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
			{
				traverse = traverse.getNext();
			}
			
			return traverse;
		}
		else
		{
			if(name.compareToIgnoreCase(second.getName()) <= 0)
			{
				if((Math.abs(name.compareToIgnoreCase(first.getName()))) <= (Math.abs(name.compareToIgnoreCase(second.getName()))))
				{
					traverse = getFirst();
					
					while(traverse.getNext() != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
					{
						traverse = traverse.getNext();
					}
				}
				else
				{
					traverse = getSecond();
					
					while(traverse.getPrevious() != null && name.compareToIgnoreCase(traverse.getName()) < 0)
					{
						traverse = traverse.getPrevious();
					}
				}
				
				return traverse;
			}
			else if(name.compareToIgnoreCase(third.getName()) <= 0)
			{
				if((Math.abs(name.compareToIgnoreCase(second.getName()))) <= (Math.abs(name.compareToIgnoreCase(third.getName()))))
				{
					traverse = getSecond();
					
					while(traverse.getNext() != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
					{
						traverse = traverse.getNext();
					}
				}
				else
				{
					traverse = getThird();
					
					while(traverse.getPrevious() != null && name.compareToIgnoreCase(traverse.getName()) < 0)
					{
						traverse = traverse.getPrevious();
					}
				}
				
				return traverse;
			}
			else if(name.compareToIgnoreCase(last.getName()) <= 0)
			{
				if((Math.abs(name.compareToIgnoreCase(third.getName()))) <= (Math.abs(name.compareToIgnoreCase(last.getName()))))
				{
					traverse = getThird();
					
					while(traverse.getNext() != null && name.compareToIgnoreCase(traverse.getName()) >= 0)
					{
						traverse = traverse.getNext();
					}
				}
				else
				{
					traverse = getLast();
					
					while(traverse.getPrevious() != null && name.compareToIgnoreCase(traverse.getName()) < 0)
					{
						traverse = traverse.getPrevious();
					}
				}
				
				return traverse;
			}
			else
			{
				return getLast();
			}
		}
	}
	
	public void print()
	{
		Node traverse = getFirst();
		
		while(traverse != null)
		{
			System.out.println(traverse.getName() + ", " + traverse.getMajor() + ", " + traverse.getID());
			traverse = traverse.getNext();
		}
	}
		
	private class Node
	{
		private String name, major;
		private int id;
		Node next, previous;
		
		private Node(String name, String major, int id)
		{
			this.name = name;
			this.major = major;
			this.id = id;
			this.next = null;
			this.previous = null;
		}
		
		private String getName()
		{
			return this.name;
		}
		
		private String getMajor()
		{
			return this.major;
		}
		
		private int getID()
		{
			return this.id;
		}
		
		private void setNext(Node next)
		{
			this.next = next;
		}
		
		private Node getNext()
		{
			return this.next;
		}
		
		private void setPrevious(Node previous)
		{
			this.previous = previous;
		}
		
		private Node getPrevious()
		{
			return this.previous;
		}
	}
}
