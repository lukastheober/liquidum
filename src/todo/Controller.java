package todo;

import java.awt.Color;
import java.util.LinkedList;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */

// test 123abc

public class Controller {

	private GUI gui;
	private LinkedList<ListOfTasks> listCollection;
	private LinkedList<Task> trashBin;
	private ListOfTasks draggedList;
	private Task draggedTask;

	/**
	 * Creates and opens the GUI-frame.
	 */
	public Controller() {
		//TODO
	}

	/**
	 * Only called by the ListCreationWizard.
	 * Adds the ListOfTasks to the listCollection.
	 * @param list: the ListOfTasks created by the wizard
	 */
	public void addList(ListOfTasks list){
		//TODO
	}

	/**
	 * Only called by the TaskCreationWizard 
	 * 		or the TaskDuplicateButton's ActionListener.
	 * Adds the Task to its ListOfTask in the listCollection.
	 * @param task: the Task created by the wizard or a clone of the duplicated Task
	 */
	public void addTask(Task task){
		//TODO
	}

	/**
	 * Called when a ListOfTasks is being deleted.
	 * Deletes all Tasks that belonged to that list from the thrashBin.
	 * @param list: the ListOfTasks that is being deleted
	 */
	private void clearBinOfAllTasksFrom(ListOfTasks list){
		//TODO
	}
	/**
	 * Called when the program is booted.
	 * Deletes all Tasks in the trashBin whose deletionDate is longer than 3 days ago.
	 */
	private void deleteOldTasksFromBin(){
		//TODO
	}

	/**
	 * Only called by the ListEditingWizard.
	 * Finds the oldList in the listCollection and overwrites all its data with the newList's data.
	 * @param oldList: the ListOfTasks before it was edited
	 * @param newList: the ListOfTasks after it was edited
	 */
	public void editList(ListOfTasks oldList, ListOfTasks newList){
		//TODO
	}

	/**
	 * Only called by the TaskEditingWizard.
	 * Finds the oldTask in its ListOfTasks and overwrites all its data with the newTask's data.
	 * @param oldTask: the Task before it was edited
	 * @param newTask the Task after it was edited
	 */
	public void editTask(Task oldTask, Task newTask){
		//TODO
	}

	/**
	 * Only called by the FilterMenu.
	 * Sets all Tasks that don't contain the Color to invisible
	 * and sets all ListOfTasks that don't contain any visible Tasks to invisible.
	 * @param color: the Color chosen by the user
	 */
	public void filterBy(Color color){
		//TODO
	}
	
	/**
	 * Only called by the FilterMenu.
	 * Turns all Tasks and ListOfTasks visible.
	 */
	public void resetFilter(){

	}

	/**
	 * Only called by the ListDeletionWarningDialog.
	 * Deletes the given ListOfTasks from the listCollection
	 * and clears the thrashBin of all Tasks that belonged to that ListOfTasks.
	 * @param list
	 */
	public void removeList(ListOfTasks list){
		//TODO
	}

	/**
	 * Only called by the TaskDeletionWarningDialog.
	 * Moves the given Task to the thrashBin.
	 * If there are duplicates of the same Task, only delete one.
	 * @param task
	 */
	public void removeTask(Task task){
		//TODO
	}

	/**
	 * Only called by the TaskRestorationWizard.
	 * Moves the given Task from the trashBin back to its ListOfTasks.
	 * @param task
	 */
	public void restoreTask(Task task){
		//TODO
	}

	/**
	 * Only called by the SearchWizard.
	 * Turns all Tasks invisible whose text or name don't contain the given String
	 * and turns all ListOfTasks invisible if they don't contain any visible Tasks 
	 * except if the ListOfTask's name contains the String
	 * @param string: the String entered by the user
	 */
	public void searchFor(String string){
		//TODO
	}

	/**
	 * Only called by the SortingComboBox.
	 * Sorts the given ListOfTasks by the given SortingCategory.
	 * Tasks that don't contain the category are listed at the bottom of the list.
	 * @param list
	 * @param category
	 */
	public void sortListBy(ListOfTasks list, SortingCategory category){
		//TODO
	}

	/**
	 * Only called by the TaskRestorationWizard.
	 * Finally deletes a Task that has been moved to the trashBin before.
	 * @param task
	 */
	public void deleteTaskFromBin(Task task){

	}

	/**
	 * Only called by a duplicateButton's ActionListener.
	 * Adds a clone of the given Task to the same ListOfTasks
	 * and puts that clone one spot after to the original Task.
	 * @param task
	 */
	public void duplicateTask(Task task){

	}

	/**
	 * Only called by a DragAndDropMouseListener.
	 * Changes the order of the listCollection so that 
	 * the draggedList is on the right side of the given list.
	 * Reset the draggedList to null.
	 * @param list: the ListOfTasks that will end up on the left side of the draggedList.
	 */
	public void moveDraggedListNextToOtherList(ListOfTasks list){

	}

	/**
	 * Only called by a DragAndDropMouseListener.
	 * Removes the draggedTask from its current ListOfTasks
	 * and add it to the given ListOfTasks.
	 * Reset the draggedTask to null.
	 * @param list
	 */
	public void moveDraggedTaskToList(ListOfTasks list){

	}

	/**
	 * Only called by a DragAndDropMouseListener.
	 * If the draggedTask and the given Task are in the same ListOfTasks
	 * change the ListOfTasks' order so that the draggedTask appears right after the given Task.
	 * @param task
	 */
	public void moveTaskUnderOtherTask(Task task){

	}

	/**
	 * Called when the user starts a drag over a ListOfTasks.
	 * @param list: the new draggedList
	 */
	public void saveDraggedList(ListOfTasks list){

	}

	/**
	 * Called when the user starts a drag over a Task.
	 * @param task: the new draggedTask
	 */
	public void saveDraggedTask(Task task){

	}

}