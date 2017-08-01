/* This is the Window class that takes in a String and displays it to the user as a ScrollPane Window
 * Coded by Christopher Rosenfelt for CSI 213
 */

package linked_list;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Window extends JFrame
{
	public Window(String content)
	{
		// Make a text area with the String parameter and added to a ScrollPane
		JTextArea text = new JTextArea(content);
		JScrollPane scrollPane = new JScrollPane (text);
		
		// Window setting and add the ScrollPane content
		setSize(500, 500);
		setTitle("The List");
		add(scrollPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
