package me.lyc8503.DarkForest.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.lyc8503.DarkForest.GameLoop.GameMap;
import me.lyc8503.DarkForest.Start.Start;

public class FileUtilities {
	
	public static GameMap load(File dictionary) throws Exception {
		FileInputStream stream = new FileInputStream(dictionary);
		ObjectInputStream inputStream = new ObjectInputStream(stream);
		GameMap map =(GameMap) inputStream.readObject();
		inputStream.close();
		stream.close();
		return map;
	}
	
	public static String save(File dictionary) throws Exception {
		System.out.println("Save to : " + dictionary.getPath());
		Date date = new Date();
		String dateStr = null;
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd_E_kk.mm.ss");
		dateStr = f.format(date);
		File file = new File(dictionary.getPath() + "/universe-" + dateStr + ".lycUniverse");
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();
		FileOutputStream stream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
		objectOutputStream.writeObject(Start.map);
		objectOutputStream.close();
		stream.close();
		return file.getAbsolutePath();
	}
}
