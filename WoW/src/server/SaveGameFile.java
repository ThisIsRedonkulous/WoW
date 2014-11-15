package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SaveGameFile {
	private String file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Serializable saveObject;
	public SaveGameFile(String savefile)
	{
		file = savefile;
		readGameFile();
	}
	
	public Object getSave()
	{
		readGameFile();
		return saveObject;
	}
	
	public void save(Serializable newSave)
	{
		saveObject = newSave;
		saveGameFile();
	}

	private void saveGameFile()
	{
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			//write Objects to file
			oos.writeObject(saveObject);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readGameFile()
	{
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			saveObject = (Serializable) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			saveObject = null;
			saveGameFile();
			System.out.println("save file created");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
