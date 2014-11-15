package server;
import client.ClientGUI;
public class Driver
{

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {   
       Server server = new Server(2578);
       server.start();
//       ClientGUI client = new ClientGUI();
    }

}
