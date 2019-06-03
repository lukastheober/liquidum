package com.liquidum.tasklist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TestsSmallClasses {

	@Before
	public void setUp() throws Exception {
		//TODO: set up working list
	}
	/*---DialogActionListener class---*/
	//DialogActionListener seems to be an abstract class, 
	//so these tests need to be done for every class that inherits from it
	
	@Test
	public void testDialogActionListenerLegal() {
		new DialogActionListener(new Controller());
		//TODO: check if new DialogActionListener exists
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDialogActionListenerWrongObject() {
		new DialogActionListener(new Task());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDialogActionListenerNull() {
		new DialogActionListener(null);
	}
	
	/*---Task class---*/
	//Task needs to have a method to set the deadline
	
	@Test
	public void testTaskExpiryTrue() {
		Date expired = new Date();
		Date notExpired = new Date(1565215200000l);
		
		Task expiresSoon = new Task();
		expiresSoon.setDeadline(expired);
		
		Task wontExpire = new Task();
		wontExpire.setDeadline(notExpired);
		
		assertTrue(expiresSoon.expiresWithin3DaysOf());
		assertFalse(wontExpire.expiresWithin3DaysOf());
	}
	
	@Test
	public void testTaskOverwriteLegal() {
		Task task = new Task();
		Task newTask = new Task();
		
		task.overwrite(newTask);
		/*TODO: check if old task has been replaced
		*idea: look for "parent" of task (the value which points to it
		*and check if the pointer is correct
		*/
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTaskOverwriteWrongObject() {
		Task task = new Task();
		Date newTask = new Date();
		
		task.overwrite(newTask);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTaskOverwriteNull() {
		Task task = new Task();
		
		task.overwrite(null);
	}
	
	@Test(expected = IncompleteTaskException.class)
	public void testTaskOverwriteNoNameInTask() {
		Task task = new Task();
		Task newTask = new Task();
		/*
		 * newTask needs to be initialized incompletely!
		 * --> without Name
		 */
		
		task.overwrite(newTask);
	}
	
	@Test(expected = IncompleteTaskException.class)
	public void testTaskOverwriteNoListInTask() {
		Task task = new Task();
		Task newTask = new Task();
		/*
		 * newTask needs to be initialized incompletely!
		 * --> without parent list
		 */
		
		task.overwrite(newTask);
	}
	
	@Test(expected = IncompleteTaskException.class)
	public void testTaskOverwriteNoColorInTask() {
		Task task = new Task();
		Task newTask = new Task();
		/*
		 * newTask needs to be initialized incompletely!
		 * --> without color
		 */
		
		task.overwrite(newTask);
	}
	
	
	/*---ListOfTasks class---*/
	
	@Test
	public void testListOverwriteLegal() {
		ListOfTasks list = new ListOfTasks();
		ListOfTasks newList = new ListOfTasks();
		
		list.overwrite(newList);
		/*TODO: check if old list has been replaced
		*idea: look for "parent" of list (the value which points to it)
		*and check if the pointer is correct
		*/
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testListOverwriteWrongObject() {
		ListOfTasks list = new ListOfTasks();
		Date newList = new Date();
		
		list.overwrite(newList); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testListOverwriteNull() {
		ListOfTasks list = new ListOfTasks();

		list.overwrite(null);
	}
	
	@Test(expected = IncompleteLoTException.class)
	public void testListOverwriteNoNameInList() {
		ListOfTasks list = new ListOfTasks();
		ListOfTasks newList = new ListOfTasks();
		
		/*
		 * newList needs to be initialized incomplete
		 * --> without name
		 */
		
		list.overwrite(newList);
	}

	@Test(expected = IncompleteLoTException.class)
	public void testListOverwriteTasksNotCopied() {
		ListOfTasks list = new ListOfTasks();
		ListOfTasks newList = new ListOfTasks();
		
		/*
		 * newList needs to be initialized incomplete
		 * --> don't copy tasks from other list
		 */
		
		list.overwrite(newList);
	}

	/*--- GUI class ---*/
	
	public void testUpdate() {
		GUI gui = new GUI();
		
		//gui.changesomething();		sth like this needs to be implemented
		
		gui.update();
		//Result should be visible
	}
}





























