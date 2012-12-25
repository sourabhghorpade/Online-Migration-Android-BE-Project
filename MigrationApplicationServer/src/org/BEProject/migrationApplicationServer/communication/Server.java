package org.BEProject.migrationApplicationServer.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
   private static final int END_OF_MESSAGE = 13;
   private static final int PORT_NUMBER = 9090;
   private ServerSocket serverSocket;
   private Socket connection;
   private  static Server ourInstance;

   public static Server getInstance() throws IOException
   {
       if(ourInstance==null)
           ourInstance=  new Server(PORT_NUMBER);
       return ourInstance;
   }

   private Server(int portNumber) throws IOException
   {
        serverSocket = new ServerSocket(portNumber);
   }

   public void run() throws IOException, InterruptedException
    {
      while (true)
      {
          connection=serverSocket.accept();
          System.out.println(connection.toString());
          new ServerThread(connection,END_OF_MESSAGE).run();
      }
    }
   
   public static  void main(String args[]) throws IOException, InterruptedException
   {
	   Server server=Server.getInstance();
	   server.run();
   }
   
}
