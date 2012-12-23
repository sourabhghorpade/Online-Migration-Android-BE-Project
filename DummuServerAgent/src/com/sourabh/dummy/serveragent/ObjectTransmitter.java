package com.sourabh.dummy.serveragent;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.widget.TextView;

public class ObjectTransmitter extends Activity {

	private static final String NUMBER = "NUMBER";
	private static final String FACTORIAL = "FACTORIAL";
	private static final String ACTION = "com.sourabh.dummy.MIGRATE_WITH_OBJECT";	
   
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_transmitter);
        HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
        hashMap.put(NUMBER, 5);
        
        Intent intent=new Intent(ACTION);
        intent.putExtra(NUMBER,hashMap);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, RESULT_OK);
        boolean isIntentSafe = activities.size() > 0;
        if(isIntentSafe)
        	startActivityForResult(intent,0);
        else
        {
        	TextView outputTextView=(TextView)findViewById(R.id.messageTextView);
    		outputTextView.setText("Nope.");
		}
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_object_transmitter, menu);
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) 
    {
    	if(resultCode== RESULT_OK)
    	{
    		double resultOfFactorialOperation=data.getDoubleExtra(FACTORIAL,0);
    		TextView outputTextView=(TextView)findViewById(R.id.TextViewOutputText);
    		outputTextView.setText(""+resultOfFactorialOperation);
    	}
    }
}
