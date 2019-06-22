package todo;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Save extends Thread {
	LinkedList<ListOfTasks> collection;
	String listName;
	JSONArray jsonArray;
	JSONObject temp;

	public Save(LinkedList<ListOfTasks> listCollection) {
		this.collection = listCollection;
	}

	public void saveAll() {
		Iterator i = collection.iterator();
		while(i.hasNext()) {
			saveList((ListOfTasks)i.next());
		}
	}

	@SuppressWarnings("unchecked")
	public void saveList(ListOfTasks list) {
		this.listName = list.getName();
		LinkedList<Task> taskList = list.getTaskList();
		this.jsonArray = new JSONArray();
		
		
		JSONObject listInfo = new JSONObject();
		listInfo.put("name", list.getName());
		jsonArray.add(listInfo);
		
		
		
		for (Iterator<Task> i = taskList.iterator(); i.hasNext();) {
			Task temp = i.next();
			JSONObject jTemp = new JSONObject();
			jsonArray.add(taskToJSON(temp, jTemp));
		}

		writeJSONFile();
	}

	private void writeJSONFile() {
		// TODO duplicate file handling
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println(s);
		File dir = new File(s + "/saveFiles/");
		if (dir.isDirectory()) {
			String filepath = s + "/saveFiles/" + listName + ".json";
			// System.out.println(filepath);
			try {
				FileWriter fw = new FileWriter(filepath);
				fw.write(jsonArray.toJSONString());
				fw.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			boolean success = dir.mkdir();
			if (success) {
				String filepath = s + "/saveFiles/" + listName + ".json";
				// System.out.println(filepath);
				try {
					FileWriter fw = new FileWriter(filepath);
					fw.write(jsonArray.toJSONString());
					fw.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private JSONObject taskToJSON(Task task, JSONObject jSon) {
		this.temp = jSon;
		putColor(task);
		putDeadLine(task);
		putDeletionDate(task);
		putInterval(task);
		temp.put("name", task.getName());
		temp.put("text", task.getText());

		jSon = this.temp;
		this.temp = null;
		return jSon;
	}

	@SuppressWarnings("unchecked")
	private void putDeadLine(Task task) {
		LocalDateTime timeAsObject = task.getDeadline(); 
		String dateAsString="";
		dateAsString+=timeAsObject.getYear();
		dateAsString+=":";
		dateAsString+=timeAsObject.getMonthValue();
		dateAsString+=":";
		dateAsString+=timeAsObject.getDayOfMonth();
		dateAsString+=":";
		dateAsString+=timeAsObject.getHour();
		dateAsString+=":";
		dateAsString+=timeAsObject.getMinute();
		temp.put("deadline", dateAsString);
	}
	
	
	@SuppressWarnings("unchecked")
	private void putDeletionDate(Task task) {
		LocalDateTime deletionDateObj = task.getDeletionDate();
		String dateAsString="";
		dateAsString += deletionDateObj.getYear();
		dateAsString += ":";
		dateAsString += deletionDateObj.getMonthValue();
		dateAsString += ":";
		dateAsString += deletionDateObj.getDayOfMonth();
		dateAsString += ":";
		dateAsString += deletionDateObj.getHour();
		dateAsString += ":";
		dateAsString += deletionDateObj.getMinute();
		temp.put("deletiondate", dateAsString);
	}

	@SuppressWarnings("unchecked")
	private void putColor(Task task) {
		Color jColor = task.getColor();
		// convert Color to String
		String sColor = Integer.toString(jColor.getRGB());
		// put color in json
		temp.put("color", sColor);
	}



	@SuppressWarnings("unchecked")
	private void putInterval(Task task) {
		String i = "" + task.getInterval();
		temp.put("interval", i);
	}

	@Override
	public void run() {
		saveAll();

	}

}
