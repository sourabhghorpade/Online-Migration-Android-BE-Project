package com.sourabh.dummy;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class ObjectFactorial extends Activity {

	private static final String NUMBER = "NUMBER";
	private static final String FACTORIAL = "FACTORIAL";
   
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_factorial);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        @SuppressWarnings("unchecked")
		HashMap<String, Integer> hashMap=(HashMap<String, Integer>) bundle.get(NUMBER);
        double number=hashMap.get(NUMBER);
        double factorialResult=calculateFactorialOfANumber(number);
        Intent resultData=new Intent();
        resultData.putExtra(FACTORIAL,factorialResult);
        setResult(RESULT_OK,resultData);
        Log.v(FACTORIAL,""+ factorialResult);
        finish();
    }
    public double calculateFactorialOfANumber(double number) 
	{
    	if(number==0)
    		return 1;
		return number * calculateFactorialOfANumber(number-1);
   	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_object_factorial, menu);
        return true;
    }
}
