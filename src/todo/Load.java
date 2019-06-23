package todo;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Load extends Thread {
	LinkedList<ListOfTasks> collection;
	String listName;
	JSONArray jsonArray;
	JSONObject temp;
	LinkedList<Task> currentList;

	public Load(LinkedList<ListOfTasks> listCollection) {
		this.collection = listCollection;
	}

	public LinkedList<ListOfTasks> loadFiles() {

		/*
		 * get all Files of the directory and load them
		 * 
		 * 
		 * 
		 */
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println(s);
		File dir = new File(s + "/saveFiles/");

		if (dir.isDirectory()) {
			if (dir.list().length > 0) {
				// directory exists and is not empty
				ArrayList<String> result = new ArrayList<>();

				result = search(".*\\.json", dir, result);

				for (String files : result) {
					load(files);
				}
				// TODO: add tasks to collection!
				return collection;
			} else {
				// directory is empty
				return collection;
			}
		} else {
			return collection;
		}

	}

	private ArrayList<String> search(final String pattern, final File folder, ArrayList<String> result) {
		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				search(pattern, f, result);
			}
			if (f.isFile()) {
				if (f.getName().matches(pattern)) {
					result.add(f.getAbsolutePath());
				}
			}
		}
		return result;
	}

	private void load(String file) {
		/*
		 * Structure of the JSONArray: 1)get filename from listname + hashValue (?) <-
		 * avoid duplicate files 2)First JSONObject has to contain all info about the
		 * list!! 3)Following JSONObjects in the Array contain all info for the tasks
		 * 
		 */
		JSONParser jParse = new JSONParser();
		JSONArray jLoadedList = null;

		try {
			FileReader reader = new FileReader(file);
			jLoadedList = (JSONArray) jParse.parse(reader);

		} catch (Exception e) {
			System.out.print(e);
		}

		Iterator i = jLoadedList.iterator();
		JSONObject listInfo = (JSONObject) i.next();

		this.currentList = new LinkedList<Task>();
		ListOfTasks listOfTasks = createList(listInfo);

		while (i.hasNext()) {

			JSONObject taskObj = (JSONObject) i.next();
			Task task = createTask(taskObj, listOfTasks);
			currentList.add(task);
		}

		listOfTasks.addList(currentList);

		collection.add(listOfTasks);
		currentList = null;
	}

	private Task createTask(JSONObject taskObj, ListOfTasks list) {

		/*
		 * loads all fields from JSON-Arrays to create a new Task Obj
		 */
		String name = (String) taskObj.get("name");
		LocalDateTime deadline = loadLocalDateTime((String) taskObj.get("deadline"));
		int interval = Integer.parseInt((String) taskObj.get("interval"));
		Color color = loadColor((String) taskObj.get(color));
		String text = (String) taskObj.get(text);

		Task task = new Task(list, name, deadline, interval, color, text);
		return task;

	}

	private ListOfTasks createList(JSONObject listInfo) {
		ListOfTasks listOfTasks = new ListOfTasks((String) listInfo.get("name"));
		return listOfTasks;
	}

	private LocalDateTime loadLocalDateTime(String s) {
		LocalDateTime dateAsObj;
		String[] dateTimeValues = s.split(":");
		dateAsObj = LocalDateTime.of(Integer.parseInt(dateTimeValues[0]), Integer.parseInt(dateTimeValues[1]),
				Integer.parseInt(dateTimeValues[2]), Integer.parseInt(dateTimeValues[3]),
				Integer.parseInt(dateTimeValues[4]));
		return dateAsObj;

	}

	private Color loadColor(String c) {
		int a = Integer.parseInt(c);
		Color color = new Color(a);
		return color;
	}
}
