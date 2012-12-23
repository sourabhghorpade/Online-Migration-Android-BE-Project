package org.BEProject.migrationclient.Communication.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client
{
    public static final int END_OF_MESSAGE = 13;
    Socket connection;
    OutputStreamWriter outputStream;
    InputStreamReader inputStreamReader;

    public Client(String hostname, int portNumber) throws IOException
    {
        InetAddress address = InetAddress.getByName(hostname);
        connection = new Socket(address, portNumber);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(connection.getOutputStream());
        outputStream = new OutputStreamWriter(bufferedOutputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
        inputStreamReader = new InputStreamReader(bufferedInputStream);
    }

    public void sendTextToServer(String message) throws IOException
    {

        outputStream.write(message + (char) END_OF_MESSAGE);
        outputStream.flush();
    }

    public String getTextFromServer() throws IOException
    {
        final int CAPACITY = 100;
        StringBuilder receivedMessage = new StringBuilder(CAPACITY);
        int temp;
        while ((temp = inputStreamReader.read()) != END_OF_MESSAGE)
        {
            receivedMessage.append((char) temp);
        }
        return receivedMessage.toString();

    }
}
