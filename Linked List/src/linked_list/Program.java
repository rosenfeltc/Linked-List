/* This is the program class that contains the main method as well as the mainMenu method that interacts with the user. It uses
 * the methods from the LinkedList class to perform the operations requested by the user on the Linked List.
 * Coded by Christopher Rosenfelt for CSI 213
 */
package linked_list;

// Import the necessary libraries/packages
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class Program 
{
	// Options to be provided to the user through JPane option dialog
	final static String[] OPTIONS = {"Open File", "Scroll Pane", "Add", "Search", "Delete", "Exit"};
	// The List object that the user will interact with
	static LinkedList theList = new LinkedList();
	
	// Main method simply calls the mainMenu() method which will allow for the
	// looping of certain actions
	public static void main(String[] args)
	{
		mainMenu();
	}
	
	// Method that displays the menu of options to the user and then either executes or calls the appropriate
	// method to execute the user's selection
	public static void mainMenu()
	{
		int selection = JOptionPane.showOptionDialog(null, "Welcome!\nPlease select a Linked-List option\n", "Please select an option",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, OPTIONS, 0);
		
		switch(selection)
		{
			case 0:
				openFile();
				break;
			case 1:
				scrollPane();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				searchStudent();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				System.exit(0);
				break;
		}
	}
	
	// Method that allows the user to choose the studentinfo.csv file from its stored location and then
	// reads it line by line as a string passing the necessary parameters to the add method of LinkedList in order
	// to create each student as a node and insert it in alphabetical order in the linked list
	public static void openFile()
	{
		// Allow the user to chose the File but only show .csv files
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Value", "csv");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.showOpenDialog(fileChooser);
		File toOpen = fileChooser.getSelectedFile();
		
		// Declare the Strings references that will be needed
		String line;
		String[] students = new String[3];
		
		try
		{
			// Scan each line of the file
			Scanner fileScanner = new Scanner(toOpen);
			while(fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				// Split the line into three parameters
				students = line.split(",");	
				
				// Add the student to the list by passing the three parameters to the add method
				theList.add(students[0], students[1], Integer.parseInt(students[2]));
			}
			
			fileScanner.close();
		}
		// Check for any errors in passing the file to the Scanner
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		mainMenu();
	}// END openFile method
	
	// Method that calls the print method from the LinkedList class to obtain a String that contains the Student information
	// from each Node in the LinkedList as a separate line and passes that information to the Window class in order to display
	// it as a Scroll Pane window
	public static void scrollPane()
	{
		Window theWindow = new Window(theList.print());
	}
	
	// Method that allows the user to add the student by entering the Name, Major and ID information. Calls the add method from the
	// LinkedList class and then finally confirms to the user that the student was added successfully
	public static void addStudent()
	{
		JOptionPane.showMessageDialog(null,"Let's add a new student!");
		String name = JOptionPane.showInputDialog("Please provide the student's name: ");
		String major = JOptionPane.showInputDialog("Please provide the student's major (abbreviated form): ");
		int id =  Integer.parseInt(JOptionPane.showInputDialog("Please provide the student's id: "));
		
		theList.add(name, major, id);
		JOptionPane.showMessageDialog(null, "The student, " + name + ", was successfully added to the list!");
		mainMenu();
	}
	
	// Method that allows a user to search the Linked-List for the desired student by last name
	// The method tells the user only if the student is or isn't in the Linked-List
	public static void searchStudent()
	{
		// Obtain the last name of the student we want to search for
		JOptionPane.showMessageDialog(null, "Let's search for a student by last name!");
		String name = JOptionPane.showInputDialog("Please provide the student's last name: ");
		
		// Use the search method from LinkedList class to search for the student, the method is not case sensitive	
		if(theList.search(name))
		{
			JOptionPane.showMessageDialog(null, "The student by the last name " + name + " was found in the list!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "The student by the last name " + name + " was not found in the list!");
		}
			
			mainMenu();
	}
	
	// Method that searches for the Student name inputted by the user and if found, deletes the student from the Linked-List
	// Provides a confirmation message to the user if the Student was found and deleted from the list, otherwise it let's
	// the user know that it was unable to delete the Student
	public static void deleteStudent()
	{
		// Obtain the last name of the Student that the user wishes to delete from the Linked-List
		JOptionPane.showMessageDialog(null, "Let's delete a student from the list!");
		String name = JOptionPane.showInputDialog("Please provide the student's last name that you want to delete: ");
		
		// Call the delete method from LinkedList class to search and delete the student, the method is not case sensitive 
		if(theList.delete(name))
		{
			JOptionPane.showMessageDialog(null, "The student by the last name " + name + " was successfully deleted!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Unable to delete the student by the last name " + name +"!");
		}
		
		mainMenu();
	}
}
