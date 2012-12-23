package org.BEProject.migrationclient;

import java.io.IOException;

import org.BEProject.migrationclient.Communication.Client.Client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ClientAgentHome extends Activity {

	private static final String MIGRATE_ACTION = "com.sourabh.dummy.clientagent.MIGRATE_REQUEST";
	private static final String HOST_NAME = "localhost";
	private static final int PORT_NUMBER = 9090;
	private static final boolean ACCEPTED = true;
	private static final String REQUEST = null;
	private static final String DATA_FILES_PATHS = "DATA_FILES_PATHS";
	private Intent resultIntent;
	private Client client;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_agent_home);

		/*
		 * Basic Algorithm for Migration establish connection with server send
		 * request to server
		 *  if request accepted
		 *  	 send objects asynchronously to server
			 *  send data files to cloud asynchronously once data file transfer is successful
			 *   send paths to emulator 
			 *   wait for response 
			 *   send response to calling activity
			 *   
		 *    
		 *    else send error code to calling activity
		 */
		Intent intent = getIntent();
		int resultCode = RESULT_CANCELED;
		resultIntent=null;
		if (intent.getAction().equals(MIGRATE_ACTION))
		{
			try {
				if (migrateRequest() == ACCEPTED) 
				{
					resultIntent = migrate(intent);
					resultCode=RESULT_OK;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				resultIntent = new Intent();
				resultIntent.putExtra("EXCEPTION", e);
			}
		 }
		setResult(resultCode, resultIntent);
		finish();

	}

	private Intent migrate(Intent intent)
	{
		Bundle bundle=intent.getExtras();
		String dataFilesPaths[]=bundle.getStringArray(DATA_FILES_PATHS);
		
		client.sendObjectToServer(innerIntent);
		
		return null;
	}

	private boolean migrateRequest() throws IOException
	{
		client = new Client(HOST_NAME, PORT_NUMBER);
		// TODO change request to class and method
		client.sendTextToServer(REQUEST);
		String response = client.getTextFromServer();
		return response.equals("ACCEPTED") ? true : false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_client_agent_home, menu);
		return true;
	}
}
