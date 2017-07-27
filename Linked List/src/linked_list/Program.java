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
			case 2:
			case 3:
			case 4:
			case 5:
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
	}
}
