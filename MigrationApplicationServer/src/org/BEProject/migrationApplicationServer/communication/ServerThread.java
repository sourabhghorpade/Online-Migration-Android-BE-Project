/*
 * @author Sourabh Ghorpade
 */

// JOB: To handle a request asynchronously

package org.BEProject.migrationApplicationServer.communication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

class ServerThread extends Thread {
	OutputStreamWriter outputStream;
	InputStreamReader inputStreamReader;
	Socket connection;

	final int END_OF_MESSAGE;

	public ServerThread(Socket connection, int END_OF_MESSAGE)
			throws IOException {
		this.connection = connection;
		this.END_OF_MESSAGE = END_OF_MESSAGE;
	}

	public void sendObjectToClient(Object object) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(connection.getOutputStream()));
		out.writeObject(object);
		out.flush();
	}

	public Object getObjectFromClient() throws IOException,
			ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(
				connection.getInputStream());
		Object receivedObject = objectInputStream.readObject();
		return receivedObject;

	}

	void handleRequest() throws IOException, InterruptedException,
			ClassNotFoundException {
		Object request = getObjectFromClient();
		 if(!validRequest(request))
			 return;
		request=getObjectFromClient();
		sendObjectToClient(request);
		/*
		 * while(true) { Object request= getObjectFromClient();
		 * if(request.equals("STOP")) break; String response=request +
		 * " Processed by Thread " + this.getId(); System.out.println(response);
		 * sendTextToClient(response); }
		 */
		System.out.println(" Thread " + this.getId() + " is Done.");
	}

	private boolean validRequest(Object request) 
	{
		return true;
	}

	public void sendTextToClient(String message) throws IOException {
		outputStream.write(message + (char) END_OF_MESSAGE);
		outputStream.flush();
	}

	public String getTextFromClient() throws IOException {
		final int STRING_BUFFER_CAPACITY = 100;
		StringBuilder stringBuilder = new StringBuilder(STRING_BUFFER_CAPACITY);
		int temp;
		while ((temp = inputStreamReader.read()) != END_OF_MESSAGE)
			stringBuilder.append((char) temp);
		return stringBuilder.toString();
	}

	@Override
	public void run() {
		try {
			handleRequest();
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

	}

}
