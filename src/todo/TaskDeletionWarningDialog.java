package todo;

import javax.swing.JOptionPane;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:13
 */
public class TaskDeletionWarningDialog extends MyOptionPane {

	public TaskDeletionWarningDialog(Controller controller, Task task){
		super(controller);
		
		int selection = JOptionPane.showConfirmDialog(null, "Sicher, dass Sie diese Aufgabe l" + '\u00F6' + "schen wollen?",
				"Warnung!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if (selection == 0) {
			controller.removeTask(task);
		}
		
	}

}