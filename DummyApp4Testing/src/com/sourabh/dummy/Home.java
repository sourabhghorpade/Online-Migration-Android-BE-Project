package com.sourabh.dummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Home extends Activity {

    private static final String NUMBER = "NUMBER";
	private static final String FACTORIAL = "FACTORIAL";
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    public void sendMessage(View view)
    {
    	Intent intent= new Intent(this,com.sourabh.dummy.CalculateFactorial.class);
    	TextView inputTextView=(TextView)findViewById(R.id.textViewInput);
    	String numberString=inputTextView.getText().toString();
    	intent.putExtra(NUMBER,numberString);
    	startActivityForResult(intent, 1);
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
