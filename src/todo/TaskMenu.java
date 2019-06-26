package todo;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:13
 */
public class TaskMenu extends JMenuBar {

	private Task myTask;
	private JMenuItem deleteButton;
	private JMenuItem duplicateButton;
	private JMenuItem editButton;

	public TaskMenu(Task myTask) {
		
		this.myTask = myTask;
		setPreferredSize(new Dimension(25, 25));
		JMenu mainMenu = new JMenu();
		mainMenu.setPreferredSize(new Dimension(25, 25));
	
		ImageIcon icon = new ImageIcon("penIcon.png");
		mainMenu.setIcon(icon);
		
		add(mainMenu);
		deleteButton = new JMenuItem("Aufgabe löschen");
		mainMenu.add(deleteButton);
		duplicateButton = new JMenuItem("Aufgabe duplizieren");
		mainMenu.add(duplicateButton);
		editButton = new JMenuItem("Aufgabe bearbeiten");
		mainMenu.add(editButton);
	}
	
	public Task getTask() {
		return myTask;
	}
	
	public JMenuItem getDeleteButton() {
		return deleteButton;
	}
	public JMenuItem getDuplicateButton() {
		return duplicateButton;
	}
	public JMenuItem getEditButton() {
		return editButton;
	}

}