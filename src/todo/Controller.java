package todo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JMenuItem;

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

		gui.getMainMenuBar().getShowBinButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TaskRestorationWizard(Controller.this);
				// TODO
			}
		});

		JMenuItem[] filterMenu = gui.getMainMenuBar().getFilterButtons();
		for (JMenuItem filter : filterMenu) {
			filter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
					case "Weiß":
						filterBy(colorParser("Weiß"));
						break;
					case "Blau":
						filterBy(colorParser("Blau"));
						break;
					case "Grün":
						filterBy(colorParser("Grün"));
						break;
					case "Rot":
						filterBy(colorParser("Rot"));
						break;
					case "Orange":
						filterBy(colorParser("Orange"));
						break;
					case "Pink":
						filterBy(colorParser("Pink"));
						break;
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
		list.getAddTaskButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaskCreationWizard wiz = new TaskCreationWizard(list, Controller.this);

			}
		});

		list.getEditButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEditingWizard wiz = new ListEditingWizard(list, Controller.this);

			}
		});

		list.getDeleteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListDeletionWarningDialog diag = new ListDeletionWarningDialog(Controller.this, list);
			}
		});
		
		list.getSortingMenu().getDeadlineButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sortListBy(list, SortingCategory.Deadline);
			}
			
		});
		
		list.getSortingMenu().getColorButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sortListBy(list, SortingCategory.Color);
			}
			
		});
		
		list.getSortingMenu().getNameButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sortListBy(list, SortingCategory.Name);
			}
			
		});

		gui.getListContainer().loadListsOfTasks(listCollection);
		gui.update();

		// System.out.println("debug: listCollection size is " + listCollection.size());
		// TODO einkommentieren
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
		myList.getTaskList().add(task);
		myList.loadTasks();
		
		task.getMenu().getDeleteButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskDeletionWarningDialog diag = new TaskDeletionWarningDialog(Controller.this, task);
			}
		});
		
		task.getMenu().getDuplicateButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				duplicateTask(task);
			}
		});
		
		task.getMenu().getEditButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskEditingWizard wiz = new TaskEditingWizard(task, Controller.this);	
			}
		});
		
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
		for (Task task : trashBin) {
			if (task.getMyList().equals(list))
				trashBin.remove(task);
		}
	}

	/**
	 * Called when the program is booted. Deletes all Tasks in the trashBin whose
	 * deletionDate is longer than 3 days ago.
	 */
	private void deleteOldTasksFromBin() {
		Iterator<Task> trashIterator = trashBin.iterator();
		while (trashIterator.hasNext()) {
			if (trashIterator.next().getDeletionDate().isBefore(LocalDateTime.now().minusDays(30)))
				trashBin.remove(trashIterator.next());
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
		listCollection.set(listCollection.indexOf(oldList), newList);
		gui.update();
		save();
	}

	/**
	 * Only called by the TaskEditingWizard. Finds the oldTask in its ListOfTasks
	 * and overwrites all its data with the newTask's data.
	 * 
	 * @param         oldTask: the Task before it was edited
	 * @param newTask the Task after it was edited
	 */
	public void editTask(Task oldTask, Task newTask) {
		LinkedList<Task> actualListOfTasks = oldTask.getMyList().getTaskList();
		actualListOfTasks.set(actualListOfTasks.indexOf(oldTask), newTask);
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
		resetFilter();
		Iterator<ListOfTasks> taskListsIterator = this.listCollection.iterator();

		while (taskListsIterator.hasNext()) {

			ListOfTasks actualTaskListObject = taskListsIterator.next();
			LinkedList<Task> actualListOfTasks = actualTaskListObject.getTaskList();
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

		while (taskListsIterator.hasNext()) {

			ListOfTasks actualTaskListObject = taskListsIterator.next();
			LinkedList<Task> actualListOfTasks = actualTaskListObject.getTaskList();
			Iterator<Task> taskIterator = actualListOfTasks.iterator();
			
			actualTaskListObject.setVisible(true);
			
			while (taskIterator.hasNext()) {
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
		listCollection.remove(list);
		gui.getListContainer().remove(list);
		clearBinOfAllTasksFrom(list);
		
		gui.update();
		save();
	}

	/**
	 * Only called by the TaskDeletionWarningDialog. Moves the given Task to the
	 * thrashBin. If there are duplicates of the same Task, only delete one.
	 * 
	 * @param task
	 */
	public void removeTask(Task task) {
		LinkedList<Task> actualListOfTasks = task.getMyList().getTaskList();
		actualListOfTasks.remove(task);
		task.getMyList().remove(task);
		trashBin.add(task);
		gui.update();
		save();
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
		Iterator<ListOfTasks> taskListsIterator = this.listCollection.iterator();

		while (taskListsIterator.hasNext()) {

			ListOfTasks actualTaskListObject = taskListsIterator.next();
			String nameOfActualTaskListObject = actualTaskListObject.getListName();
			LinkedList<Task> actualListOfTasks = actualTaskListObject.getTaskList();
			Iterator<Task> taskIterator = actualListOfTasks.iterator();
			boolean taskListWithoutFilteredTasks = true;

			while (taskIterator.hasNext()) {

				Task actualTask = taskIterator.next();
				String actualTaskName = actualTask.getName();
				String actualTaskText = actualTask.getText();

				if (!(actualTaskName.contains(string) || actualTaskText.contains(string))) {
					actualTask.setVisible(false);
				} else {
					taskListWithoutFilteredTasks = false;
				}
			}
			if (taskListWithoutFilteredTasks && !(nameOfActualTaskListObject.contains(string))) {
				actualTaskListObject.setVisible(false);
			}
		}
		gui.update();
		save();
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
		LinkedList<Task> actualTaskList = list.getTaskList();

		switch (category) {
		case Color:
			Collections.sort(actualTaskList, new Comparator<Task>() {

				@Override
				public int compare(Task task1, Task task2) {
					String color1 = task1.getColor().toString();
					String color2 = task2.getColor().toString();
					return color1.compareTo(color2);
				}

			});
			break;
		case Deadline:
			Collections.sort(actualTaskList, new Comparator<Task>() {

				@Override
				public int compare(Task task1, Task task2) {
					return task1.getDeadline().compareTo(task2.getDeadline());
				}

			});
			break;
		case Name:
			Collections.sort(actualTaskList, new Comparator<Task>() {

				@Override
				public int compare(Task task1, Task task2) {
					return task1.getName().compareTo(task2.getName());
				}

			});
		}
		list.loadTasks();
		gui.update();
		save();
	}

	/**
	 * Only called by the TaskRestorationWizard. Finally deletes a Task that has
	 * been moved to the trashBin before.
	 * 
	 * @param task
	 */
	public void deleteTaskFromBin(Task task) {
		trashBin.remove(task);
		gui.update();
		save();
	}

	/**
	 * Only called by a duplicateButton's ActionListener. Adds a clone of the given
	 * Task to the same ListOfTasks and puts that clone one spot after to the
	 * original Task.
	 * 
	 * @param task
	 */
	public void duplicateTask(Task task) {
		Task clone = new Task(task.getMyList(), task.getName(), task.getDeadline(), task.getInterval(), task.getColor(),
				task.getText());
		ListOfTasks taskListObject = clone.getMyList();
		LinkedList<Task> taskList = taskListObject.getTaskList();
		taskList.add(taskList.indexOf(task) + 1, clone);
		taskListObject.loadTasks();
		
		
		clone.getMenu().getDeleteButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskDeletionWarningDialog diag = new TaskDeletionWarningDialog(Controller.this, task);
			}
		});
		
		clone.getMenu().getDuplicateButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				duplicateTask(task);
			}
		});
		
		clone.getMenu().getEditButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskEditingWizard wiz = new TaskEditingWizard(task, Controller.this);	
			}
		});
		
		gui.update();
		save();
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
		listCollection.remove(draggedObject);
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
		myList.getTaskList().remove(task);
		list.getTaskList().add(task);
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
		draggedTask.getMyList().getTaskList().remove(draggedTask);
		ListOfTasks list = task.getMyList();
		int index = list.getTaskList().indexOf(task);
		list.getTaskList().add(index + 1, draggedTask);
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

	public Collection<ListOfTasks> getallListOfTasks() {
		return listCollection;
	}
	
	public LinkedList<Task> getBin() {
		return trashBin;
	}

	public static void main(String[] args) {
		Controller bla = new Controller();
	}


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

	private Color colorParser(String clrStr) {
		switch (clrStr) {
		case "Blau":
			return Color.BLUE;
		case "Grün":
			return Color.GREEN;
		case "Rot":
			return Color.RED;
		case "Orange":
			return Color.ORANGE;
		case "Pink":
			return Color.PINK;
		default:
			return Color.WHITE;
		}
	}
}