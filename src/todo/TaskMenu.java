package todo;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:13
 */
public class TaskMenu extends JMenu {

	private Task myTask;
	private JMenuItem deleteButton;
	private JMenuItem duplicateButton;
	private JMenuItem editButton;

	public TaskMenu(Task myTask) {
		this.myTask = myTask;
		JMenu mainMenu = new JMenu("Bearbeiten");
		add(mainMenu);
		deleteButton = new JMenuItem("Task löschen");
		mainMenu.add(deleteButton);
		duplicateButton = new JMenuItem("Task duplizieren");
		mainMenu.add(duplicateButton);
		editButton = new JMenuItem("Task bearbeiten");
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