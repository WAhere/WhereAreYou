package com.example.login;

import java.util.Timer;
import java.util.TimerTask;

import com.example.welcome.WelcomeActivity;
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

public class SignupActivity extends Activity {
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		final EditText edt_reg_user = (EditText)this.findViewById(R.id.edit_reg_user);
		final EditText edt_reg_pwd = (EditText)this.findViewById(R.id.edt_reg_pass);
		final EditText edt_reg_conf = (EditText)this.findViewById(R.id.edt_reg_conf);
		final Button btn_signupnow = (Button)this.findViewById(R.id.btn_signupnow);
		
		btn_signupnow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String user = edt_reg_user.getText().toString().trim();
				final String pwd = edt_reg_pwd.getText().toString().trim();
				final String repwd = edt_reg_conf.getText().toString().trim();
				
				if(user.equals(""))
				{
					Toast.makeText(SignupActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
				}
				else if(pwd.equals(""))
				{
					Toast.makeText(SignupActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
				}
				
				else if(!repwd.equals(pwd))
				{
					Toast.makeText(SignupActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
				}
				
				else
				{
				
					Toast.makeText(SignupActivity.this, "注册成功,正在跳转登录界面...", Toast.LENGTH_SHORT).show();
					final Intent intent = new Intent(SignupActivity.this, com.example.login.LoginActivity.class);
					Timer timer = new Timer();
					TimerTask task = new TimerTask() 
					{
					    @Override
					    public void run() 
					    {
					    	startActivity(intent);		//执行 
					    	SignupActivity.this.finish();		//结束
					    }
					};
					timer.schedule(task, 1000 * 2); 
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}

}
