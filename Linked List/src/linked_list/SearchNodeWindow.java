/* This is the SearchNodeWindow class that takes in a String and displays it to the user as a ScrollPane Window for the Bonus SearchNode class
 * Coded by Christopher Rosenfelt for CSI 213
 */
package linked_list;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class SearchNodeWindow extends JFrame
{
	public SearchNodeWindow(String content)
	{
		// Make a text area with the String parameter and added to a ScrollPane
		JTextArea text = new JTextArea(content);
		text.setEditable(false);
		JLabel theLabel = new JLabel("Student List:");
		JScrollPane scrollPane = new JScrollPane (text);
		
		// Make and set the buttons
		JPanel buttonPanel = new JPanel();
		JButton mainMenu = new JButton("Main Menu");
		JButton exit = new JButton("Exit");
		buttonPanel.add(mainMenu);
		buttonPanel.add(exit);
		
		mainMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				SearchNode.mainMenu();
			}
		});
		
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});		
		
		// Window settings
		setSize(500, 500);
		setTitle("The List");
		add(theLabel, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
