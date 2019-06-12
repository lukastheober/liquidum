package todo;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ListSaver.Task;

public class Save {
	LinkedList<ListOfTasks> collection;
	String listName;
	JSONArray jsonArray;
	JSONObject temp;

	public Save(LinkedList<ListOfTasks> listCollection) {
		this.collection = listCollection;
	}

	public void saveAll() {
		for (int i = 0; i < collection.size(); i++) {
			ListOfTasks temp = collection.get(i);
			saveList(temp);
		}
	}

	public void saveList(ListOfTasks list) {
		this.listName = list.getName();
		this.jsonArray = new JSONArray();

		int lenght = list.size();
		for (int i = 0; i < lenght; i++) {
			Task temp = list.get(i);
			JSONObject jTemp = new JSONObject();
			JSONObject addable = taskToJSON(temp, jTemp);
			jsonArray.add(addable);
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
			//System.out.println(filepath);
			try {
				FileWriter fw = new FileWriter(filepath);
				fw.write(jsonArray.toJSONString());
				fw.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			boolean success = dir.mkdir();
			if(success) {
				String filepath = s + "/saveFiles/" + listName + ".json";
				//System.out.println(filepath);
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
	private JSONObject taskToJSON(Task task, JSONObject jSon) {
		this.temp=jSon;
		putColor(task);
		putDeadLine(task);
		putDeletionDate(task);
		putInterval(task);
		temp.put("name", task.getName());
		
		
		jSon = this.temp;
		this.temp = null;
		return jSon;
	}
	
	@SuppressWarnings("unchecked") 
	private void putDeadLine(Task task) {
		Date jDeadLine = task.getDeadline();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(jDeadLine);
		temp.put("deadline", s);
	}
	
	@SuppressWarnings("unchecked") 
	private void putColor(Task task) {
		Color jColor = task.getColor();	
		//convert Color to String
		String sColor = Integer.toString(jColor.getRGB());
		//put color in json
		temp.put("color", sColor);
	}
	
	@SuppressWarnings("unchecked") 
	private void putDeletionDate(Task task) {
		Date jDeletionDate = task.getDeletionDate();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = formatter.format(jDeletionDate);
		temp.put("deletiondate", s);
	}
	
	@SuppressWarnings("unchecked") 
	private void putInterval(Task task) {
		String i = "" +task.getInterval();
		temp.put("interval", i);
	}

}
