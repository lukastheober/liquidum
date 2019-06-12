package todo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Savepoint;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.ls.LSInput;

public class TestController {
	Controller c1;
	LinkedList<ListOfTasks> listCollection;
	LinkedList<Task> trashBin;
	ListOfTasks draggedList;
	Task draggedTask;

	@Before
	public void setUp() {
		c1 = new Controller();
		listCollection = new LinkedList();
		trashBin = new LinkedList();
		draggedList = null;
		draggedTask = null;
	}

	///////////////// moveListNextToOtherList Test /////////////////
	@Test
	public void moveListNextToOtherListTf1Working() {
		ListOfTasks toDrag = new ListOfTasks("toDrag");
		ListOfTasks toMove = new ListOfTasks("toMove");
		listCollection.add(toDrag);
		listCollection.add(toMove);
		c1.moveDraggedListNextToOtherList(listCollection.get(1));
		assertEquals(toMove, listCollection.getFirst());
		assertNull(draggedList);
	}

	@Test
	public void moveListNextToOtherListTf2NullObject() {
		ListOfTasks a = new ListOfTasks("a");
		ListOfTasks b = new ListOfTasks("b");
		ListOfTasks c = new ListOfTasks("c");
		listCollection.add(a);
		listCollection.add(b);
		listCollection.add(c);
		draggedList = a;
		c1.moveDraggedListNextToOtherList(null);
		assertEquals(a, listCollection.getFirst());
		assertNull(draggedList);
	}

	///////////////// moveTaskToList Test /////////////////
	@Test
	public void moveTaskToListTf1Working() {
		ListOfTasks list1 = new ListOfTasks("1");
		ListOfTasks list2 = new ListOfTasks("2");
		Task toMove = new Task();
		list1.getTaskList().add(toMove);
		c1.moveDraggedTaskToList(list2);
		assertNull(list1.getTaskList().getFirst());
		assertEquals(toMove, list2.getTaskList().getFirst());
		assertNull(draggedTask);
	}

	@Test
	public void moveTaskToListTf2NullObject() {
		ListOfTasks list1 = new ListOfTasks("1");
		Task toMove = new Task();
		list1.getTaskList().add(toMove);
		c1.moveDraggedTaskToList(null);
		assertEquals(toMove, list1.getTaskList().getFirst());
		assertNull(draggedTask);
	}

	///////////////// removeList Test /////////////////
	@Test
	public void removeListTF1Working() {
		ListOfTasks list1 = new ListOfTasks("1");
		Task a = new Task();
		trashBin.add(a);
		listCollection.add(list1);
		c1.removeList(list1);
		assertNull(listCollection.getFirst());
		assertNull(trashBin.getFirst());
	}

	@Test
	public void removeListTF2NullObject() {
		ListOfTasks list1 = new ListOfTasks("1");
		listCollection.add(list1);
		c1.removeList(null);
		assertEquals(list1, listCollection.getFirst());
	}

	// Constructor of ListOfTasks missing
	@Test
	public void removeListTF3ObjectWithTasksNull() {
		ListOfTasks list1 = new ListOfTasks("1");
	}

	///////////////// removeTask Test /////////////////
	@Test
	public void removeTaskTF1Working() {
		ListOfTasks list1 = new ListOfTasks("1");
		listCollection.add(list1);
		Task a = new Task();
		Task b = new Task();
		list1.getTaskList().add(a);
		list1.getTaskList().add(b);
		c1.removeTask(a);
		assertFalse(listCollection.getFirst().getTaskList().contains(a));
		assertTrue(trashBin.contains(a));
	}

	@Test
	public void removeTaskTF2NullObject() {
		ListOfTasks list1 = new ListOfTasks("1");
		listCollection.add(list1);
		Task a = new Task();
		list1.getTaskList().add(a);
		c1.removeTask(null);
		assertEquals(a, listCollection.getFirst().getTaskList().getFirst());
	}

	// Constructor of ListOfTasks missing
	@Test
	public void removeTaskTF3ObjectWithListOfTasksNull() {

	}

	///////////////// restoreTask Test /////////////////
	@Test
	public void restoreTaskTF1Working() {
		ListOfTasks list1 = new ListOfTasks("1");
		listCollection.add(list1);
		Task a = new Task();
		list1.getTaskList().add(a);
		c1.removeTask(a);
		c1.restoreTask(a);
		assertEquals(0, trashBin.size());
		assertTrue(listCollection.getFirst().getTaskList().contains(a));
	}

	@Test
	public void restoreTaskTF2NullObject() {
		ListOfTasks list1 = new ListOfTasks("1");
		listCollection.add(list1);
		Task a = new Task();
		list1.getTaskList().add(a);
		c1.removeTask(a);
		c1.restoreTask(null);
		assertEquals(1, trashBin.size());
		assertFalse(listCollection.getFirst().getTaskList().contains(a));
	}

	// Constructor of ListOfTasks missing
	@Test
	public void restoreTaskTF3ObjectWithListOfTasksNull() {

	}

	///////////////// save Test /////////////////
	@Test
	public void saveTF1Working() {

	}

	///////////////// searchFor Test /////////////////
	@Test
	public void searchForTF1Working() {
		// ???
	}

	///////////////// sortListBy Test /////////////////
	@Test
	public void sortListByTF1WorkingName() {
		// ???
	}

	@Test
	public void sortListByTF2WorkingDeadline() {
		// ???
	}

	@Test
	public void sortListByTF3WorkingColor() {
		// ???
	}

	@Test
	public void sortListByTF4NullName() {
		// ???
	}

	@Test
	public void sortListByTF5EmptyStringName() {
		// ???
	}

	@Test
	public void sortListByTF6NullDeadline() {
		// ???
	}

	@Test
	public void sortListByTF7NullColor() {
		// ???
	}

	@Test
	public void sortListByTF8NullObject() {
		c1.sortListBy(null, SortingCategory.Name);
	}

	@Test
	public void sortListByTF9NullSortingCategory() {
		// ???
	}
}
