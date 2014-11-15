package server;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {
	private static final long serialVersionUID = -3306657696814114583L;
	private String name, gameSaveFile, email;
	private byte[] hashedPassword;
	private boolean loggedIn;
	private boolean isAdmin;
	public User(String newName, byte[] newPass, boolean admin)
	{
		name = newName;
		hashedPassword = newPass;
		isAdmin = admin;
		gameSaveFile ="data/"+name+".sav";
	}
	public boolean checkPass(byte[] hashedPass)
	{
		return Arrays.equals(hashedPass, hashedPassword);
	}
	
	public boolean changePass(byte[] hashedOldPass, byte[] hashedNewPass)
	{
		if(checkPass(hashedOldPass))
		{
			System.out.println("old pass correct");
			hashedPassword = hashedNewPass;
			return true;
		}
		System.out.println("old pass not correct");
		return false;
	}
	public String toString()
	{
		String admin="", loged="";
		if(isAdmin)
			admin =" admin";
		if(loggedIn)
			loged = " loggedin";
		return name+admin+loged;
	}
	
	public void adminPrivliges(boolean allow)
	{
			isAdmin = allow;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isLoggedIn()
	{
		System.out.println("user is loggedin: "+loggedIn);
		return loggedIn;
	}
	
	public void loggIn()
	{
		loggedIn=true;
	}
	
	public void logOut()
	{
		loggedIn=false;
	}
	
	public String getSaveFile()
	{
		return gameSaveFile;
	}
	
	public boolean isAdmin()
	{
		return isAdmin;
	}
}
