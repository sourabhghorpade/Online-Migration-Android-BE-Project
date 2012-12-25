// JOB: To handle a request asynchronously

package org.BEProject.migrationApplicationServer.communication;

import java.io.*;
import java.net.Socket;
import java.util.Date;

class ServerThread  extends Thread
{
    OutputStreamWriter outputStream;
    InputStreamReader inputStreamReader;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket connection;

    final int END_OF_MESSAGE;

    public ServerThread(Socket connection,int END_OF_MESSAGE) throws IOException
    {
        this.connection=connection;
        this.END_OF_MESSAGE=END_OF_MESSAGE;
        objectInputStream=new ObjectInputStream(connection.getInputStream());
        objectOutputStream=new ObjectOutputStream(connection.getOutputStream());
    }


    public void sendObjectToClient(Object object) throws IOException
    {
        objectOutputStream.writeObject(new Date());
    }


    public Object getObjectFromClient() throws IOException {
        return objectInputStream.read();
    }

    void handleObjectRequest() throws IOException, InterruptedException
    {
        /*Object request= getObjectFromClient();
        Date date=(Date)request;
        System.out.println(date.toString());
        sendObjectToClient(new Date());*/
        while(true)
        {
            Object request= getObjectFromClient();
            if(request.equals("STOP"))
                break;
            String response=request + " Processed by Thread " + this.getId();
            System.out.println(response);
            sendTextToClient(response);
        }
       System.out.println(" Thread " + this.getId() + " is Done.");  
    }


    void handleRequest() throws IOException, InterruptedException
    {
        while(true)
        {
            String request= getTextFromClient();
            if(request.equals("STOP"))
                break;
            String response=request + " Processed by Thread " + this.getId();
            System.out.println(response);
            sendTextToClient(response);
        }
        System.out.println(" Thread " + this.getId() + " is Done.");

    }

    public void sendTextToClient(String message) throws IOException
    {
        outputStream.write(message + (char) END_OF_MESSAGE);
        outputStream.flush();
    }

    public String getTextFromClient() throws IOException
    {
        final int STRING_BUFFER_CAPACITY=100;
        StringBuilder stringBuilder=new StringBuilder(STRING_BUFFER_CAPACITY);
        int temp;
        while ((temp=inputStreamReader.read())!=END_OF_MESSAGE)
            stringBuilder.append((char)temp);
        return stringBuilder.toString();
    }

    @Override
    public void run()
    {
        try
        {
            handleRequest();
            //handleObjectRequest();
        }
        catch (IOException e)
        {
            try
            {
                sendTextToClient(e.getMessage());
            } catch (IOException ignored) {}
        } catch (InterruptedException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


}
