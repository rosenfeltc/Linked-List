/* This is the program class that contains the main method as well as the mainMenu method that interacts with the user. It uses
 * the methods from the LinkedList class to perform the operations requested by the user
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
	final static String[] OPTIONS = {"Open File", "Scroll Pane", "Add", "Delete", "Search", "Exit"};
	static LinkedList theList = new LinkedList();
	
	public static void main(String[] args)
	{
		mainMenu();
	}
	
	public static void mainMenu()
	{
		int selection = JOptionPane.showOptionDialog(null, "Welcome!\nPlease choose what you would like me to do\n", "Please select an option",
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
			Scanner fileScanner = new Scanner(toOpen);
			while(fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				students = line.split(",");	
				
				theList.add(students[0], students[1], Integer.parseInt(students[2]));
			}
			
			fileScanner.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		mainMenu();
	}// END openFile method
	
	public static void scrollPane()
	{
		Window theWindow = new Window(theList.print());
		mainMenu();
	}
	
	public static void addStudent()
	{
		JOptionPane.showMessageDialog(null,"Let's add a new student!");
		String name = JOptionPane.showInputDialog("Please provide the student's name: ");
		String major = JOptionPane.showInputDialog("Please provide the student's major: ");
		int id =  Integer.parseInt(JOptionPane.showInputDialog("Please provide the student's id: "));
		
		theList.add(name, major, id);
		JOptionPane.showMessageDialog(null, "The student was added to the list!");
		mainMenu();
	}
	
	public static void searchStudent()
	{
		JOptionPane.showMessageDialog(null, "Let's search for a student by last name!");
		String name = JOptionPane.showInputDialog("Please provide the student's last name: ");
		
			if(theList.search(name))
			{
				JOptionPane.showMessageDialog(null, "The studen by the last name " + name + " was found in the list!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The studen by the last name " + name + " was not found in the list!");
			}
			
			mainMenu();
	}
	
	public static void deleteStudent()
	{
		JOptionPane.showMessageDialog(null, "Let's delete a student from the list!");
		String name = JOptionPane.showInputDialog("Please provide the student's last name that you want to delete: ");
		
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
