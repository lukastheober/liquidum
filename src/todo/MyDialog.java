package todo;

import javax.swing.JDialog;

/**
 * @author benja
 * @version 1.0
 * @created 21-Mai-2019 12:00:39
 */
public abstract class MyDialog extends JDialog {

	public Controller controller;

	/**
	 * @param controller: the Controller this dialog ansers to
	 */
	public MyDialog(Controller controller){
		this.controller = controller;
	}

}