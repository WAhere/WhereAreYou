package com.example.login;

import java.util.Timer;
import java.util.TimerTask;

import com.example.whereareyou.R;
import com.example.whereareyou.R.layout;		//导入布局包
import com.example.whereareyou.R.menu;			//导入菜单包

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

//新建类WelcomeActivity继承Activity
public class WelcomeActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		final Intent intent = new Intent(this, com.example.whereareyou.MainActivity.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() 
		{
		    @Override
		    public void run() 
		    {
		    	startActivity(intent);		//执行 
		    	WelcomeActivity.this.finish();		//结束
		    }
		};
		timer.schedule(task, 1000 * 2); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		//创建加载你要的布局文件，也就是你看到的页面菜单，你按手机menu按键时出现的那个菜单;布局填充气，填充你自己设计的menu;
		//如果菜单已经存在，则会将项目添加到操作栏。
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
