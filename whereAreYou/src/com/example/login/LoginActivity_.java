package com.example.login;


import com.example.database.MyDatabaseHelper;
import com.example.whereareyou.MainActivity;
import com.example.whereareyou.R;
import com.example.whereareyou.R.layout;
import com.example.whereareyou.R.menu;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//新建活动
public class LoginActivity_ extends Activity {

	private MyDatabaseHelper dbHelper;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_activity_);
		
		Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final String user1 = bundle.getString("user");
        final String pwd1 = bundle.getString("pwd");
		
		dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 2);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		//获取用户名与密码文本和登录与注册按钮情况
		final EditText edt_user = (EditText)this.findViewById(R.id.edit_reg_user);
		final EditText edt_pwd = (EditText)this.findViewById(R.id.edt_reg_pass);
		final Button btn_signin = (Button)this.findViewById(R.id.btn_signupnow);
		final Button btn_signup = (Button)this.findViewById(R.id.btn_signup);
		
		edt_user.setText(user1);
		edt_pwd.setText(pwd1);
		
		final CheckBox  rememberPass = (CheckBox)this.findViewById(R.id.checkBox1);
		boolean isRemember = pref.getBoolean("remember_password", false);
		//确认是否成功？
		if(isRemember)
		{
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			edt_user.setText(account);
			edt_pwd.setText(password);
			rememberPass.setChecked(true);
		}
		/* setOnclicklistener方法传的参数是OnClickListener接口，
		 * 需要new一个接口当做参数传入，对象调用方法都是对象名.
		 */
		btn_signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbHelper.getWritableDatabase();
				final Intent intent = new Intent();
				
				intent.setClass(LoginActivity_.this, SignupActivity.class);
				startActivity(intent);
			}
		});
		
		btn_signin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String user = edt_user.getText().toString().trim();
				final String pwd = edt_pwd.getText().toString().trim();
				editor = pref.edit();	
				if(rememberPass.isChecked())
				{
					editor.putBoolean("remember_password", true);
					editor.putString("account", user);
					editor.putString("password", pwd);		
				}
				else
				{
					editor.clear();
				}
				editor.commit();
				if(user.equals(""))
				{
					Toast.makeText(LoginActivity_.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
				}
				else if(pwd.equals(""))					
				{
					Toast.makeText(LoginActivity_.this, "请输入密码！", Toast.LENGTH_SHORT).show();
				}
				
				else
				{
					//从后台数据库中查找数据
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					Cursor cursor = db.rawQuery("select * from User where user = ? ", new String[] {user});
					
					
					if(cursor.moveToFirst()){
						
						//找到了注册过的用户之后进行密码判断
						Cursor cursor1 = db.rawQuery("select * from User where user = ? and pwd = ? ", new String[] {user,pwd});
						if(cursor1.moveToNext())
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();											
						
						intent.setClass(LoginActivity_.this, MainActivity.class);
						startActivity(intent);
						LoginActivity_.this.finish();
						}
						else
						{
							Toast.makeText(LoginActivity_.this, "用户名和密码不匹配，请重新输入！", Toast.LENGTH_SHORT).show();
						}		
					}
					else 		
					{
						Toast.makeText(LoginActivity_.this, "该用户名未进行过注册，请先注册！", Toast.LENGTH_SHORT).show();
					}
					cursor.close();
				}
			}
		});
	}
	
	//自定义选项菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_activity_ , menu);
		return true;
	}

}
