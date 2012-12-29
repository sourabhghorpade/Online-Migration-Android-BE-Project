package org.BEProject.migrationclient.Communication.Client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static final int END_OF_MESSAGE = 13;
	Socket connection;
	OutputStreamWriter outputStream;
	InputStreamReader inputStreamReader;

	public Client(String hostname, int portNumber) throws IOException {
		InetAddress address = InetAddress.getByName(hostname);
		connection = new Socket(address, portNumber);
	}

	public void sendTextToServer(String message) throws IOException {
		outputStream.write(message + (char) END_OF_MESSAGE);
		outputStream.flush();
	}

	public void sendObjectToServer(Object object) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
	}

	public Object getObjectFromServer() throws IOException, ClassNotFoundException {
		 ObjectInputStream objectInputStream = new ObjectInputStream(connection.getInputStream());  
         Object receivedObject = objectInputStream.readObject();
         return receivedObject;
	}

	public String getTextFromServer() throws IOException {
		final int CAPACITY = 100;
		StringBuilder receivedMessage = new StringBuilder(CAPACITY);
		int temp;
		while ((temp = inputStreamReader.read()) != END_OF_MESSAGE) {
			receivedMessage.append((char) temp);
		}
		return receivedMessage.toString();

	}
}
