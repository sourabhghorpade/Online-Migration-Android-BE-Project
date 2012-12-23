package com.sourabh.dummy.serveragent;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ServerAgentHome extends Activity {
	private static final String NUMBER = "NUMBER";
	private static final String FACTORIAL = "FACTORIAL";
	private static final String ACTION = "com.sourabh.dummy.MIGRATE";	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=new Intent(ACTION);
        intent.putExtra(NUMBER,"100");
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) 
    {
    	if(resultCode== RESULT_OK)
    	{
    		double resultOfFactorialOperation=data.getDoubleExtra(FACTORIAL,0);
    		TextView outputTextView=(TextView)findViewById(R.id.messageTextView);
    		outputTextView.setText(""+resultOfFactorialOperation);
    	}
    }
}
