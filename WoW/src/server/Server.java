package server;

import java.io.*;
import java.net.*;


public class Server
{
    private ServerSocket server;
    private static final String USER_DB_FILE = "data/users.dat";
    private static UserDatabase userDB;
    
    public Server(int PortNumber)
    {
        try
        {
            server = new ServerSocket(PortNumber);
            userDB = new UserDatabase(USER_DB_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void start() throws Exception
    {
        while(true)
        	new MultiServer(server.accept(), userDB).start();
    }
    
    public void close()
    {
        try
        {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
