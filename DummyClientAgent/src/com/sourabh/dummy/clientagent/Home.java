package com.sourabh.dummy.clientagent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Home extends Activity 
{
	private static final String MIGRATE_ACTION="com.sourabh.dummy.clientagent.MIGRATE_REQUEST";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        if(intent.getAction().equals(MIGRATE_ACTION))
        {
        	// establish connection with server
        	// send request to server
        	// if request accepted 
        		// send objects asynchronously to server agent on emulator
        		// send data files to cloud asynchronously 
        			// once data file transfer is successful send paths to emulator
        		//wait for response
        		//send response to calling activity
        	// else
        		// send error code to calling activity
        }
        else
        {
        	finish();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
}
