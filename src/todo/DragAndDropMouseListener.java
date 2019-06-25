package todo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author benja
 * @version 1.0
 * @created 22-Mai-2019 17:49:17
 */
public class DragAndDropMouseListener extends MouseAdapter {

	private Controller controller;

	public DragAndDropMouseListener(Controller controller){
		this.controller = controller;
	}
	
	public void mouseDragged(MouseEvent e) {
		Object source = e.getSource();
		
		controller.saveDraggedObject(source);
	}
	
	public void mouseReleased(MouseEvent e) {
		Object source = e.getSource();
		
		if (source instanceof Task && controller.getDraggedObject() instanceof Task) {
			controller.moveDraggedTaskUnderOtherTask((Task) source);
		}
		else if (source instanceof Task && controller.getDraggedObject() instanceof ListOfTasks) {
			controller.moveDraggedTaskToList((ListOfTasks) source);
		}
		else if (source instanceof ListOfTasks && controller.getDraggedObject() instanceof ListOfTasks) {
			controller.moveDraggedListNextToOtherList((ListOfTasks) source); 
		}
		else {
			controller.setDraggedObject(null);
		}
	}

}