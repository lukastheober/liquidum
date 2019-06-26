package todo;

import javax.swing.*;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListDeletionWarningDialog extends MyOptionPane {

	public ListDeletionWarningDialog(Controller controller, ListOfTasks list){
		super(controller);
		
		int selection = JOptionPane.showConfirmDialog(null, "Sicher, dass Sie diese Liste l" + '\u00F6' + "schen wollen?",
				"Warnung!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if (selection == 0) {
			controller.removeList(list);
		}
		
	}

}