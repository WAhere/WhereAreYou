package com.example.login;

import java.util.Timer;
import java.util.TimerTask;


import com.example.whereareyou.R;
import com.example.whereareyou.R.layout;
import com.example.whereareyou.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		final Intent intent = new Intent(this, com.example.whereareyou.MainActivity.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		    @Override
		    public void run() {
		    startActivity(intent);//执行 
		   WelcomeActivity.this.finish();
		    }
		};
		timer.schedule(task, 1000 * 2); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
