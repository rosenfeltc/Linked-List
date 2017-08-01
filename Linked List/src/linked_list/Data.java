/* This is the Data class which has three fields representing the Data contained in a Node. A String for a name, a String for the major and
 * and integer for the ID. A Node will have a reference to a Data object that can be used to obtain any of the three fields, therefore, this
 * class has a getter for each field. No setters are necessary for the operations that can be performed on the LinkedList and only a three
 * parameter constructor is allowed, meaning that all the data information must be provided in order to create the Data object.
 * Coded by Christopher Rosenfelt for CSI 213
 */
package linked_list;

public class Data 
{
	private String name;
	private String major;
	int id;
	
	// Three parameter constructor is the only constructor allowed
	// so that a Data object cannot be created if any one of the three
	// fields are missing
	public Data(String name, String major, int id)
	{
		this.name = name;
		this.major = major;
		this.id = id;
	}
	
	// Getter for the name
	public String getName()
	{
		return name;
	}
	
	// Getter for the major
	public String getMajor()
	{
		return major;
	}
	
	// Getter for the ID
	public int getID()
	{
		return id;
	}
}
