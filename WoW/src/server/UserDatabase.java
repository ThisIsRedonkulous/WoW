package server;

import java.io.*;
import java.util.*;

import UsefulThings.Quicksort;

public class UserDatabase implements Serializable {
		private static String saveFile;
		private static Hashtable<String, User> users;
		private ObjectInputStream readDB;
		private ObjectOutputStream writeDB;
		private static boolean inUse;
		private Quicksort qsort = new Quicksort();
		public UserDatabase(String datafile)
		{
			saveFile = datafile;
			inUse = false;
			updateDatabase();
			Enumeration<String> out =users.keys();
			while(out.hasMoreElements())
				logUserOut((String) out.nextElement());
		}
		
		public boolean checkUser(String user)
		{
			updateDatabase();
			return users.containsKey(user);
		}
		
		public boolean checkPass(String user, byte[] pass)
		{
			updateDatabase();
			if(users.containsKey(user))
				return users.get(user).checkPass(pass);
			return false;
		}
		
		public boolean userLoggedIn(String user)
		{
			updateDatabase();
			if(users.containsKey(user))
				return users.get(user).isLoggedIn();
			return false;
		}
		
		public boolean userIsAdmin(String user)
		{
			updateDatabase();
			if(users.containsKey(user))
				return users.get(user).isAdmin();
			return false;
		}
		
		public boolean userMakeAdmin(String user)
		{
			updateDatabase();
			if(users.containsKey(user))
			{
				users.get(user).adminPrivliges(true);
				saveDatabase();
				return true;
			}
			return false;
		}
		
		public boolean userMakeNormal(String user)
		{
			updateDatabase();
			if(users.containsKey(user))
			{
				users.get(user).adminPrivliges(false);
				saveDatabase();
				return true;
			}
			return false;
		}
		
		public boolean passwd(String user, byte[] oldHashedPass, byte[] newHashedPass)
		{
			boolean derp = users.get(user).changePass(oldHashedPass, newHashedPass);
			saveDatabase();
			System.out.println("derp = "+derp);
			return derp;
		}
		
		public void logUserIn(String user)
		{
			updateDatabase();
			users.get(user).loggIn();
			saveDatabase();
		}
		
		public void logUserOut(String user)
		{
			updateDatabase();
			users.get(user).logOut();
			saveDatabase();
		}
		
		public String getUserSaveFile(String user)
		{
			updateDatabase();
			return users.get(user).getSaveFile();
		}
		
		public String[] getUserList()
		{
			updateDatabase();
			String[] a = new String[users.size()];
			users.keySet().toArray(a);
			a = (String[]) qsort.sort(a);
			return a;
		}
		
		public boolean isEmpty()
		{
			updateDatabase();
			return users.isEmpty();
		}
		
		public boolean addUser(String user, byte[] pass, boolean admin)
		{
			updateDatabase();
			if(!users.containsKey(user))
			{
				users.put(user, new User(user,pass, admin));
				saveDatabase();
				return true;
			}
			return false;
		}
		
		public boolean addUser(User user)
		{
			updateDatabase();
			if(!users.contains(user))
			{
				users.put(user.getName(), user);
				saveDatabase();
				return true;
			}
			return false;
		}
		public boolean removeUser(String user)
		{
			updateDatabase();
			if(users.containsKey(user))
			{
				users.remove(user);
				saveDatabase();
				System.out.println(user+" removed");
				return true;
			}
			System.out.println(user+" not removed");
			return false;
		}
		
		public void saveDatabase()
		{
			try {
				fileInUse();
				inUse=true;
				writeDB = new ObjectOutputStream( new FileOutputStream(saveFile));
				writeDB.writeObject(users);
				writeDB.flush();
				writeDB.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			inUse=false;
		}
		
		public void updateDatabase()
		{
			try {
				fileInUse();
				inUse=true;
				readDB = new ObjectInputStream(new FileInputStream(saveFile));
				users = (Hashtable<String, User>) readDB.readObject();
				System.out.println(users.toString());
				readDB.close();
			} catch (FileNotFoundException e) {
				users = new Hashtable<String, User>();
				System.out.println("test");
				inUse=false;
				saveDatabase();
				inUse=true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			inUse=false;
		}
		private void fileInUse()
		{
			while(inUse)
			{
				System.out.println("File in use");
			}
		}
}
