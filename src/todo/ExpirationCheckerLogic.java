package todo;

import java.util.LinkedList;
import java.util.TimerTask;

/**
 * @author benja
 * @version 1.0
 * @created 22-Mai-2019 17:49:20
 */
public class ExpirationCheckerLogic extends TimerTask {

	private LinkedList<Task> expiredTasks;
	private LinkedList<ListOfTasks> listCollection;

	/**
	 * @param listCollection: all of this programs lists
	 */
	public ExpirationCheckerLogic(LinkedList<ListOfTasks> listCollection){

	}

	/**
	 * Goes through all Tasks of all ListOfTasks.
	 * If a lists deadline is within 3 days of the current date, add them to the expiredTasts list.
	 * At the end create a ReminderPopUp with the expiredTasks list.
	 */
	public void run() {
		// TODO Auto-generated method stub
		
	}

}