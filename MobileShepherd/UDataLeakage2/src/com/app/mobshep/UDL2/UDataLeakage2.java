package com.app.mobshep.UDL2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UDataLeakage2 extends Activity implements OnClickListener {

	Button bLogin;
	Button bForgot;
	EditText username;
	String usernameVar = "Jack";
	EditText password;
	static String tempPass;
	static Boolean passwordReset = false;

	private static final String TAG = "MyActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.broken);
		referenceXML();
		
		logDetails("My name is Jack Meade, I'm here to kick ass and drink gravy... and I'm all outta gravey!");
		logDetails("Today I had chicken again! I love Chicken! #deliciousChicken #whyDoIDoThis");
		logDetails("The house is flooded... uh oh");
		logDetails("Misplaced my phone again, found it in the microwave.");
		logDetails("My mother just married again! Goodbye Mrs. Meade hello Mrs Jenkins!");
		logDetails("Sunglasses! Sunglasses everywhere!");

	}

	private void referenceXML() {
		// TODO Auto-generated method stub
		bLogin = (Button) findViewById(R.id.bLogin);
		bForgot = (Button) findViewById(R.id.bForgot);
		username = (EditText) findViewById(R.id.etName);
		password = (EditText) findViewById(R.id.etPass);
		bForgot.setOnClickListener(this);
		bLogin.setOnClickListener(this);

	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {

		case (R.id.bForgot):
			Intent gotoForgot = new Intent("com.app.mobshep.UDL2.Forgotton");
			startActivity(gotoForgot);
			break;

		case (R.id.bLogin):

			String CheckName = username.getText().toString();
			String CheckPass = password.getText().toString();

			Log.d(TAG, "temp pass = " + Forgotton.tempPassVar
					+ "passwordReset = " + passwordReset);

			if (passwordReset == true)

				if (CheckName.equals(usernameVar)) {

					if (CheckPass.equals(tempPass)) {
						Toast loggedIn = Toast.makeText(UDataLeakage2.this,
								"Logged in!", Toast.LENGTH_LONG);
						loggedIn.show();
						
						Intent loggedInIntent = new Intent("com.app.mobshep.UDL2.Main");
						startActivity(loggedInIntent);
						
					}

				} else {
					Toast locked = Toast.makeText(UDataLeakage2.this,
							"Invalid Password!", Toast.LENGTH_SHORT);
					locked.show();

					Log.d(TAG, "The password is " + tempPass);
				}

			else {
				Toast locked = Toast.makeText(UDataLeakage2.this,
						"You're account has been locked!", Toast.LENGTH_SHORT);
				locked.show();
				break;
			}

			if (CheckName.contentEquals("") || CheckPass.contentEquals("")) {
				Toast empty = Toast.makeText(UDataLeakage2.this,
						"Empty Fields Detected.", Toast.LENGTH_SHORT);
				empty.show();
			}

			if (CheckName.equals("Jack") == false
					|| CheckPass.equals(Forgotton.tempPassVar) == false
					|| passwordReset == false) {
				Toast invalid = Toast.makeText(UDataLeakage2.this,
						"Invalid Credentials!", Toast.LENGTH_SHORT);
				invalid.show();

				Log.d(TAG, CheckName);
				Log.d(TAG, CheckPass);

				Log.d(TAG, "temp pass = " + Forgotton.tempPassVar
						+ "passwordReset = " + passwordReset);
				break;
			}
			break;
		}
	}

	private void logDetails(String content) {
		// TODO Auto-generated method stub
		Date date = new Date();

		String filename = "DEBUGLog" + date.toString();
		String EOL = System.getProperty("line.seperator");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(
					filename, Context.MODE_WORLD_READABLE)));
			writer.write(content + EOL);
			writer.write(date + EOL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
