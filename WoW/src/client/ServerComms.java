package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import engine.Window;


public class ServerComms
{
    
    private Socket client;
    private PrintWriter outputToServer;
    private ObjectOutputStream objectOutputToServer;
    private ObjectInputStream objectInput;
    private BufferedReader serverInput;
    private Whirlpool hash = new Whirlpool();
    private String userLoggedIn;
    private boolean userIsAdmin;
    Window wondow;
    
    public ServerComms(int PortNumber, Window win)
	{
	    try 
	    {
	    	wondow = win;
	    	
	    	byte[] taco = {(byte) 127,(byte) 0,(byte) 0,1};
	    	client = new Socket(InetAddress.getByAddress(taco), PortNumber);
	        serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        outputToServer = new PrintWriter(client.getOutputStream(),true);
	        objectOutputToServer = new ObjectOutputStream(client.getOutputStream());
	        objectInput = new ObjectInputStream(client.getInputStream());
	    }
	    catch (IOException e) {
	        System.out.println(e);
	    }
	 }
	 
	 public void close()
	 {
	     try
	     {
//	    	 if(wondow!=null)
//	    	 {
//	    		 System.out.println("wondow not null");
//	    		 save((Serializable) wondow.saveFile.getSave());
//	    	 }
	    	 outputToServer.println("quit");
	    	 client.close();
	         System.out.println("Connection Closed. \nClient terminated.");
	         System.exit(0);
	     } catch (IOException e) {
	         e.printStackTrace();
	         System.exit(0);
	     }
	 }
	 
	 public boolean isConnected()
	 {
		 return (client != null) && (outputToServer != null) && (serverInput != null);
	 }
	 
	 public String getUser()
	 {
		 return userLoggedIn;
	 }
	 
	 public boolean isAdmin()
	 {
		 return userIsAdmin;
	 }
	 
	 private byte[] hashString(String username, char[] clearString)
	 {
		 byte[] hashedString = new byte[64];
		 
		 //hashes username and password together
		 hash.NESSIEinit();
		 hash.NESSIEadd(clearString);
		 hash.NESSIEadd(username);
		 hash.NESSIEfinalize(hashedString);
		 
		 //hashes username and password hash with the password
		 hash.NESSIEinit();
		 hash.NESSIEadd(hashedString, 510);
		 hash.NESSIEadd(clearString);
		 hash.NESSIEfinalize(hashedString);
		 
         return hashedString;
	 }
	 
	 public boolean authenticate(String user, char[] clearPassword)
	 {
		 byte[] hashedPassword = hashString(user, clearPassword);
		 clearPassword = null;
		 String input;
		 if(isConnected())
		 {
			 try {
				 outputToServer.println("login");
				 outputToServer.println(user);
				 if(serverInput.readLine().contains("true"))
				 {
					 objectOutputToServer.writeObject(hashedPassword);
					 hashedPassword = null;
					 input = serverInput.readLine();
					 System.out.println(input+ " input434");
					 if(input.equals("true"))
					 {
						 userLoggedIn = user;
						 System.out.println("Logging success");
						 userIsAdmin = serverInput.readLine().equals("true");
						 System.out.println("admin: "+userIsAdmin);
						 return true;
					 }
				 }
				 else
				 {
					 System.out.println("User does not exist or is already logged in.");
				 }
			} catch (IOException e) {
				System.out.println("Server Disconected.");
				close();
			} catch (NullPointerException e) {
				e.printStackTrace();
				System.out.println(e);
				close();
			}
		 }
		 hashedPassword = null;
		 return false;
	 }
	 
	 public boolean register(String user, char[] clearPassword)
	 {
		 byte[] hashedPassword = hashString(user, clearPassword);
		 clearPassword = null;
		 try {
			 outputToServer.println("add");
			 outputToServer.println(user);
			 if(serverInput.readLine().equals("true"))
			 {
				 objectOutputToServer.writeObject(hashedPassword);
				 hashedPassword = null;
				 return true;
			 }
		} catch (IOException e) {
			System.out.println("Server Disconected.");
			close();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println(e);
			close();
		}
		 hashedPassword = null;
		return false;
	 }
	 
	 public boolean passwd(String user, char[] oldClearPass, char[] newClearPass)
	 {
		 byte[] oldHashedPass = hashString(user, oldClearPass);  
		 byte[] newHashedPass = hashString(user, newClearPass);
//		 oldClearPass = null;
//		 newClearPass = null;
		 try {
			 outputToServer.println("passwd");
			 System.out.println("User = "+user);
			 outputToServer.println(user);
			 System.out.println("test35325" + oldHashedPass);
			 objectOutputToServer.writeObject(oldHashedPass);
			 System.out.println("test987325");
			 objectOutputToServer.writeObject(newHashedPass);
			 System.out.println("test*&(^3295912692");
			 oldHashedPass = null;
			 newHashedPass = null;
			 if(serverInput.readLine().equals("true"))
			 {
				 return true;
			 }
		} catch (IOException e) {
			System.out.println("Server Disconected.");
			close();
		} 
		oldHashedPass = null;
		newHashedPass = null;
		return false;
	 }
	 
	 public boolean adminTask(String task, String user)
	 {
	    try 
		{
			 if(userIsAdmin)
			 {
				 outputToServer.println(task);
				 outputToServer.println(user);
				 return serverInput.readLine().equals("true");
			 }
		 } catch (IOException e) {
			 System.out.println("Server Disconected.");
		 	 close();
		 } catch (NullPointerException e) {
			 e.printStackTrace();
		 	 System.out.println(e);
			 close();
		 }
		 return false;
	 }
	 
	public String[] getUserList()
	{
		 System.out.println("getting user list.");
		 outputToServer.println("users");
		 try 
		 {
			return (String[]) objectInput.readObject();
		 } catch (IOException e) {
			 System.out.println("Server Disconected.");
		 	 close();
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 return null;
	 }
	
	public boolean save(Serializable saveObject)
	{
		System.out.println("sending save file. " +saveObject.toString()); 
		System.out.println(saveObject instanceof Serializable);
		try {
			outputToServer.println("save");
//			objectOutputToServer.flush();
			System.out.println("testsave678t");
			objectOutputToServer.writeObject(saveObject);
			System.out.println("testsave324  "+saveObject.hashCode());
//			return serverInput.readLine().equals("true");
		}catch (SocketException e) {
			e.printStackTrace();
		}catch (IOException e) {
		    e.printStackTrace();
			System.out.println("Server Disconected.");
			close();
		} 
		return false;
	}
	
	public Serializable load()
	{
	    try
        {
	        outputToServer.println("load");
	        System.out.println("test load349258");
            return (Serializable) objectInput.readObject();
        } catch (IOException e)
        {
            System.out.println("Server Disconected.");
            close();
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}
	public void setWondow(Window win)
	{
		wondow = win;
	}
}