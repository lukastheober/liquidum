package todo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JMenuItem;

import org.w3c.dom.ls.LSInput;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */

public class Controller {

	// check
	// TODO test

	private GUI gui;
	private LinkedList<ListOfTasks> listCollection;
	private LinkedList<Task> trashBin;
	private Object draggedObject;

	/**
	 * Creates and opens the GUI-frame.
	 */
	public Controller() {
		// TODO
		listCollection = new LinkedList<ListOfTasks>();
		trashBin = new LinkedList<Task>();
		draggedObject = null;
		gui = new GUI();
		setActionListenerUp();
	}

	// method to add ActionListener for MainMenuBar
	private void setActionListenerUp() {
		gui.getMainMenuBar().getListCreateButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListCreationWizard wiz = new ListCreationWizard(Controller.this);
			}
		});

		gui.getMainMenuBar().getSearchButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchWizard wiz = new SearchWizard(Controller.this);
			}
		});
		
		gui.getMainMenuBar().getResetFilter().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetFilter();
			}
		});
		
		JMenuItem[] filterMenu = gui.getMainMenuBar().getFilterButton();
		for(JMenuItem filter : filterMenu) {
			filter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					switch(e.getActionCommand()) {
					case "Rot":
						filterBy(Color.red);
						break;
					case "Blau":
						filterBy(Color.blue);
					case "Gr�n":
						filterBy(Color.green);
					default:
						System.out.println("couldnt pick color");
					}
				}
			});
		}	
	}

	/**
	 * Only called by the ListCreationWizard. Adds the ListOfTasks to the
	 * listCollection.
	 * 
	 * @param list: the ListOfTasks created by the wizard
	 */
	public void addList(ListOfTasks list) {
		listCollection.add(list);
		gui.update();
		save();
	}

	/**
	 * Only called by the TaskCreationWizard or the TaskDuplicateButton's
	 * ActionListener. Adds the Task to its ListOfTask in the listCollection.
	 * 
	 * @param task: the Task created by the wizard or a clone of the duplicated Task
	 */
	public void addTask(Task task) {
		ListOfTasks myList = task.getMyList();
		myList.getTasks().add(task);
		gui.update();
		save();
	}

	/**
	 * Called when a ListOfTasks is being deleted. Deletes all Tasks that belonged
	 * to that list from the thrashBin.
	 * 
	 * @param list: the ListOfTasks that is being deleted
	 */
	public void clearBinOfAllTasksFrom(ListOfTasks list) {
		LinkedList tasksToDelete = list.getTasks();
		Iterator<Task> listIterator = tasksToDelete.iterator();
		Iterator<Task> trashIterator = trashBin.iterator();
		while (listIterator.hasNext()) {
			while (trashIterator.hasNext()) {
				if (trashIterator.next().equals(listIterator.next())) {
					trashBin.remove(trashIterator.next());
				}
			}
		}
	}

	/**
	 * Called when the program is booted. Deletes all Tasks in the trashBin whose
	 * deletionDate is longer than 3 days ago.
	 */
	private void deleteOldTasksFromBin() {
		Iterator<Task> trashIterator = trashBin.iterator();
		while (trashIterator.hasNext()) {
			// Date to Calendar Conversion missing
//			if (trashIterator.next().getDeletionDate())
//				trashBin.remove(trashIterator.next());
		}
	}

	/**
	 * Only called by the ListEditingWizard. Finds the oldList in the listCollection
	 * and overwrites all its data with the newList's data.
	 * 
	 * @param oldList: the ListOfTasks before it was edited
	 * @param newList: the ListOfTasks after it was edited
	 */
	public void editList(ListOfTasks oldList, ListOfTasks newList) {
		// TODO
	}

	/**
	 * Only called by the TaskEditingWizard. Finds the oldTask in its ListOfTasks
	 * and overwrites all its data with the newTask's data.
	 * 
	 * @param         oldTask: the Task before it was edited
	 * @param newTask the Task after it was edited
	 */
	public void editTask(Task oldTask, Task newTask) {
		Iterator<ListOfTasks> iterator = this.listCollection.iterator();
		while (iterator.hasNext()) {
			LinkedList<Task> actualListOfTasks = iterator.next().getTasks();
			if (actualListOfTasks.contains(oldTask)) {
				actualListOfTasks.set(actualListOfTasks.indexOf(oldTask), newTask);
			}
		}
		gui.update();
		save();
	}

	/**
	 * Only called by the FilterMenu. Sets all Tasks that don't contain the Color to
	 * invisible and sets all ListOfTasks that don't contain any visible Tasks to
	 * invisible.
	 * 
	 * @param color: the Color chosen by the user
	 */
	public void filterBy(Color color) {
		Iterator<ListOfTasks> taskListsIterator = this.listCollection.iterator();

		while (taskListsIterator.hasNext()) {

			ListOfTasks actualTaskListObject = taskListsIterator.next();
			LinkedList<Task> actualListOfTasks = actualTaskListObject.getTasks();
			Iterator<Task> taskIterator = actualListOfTasks.iterator();
			boolean taskListWithoutFilteredColor = true;

			while (taskIterator.hasNext()) {

				Task actualTask = taskIterator.next();

				if (actualTask.getColor() != color) {
					actualTask.setVisible(false);
				} else {
					taskListWithoutFilteredColor = false;
				}
			}
			if (taskListWithoutFilteredColor) {
				actualTaskListObject.setVisible(false);
			}
		}
		gui.update();
		save();
	}

	/**
	 * Only called by the FilterMenu. Turns all Tasks and ListOfTasks visible.
	 */
	public void resetFilter() {
		Iterator<ListOfTasks> taskListsIterator = this.listCollection.iterator();
		
		while(taskListsIterator.hasNext()) {
			
			ListOfTasks actualTaskListObject = taskListsIterator.next();
			LinkedList<Task> actualListOfTasks = actualTaskListObject.getTasks();
			Iterator<Task> taskIterator = actualListOfTasks.iterator();
			
			while(taskIterator.hasNext()) {
				taskIterator.next().setVisible(true);
			}
		}
		gui.update();
		save();
	}

	/**
	 * Only called by the ListDeletionWarningDialog. Deletes the given ListOfTasks
	 * from the listCollection and clears the thrashBin of all Tasks that belonged
	 * to that ListOfTasks.
	 * 
	 * @param list
	 */
	public void removeList(ListOfTasks list) {
		// TODO
	}

	/**
	 * Only called by the TaskDeletionWarningDialog. Moves the given Task to the
	 * thrashBin. If there are duplicates of the same Task, only delete one.
	 * 
	 * @param task
	 */
	public void removeTask(Task task) {
		// TODO
	}

	/**
	 * Only called by the TaskRestorationWizard. Moves the given Task from the
	 * trashBin back to its ListOfTasks.
	 * 
	 * @param task
	 */
	public void restoreTask(Task task) {
		// TODO
	}

	/**
	 * Only called by the SearchWizard. Turns all Tasks invisible whose text or name
	 * don't contain the given String and turns all ListOfTasks invisible if they
	 * don't contain any visible Tasks except if the ListOfTask's name contains the
	 * String
	 * 
	 * @param string: the String entered by the user
	 */
	public void searchFor(String string) {
		// TODO
	}

	/**
	 * Only called by the SortingComboBox. Sorts the given ListOfTasks by the given
	 * SortingCategory. Tasks that don't contain the category are listed at the
	 * bottom of the list.
	 * 
	 * @param list
	 * @param category
	 */
	public void sortListBy(ListOfTasks list, SortingCategory category) {
		// TODO
	}

	/**
	 * Only called by the TaskRestorationWizard. Finally deletes a Task that has
	 * been moved to the trashBin before.
	 * 
	 * @param task
	 */
	public void deleteTaskFromBin(Task task) {

	}

	/**
	 * Only called by a duplicateButton's ActionListener. Adds a clone of the given
	 * Task to the same ListOfTasks and puts that clone one spot after to the
	 * original Task.
	 * 
	 * @param task
	 */
	public void duplicateTask(Task task) {

	}

	/**
	 * Only called by a DragAndDropMouseListener. Changes the order of the
	 * listCollection so that the draggedList is on the right side of the given
	 * list. Reset the draggedObject to null.
	 * 
	 * @param list: the ListOfTasks that will end up on the left side of the
	 *        draggedList.
	 */
	public void moveDraggedListNextToOtherList(ListOfTasks list) {
		listCollection.remove((ListOfTasks) draggedObject);
		int index = listCollection.indexOf(list);
		listCollection.add(index + 1, (ListOfTasks) draggedObject);
		draggedObject = null;
		
		gui.update();
		save();
	}

	/**
	 * Only called by a DragAndDropMouseListener. Removes the draggedTask from its
	 * current ListOfTasks and adds it to the given ListOfTasks. Reset the
	 * draggedObject to null.
	 * 
	 * @param list
	 */
	public void moveDraggedTaskToList(ListOfTasks list) {
		Task task = (Task) draggedObject;
		ListOfTasks myList = task.getMyList();
		myList.getTasks().remove(task);
		list.getTasks().add(task);
		draggedObject = null;
		
		gui.update();
		save();
	}

	/**
	 * Only called by a DragAndDropMouseListener. If the draggedTask and the given
	 * Task are in the same ListOfTasks change the ListOfTasks' order so that the
	 * draggedTask appears right after the given Task.
	 * 
	 * @param task
	 */
	public void moveDraggedTaskUnderOtherTask(Task task) {
		Task draggedTask = (Task) draggedObject;
		draggedTask.getMyList().getTasks().remove(draggedTask);
		ListOfTasks list = task.getMyList();
		int index = list.getTasks().indexOf(task);
		list.getTasks().add(index + 1, draggedTask);
		draggedObject = null;
		
		gui.update();
		save();
	}

	/**
	 * Called when the user starts a drag over a ListOfTasks.
	 * 
	 * @param list: the new draggedList
	 */
	public void saveDraggedObject(Object draggedObject) {
		this.draggedObject = draggedObject;
	}

	

//	public static void main(String[] args) {
//		Controller bla = new Controller();
//	}
	public void save() {
		Thread t1 = new Save(listCollection);
		t1.start();
	}

	public Object getDraggedObject() {
		return draggedObject;
	}

	public void setDraggedObject(Object object) {
		draggedObject = null;
	}

}