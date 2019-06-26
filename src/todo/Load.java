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
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Load extends Thread {
	Controller controller;
	LinkedList<ListOfTasks> collection;
	String listName;
	JSONArray jsonArray;
	JSONObject temp;
	LinkedList<Task> currentList;

	public Load(LinkedList<ListOfTasks> listCollection, Controller c) {
		this.controller = c;
		this.collection = listCollection;
	}

	public void run() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		File dir = new File(s + "/saveFiles/");
		File trash = new File(s + "/trashbin/");

		if (dir.isDirectory()) {
			if (dir.list().length > 0) {
				// directory exists and is not empty
				ArrayList<String> result = new ArrayList<>();

				result = search(".*\\.json", dir, result);

				for (String files : result) {
					load(files);
				}
			}
		}
//		if(trash.isDirectory()) {
//			if(dir.list().length>0) {
//				ArrayList<String> trashList = new ArrayList<>();
//				trashList = search(".*\\.json", trash, trashList);
//				for(String files: trashList) {
//					loadBin(files);
//				}
//			}
//		}
	}

	private void loadBin(String file) {
//		LinkedList<Task> trash=null;
//		JSONParser jParse = new JSONParser();
//		JSONArray jLoadedList = null;
//
//		try {
//			FileReader reader = new FileReader(file);
//			jLoadedList = (JSONArray) jParse.parse(reader);
//
//		} catch (Exception e) {
//			System.out.print(e);
//		}
//		
//		Iterator i = jLoadedList.iterator();
//		JSONObject listInfo = (JSONObject) i.next();
//		ListOfTasks listOfTasks = new ListOfTasks(listName);
//		//controller.addList(listOfTasks);
//
//		while (i.hasNext()) {
//
//			JSONObject taskObj = (JSONObject) i.next();
//			Task task = createTask(taskObj, listOfTasks);
//			controller.addTask(task);
//		}
//		
//		
//		this.controller.setTrashBin(trash);
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
		 * Loads files
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
		String listName = (String) listInfo.get("name");
		String uuid = (String) listInfo.get("UUID");
		ListOfTasks listOfTasks = new ListOfTasks(listName, uuid);
		controller.addList(listOfTasks);

		while (i.hasNext()) {

			JSONObject taskObj = (JSONObject) i.next();
			Task task = createTask(taskObj, listOfTasks);
			controller.addTask(task);
		}
	}

	private Task createTask(JSONObject taskObj, ListOfTasks list) {

		/*
		 * loads all fields from JSON-Arrays to create a new Task Obj
		 */

		String name = (String) taskObj.get("name");

		LocalDateTime deadline = loadLocalDateTime((String) taskObj.get("deadline"));
		int interval = Integer.parseInt((String) taskObj.get("interval"));
		Color color = loadColor((String) taskObj.get("color"));

		String text = (String) taskObj.get("text");

		Task task = new Task(list, name, deadline, interval, color, text);
		return task;

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
