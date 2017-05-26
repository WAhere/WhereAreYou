package com.example.login;

import com.example.database.MyDatabaseHelper;
import com.example.whereareyou.MainActivity;
import com.example.whereareyou.R;
import com.example.whereareyou.R.layout;
import com.example.whereareyou.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 2);
		
		final EditText edt_user = (EditText)this.findViewById(R.id.edit_reg_user);
		final EditText edt_pwd = (EditText)this.findViewById(R.id.edt_reg_pass);
		final Button btn_signin = (Button)this.findViewById(R.id.btn_signupnow);
		final Button btn_signup = (Button)this.findViewById(R.id.btn_signup);
		
		/*final String user = edt_user.getText().toString().trim();
		final String pwd = edt_pwd.getText().toString().trim();*/
		
		/*edt_user.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(user == "User")
				{
					edt_user.setText("");
				}
			}
		});*/
		btn_signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbHelper.getWritableDatabase();
				final Intent intent = new Intent();
				/*Bundle bundle = new Bundle();
		    	bundle.putString("user",user1);
		    	intent.putExtras(bundle);*/
				intent.setClass(LoginActivity.this, SignupActivity.class);
				startActivity(intent);
			}
		});
		
		btn_signin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String user = edt_user.getText().toString().trim();
				final String pwd = edt_pwd.getText().toString().trim();
				if(user.equals(""))
				{
					Toast.makeText(LoginActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
				}
				else if(pwd.equals(""))					
				{
					Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
				}
				/*else if(pwd != "1")
				{
					Toast.makeText(LoginActivity.this, "密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
					edt_pwd.setText("");
				}*/
				else
				{
					final Intent intent = new Intent();
					/*Bundle bundle = new Bundle();
			    	bundle.putString("user",user1);
			    	intent.putExtras(bundle);*/
					intent.setClass(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					//LoginActivity.this.finish();
				}
			}
		});
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
