package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import engine.Saver;

public class MultiServer extends Thread
{
    private Socket client;
    private PrintWriter output;
    private BufferedReader clientInput;
    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    private static UserDatabase userDB;
    private String currentUser;
    private SaveGameFile currentGameFile;
    
    public MultiServer(Socket socket, UserDatabase users)
    {
        super("WoWMultiServer");
        client = socket;
        userDB = users;
        try {
        	clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
			objectInput = new ObjectInputStream(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(), true);
			objectOutput = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void run()
    {
    	System.out.println("test3");
    	String option, user = null;
    	byte[] hashedPass = new byte[64];
    	try {
    		while(true)
    		{
				option = clientInput.readLine();
				System.out.println(option);
				System.out.println(" user:" + user);
				if(option == null || option.equals("quit") || option.equals("null"))
					throw new IOException();
				else if(option.contains("login"))
				{
					user = clientInput.readLine();
					System.out.println("newuser:"+user);
					if(userDB.checkUser(user) && !userDB.userLoggedIn(user))
					{
						output.println("true");
						System.out.println("test3445");
						hashedPass = (byte[]) objectInput.readObject();
						System.out.println(user);
						if(auth(user,hashedPass))
						{
							output.println("true");
							System.out.println("user logged in");
							currentUser = user;
							userDB.logUserIn(currentUser);
							currentGameFile = new SaveGameFile(userDB.getUserSaveFile(currentUser));
							if(userDB.userIsAdmin(user))
							{
								output.println("true");
								System.out.println("user is admin");
							}
							else
								output.println("false");
							System.out.println("Login sucsessful!");
						}
						else
						{
							output.println("false");
							System.out.println("fail");
							user=null;
						}
					}
					else
					{
						output.println("false");
						System.out.println("nope "+(userDB.checkUser(user) && !userDB.userLoggedIn(user)));
						user = null;
					}
					hashedPass = null;
				 }
				 else if(option.contains("add"))
				 {
					user = clientInput.readLine();
					if(!userDB.checkUser(user))
					{
						output.println(true);
						hashedPass = (byte[]) objectInput.readObject();
						if(userDB.isEmpty())
							userDB.addUser(user, hashedPass, true);
						else
							userDB.addUser(user, hashedPass, false);
					}
					else
					{
						output.println("false");
					}
					hashedPass = null;
				 }
				 else if(currentUser!=null && userDB.userIsAdmin(currentUser))
				 {
					 if(option.equals("remove"))
					 {
						 user = clientInput.readLine();
						 output.println(userDB.removeUser(user));
					 }
					 else if(option.equals("admin"))
					 {
						 user = clientInput.readLine();
						 output.println(userDB.userMakeAdmin(user));
					 }
					 else if(option.equals("normal"))
					 {
						 user = clientInput.readLine();
						 output.println(userDB.userMakeNormal(user));
					 }
					 else if(option.equals("users"))
					 {
						 System.out.println("sending user list.");
						 objectOutput.writeObject(userDB.getUserList());
					 }
				 }
				 if(currentUser!=null)
				 {
					 if(option.contains("load"))
					 {
						 objectOutput.writeObject(currentGameFile.getSave());
					 }
					 else if(option.contains("save"))
					 {
						 System.out.println("testsave5u984");
						 Serializable savefile = (Serializable) objectInput.readObject();
						 System.out.println("testsave485uo");
						 currentGameFile.save(savefile);
						 System.out.println("testsave3456  "+savefile.hashCode());
//						 output.println("true");
					 }
					 else if(option.contains("load"))
					 {
					     SaveGameFile savefile = new SaveGameFile(userDB.getUserSaveFile(currentUser));
					     objectOutput.writeObject(savefile.getSave());
					 }
					 else if(option.equals("passwd"))
					 {
						 user = clientInput.readLine();
						 System.out.println("got user"+user);
						 byte[] oldHashedPass = (byte[]) objectInput.readObject();
						 System.out.println("got old pass" +oldHashedPass);
						 byte[] newHashedPass = (byte[]) objectInput.readObject();
						 System.out.println("got new pass" +newHashedPass);
						 output.println(userDB.passwd(user, oldHashedPass, newHashedPass));
						 System.out.println("password changed");
						 oldHashedPass = null;
						 newHashedPass = null;
					 }
					 else if(option.equals("logout"))
					 {
						 close();
					 }
				 }
    		 }
		} catch (IOException e) {
            close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
//			System.out.println(e);
			close();
		}
    }
    
    private boolean auth(String user, byte[] hashedPass)
    {
    	return userDB.checkPass(user, hashedPass);
    }
    
    private String getClientInput()
    {
			try {
				if(clientInput.ready())
					return clientInput.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return "";
    }
    
    public boolean close()
    {
    	if(currentUser!=null)
		{
    		userDB.logUserOut(currentUser);
			currentUser = null;
		}
        try
        {
			System.out.println("Client disconnected");
        	output.flush();
        	output.close();
        	client.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
