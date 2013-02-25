package com.example.windmapindia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.Settings.System;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.util.Log;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Button;
//import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.telephony.SmsManager;
import android.location.Criteria;
import android.location.Location;
//import android.location.LocationListener;
import android.location.LocationManager;




public class WindMapIndiaActivity extends Activity  
{
	
    EditText txtPhone,txtTem,txtWin,txtHum;
    Button btnsendsms,btnsendurl;
    LocationManager locationManager ;			
	String provider;
	
	   
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wind_map_india);
		txtPhone=(EditText)findViewById(R.id.editText1);
		txtTem=(EditText)findViewById(R.id.editText2);
		txtHum=(EditText)findViewById(R.id.editText3);
		txtWin=(EditText)findViewById(R.id.editText4);
		btnsendsms = (Button) findViewById(R.id.button1);
		btnsendurl = (Button) findViewById(R.id.button2);
		
		btnsendsms.setOnClickListener(new OnClickListener() 
		{
			 
			@Override
			public void onClick(View v) 
			{
 
				String phoneNo = txtPhone.getText().toString();
				String sms = txtTem.getText().toString() + "," + txtHum.getText().toString() + "," + txtWin.getText().toString() ;
 
				try 
				{
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
					Toast.LENGTH_LONG).show();
				} 
				catch (Exception e) 
				{
					Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
 
			}
		});
		
		btnsendurl.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View view) 
			{
				   	 String PhoneNo = txtPhone.getText().toString();
				   	 String Temp = txtTem.getText().toString();
				   	 String Humidity = txtHum.getText().toString();
				   	 String WindVelocity = txtWin.getText().toString();
				  
					try
				    {
				    	
				    	Uri uri = Uri.parse("http://127.0.0.1:8000/?&mno="+ PhoneNo + "&temp="+ Temp + "&humidity=" + Humidity + "&windvelocity=" + WindVelocity);
				    	/* above line gives could not redirect to 127.0.0.1 localhost ,   because android Emulator use his localhost that 							   is 10.0.2.2.
						   so try to run it on mobile.

						*/ 		
				    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				    	//startActivity(intent);
						Toast.makeText(getApplicationContext(),"your request sent!",Toast.LENGTH_LONG).show();
				    
							 			
				    }
				    catch (Exception e) 
					{
				        
				        e.printStackTrace();
				    }
				    	
				    		 
				   	
				    		 
				    		
			
			}		      
		
	});
}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		
		
		getMenuInflater().inflate(R.menu.wind_map_india, menu);
		return true;
	}
	
}
