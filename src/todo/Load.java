package todo;

import java.awt.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		// TODO
		/* get all Files of the directory and load them */
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println(s);
		File dir = new File(s + "/saveFiles/");
		
		if (dir.isDirectory()) {
			if (dir.list().length > 0) {
				//directory exists and is not empty
				ArrayList<String> result = new ArrayList<>();

				result = search(".*\\.json", dir, result);

				for (String files : result) {
					load(files);
				}				
				return collection;
			}else {
			//directory is empty
				return collection;}
		}else {
		return collection;}

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
		 * Structure of the JSONArray: 
		 * 1)get filename from listname + hashValue (?) <- avoid duplicate files
		 * 2)First JSONObject has to contain all info about the list!!
		 * 3)Following JSONObjects in the Array contain all info for the tasks
		 * 
		 * */
		JSONParser jParse = new JSONParser();
		JSONArray jLoadedList = null;
		try {
			//build shit
			FileReader reader = new FileReader(file);
			jLoadedList = (JSONArray) jParse.parse(reader);
		}catch (Exception e) {
			System.out.print(e);
		}
		Iterator i = jLoadedList.iterator();
		JSONObject listInfo = (JSONObject)i.next();
		this.currentList = createList(listInfo);
		while(i.hasNext()) {
			JSONObject task = (JSONObject) i.next();
			createTask(task);
		}
	}

	private void createTask(JSONObject task) {
		//TODO create task loading methods
		//Task task = new Task();
		
	}
	
	private LinkedList<Task> createList(JSONObject listInfo){
		//TODO create loading methods
		return null;
	}
}
