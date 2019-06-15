package todo;

import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * @author benja
 * @version 1.0
 * @created 18-Mai-2019 16:01:12
 */
public class ListContainer extends JPanel {

	public ListContainer(){
		setSize(200,200);
		setLayout(new GridLayout(1,10));
	}

	public void loadListsOfTasks(LinkedList<ListOfTasks> inListOfTasks) {
		for(ListOfTasks lot : inListOfTasks) {
			add(lot);
		}
		validate();
		repaint();
	}
	
}