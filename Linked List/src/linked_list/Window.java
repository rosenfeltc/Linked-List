package linked_list;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Window extends JFrame
{
	public Window(String content)
	{
		JTextArea text = new JTextArea(content);
		JScrollPane scrollPane = new JScrollPane (text);
		
		setSize(500, 500);
		setTitle("The List");
		add(scrollPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
