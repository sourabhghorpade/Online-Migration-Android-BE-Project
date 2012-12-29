package com.sourabh.dummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MigrationHome extends Activity
{

    private static final String NUMBER = "NUMBER";
	private static final String FACTORIAL = "FACTORIAL";
	private static final String MIGRATE_ACTION ="com.sourabh.dummy.clientagent.MIGRATE_REQUEST";
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    public void sendMessage(View view)
    {
    	Intent intent= new Intent(MIGRATE_ACTION);
    	TextView inputTextView=(TextView)findViewById(R.id.textViewInput);
    	String numberString=inputTextView.getText().toString();
    	Intent innerIntent=new Intent();
    	innerIntent.putExtra(FACTORIAL,numberString);
    	intent.putExtra("INNER_INTENT", innerIntent);
    	startActivityForResult(intent,0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) 
    {
    	if(resultCode== RESULT_OK)
    	{
    		Double resultOfFactorialOperation=Double.valueOf(data.getDoubleExtra(FACTORIAL,0));
    		TextView outputTextView=(TextView)findViewById(R.id.textViewOutput);
    		outputTextView.setText(resultOfFactorialOperation.toString());
    	}
    }
    
}
