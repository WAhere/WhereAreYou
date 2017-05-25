package com.example.whereareyou;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	Intent intent = null;
	@Override
	/*保护创建，这个是android开发里的activity一个生命周期
	 * activity有以下周期函数：
	 * onCreate(),onStart(),onRestart(),onResume(),onPause(),onStop(),onDestroy()
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 获取所有的组件问题
		final EditText editNumber = (EditText)this.findViewById(R.id.editNumber);	//获取文本（输入号码）
		final Button btnPhone = (Button)this.findViewById(R.id.btnPhone);			//获取（监控手机）按钮状态
		final Button btnSms = (Button)this.findViewById(R.id.btnSms);				//获取（监控短信）按钮状态
		final Button btnCallBack = (Button)this.findViewById(R.id.btnCallBack);		//获取（回拨电话）按钮状态
		final Button btnRing = (Button)this.findViewById(R.id.btnRing);				//获取（响铃）按钮状态
		final Button btnVibra = (Button)this.findViewById(R.id.btnVibra);			//获取（振动）按钮状态
		final Button btnListen = (Button)this.findViewById(R.id.btnListen);			//获取（开始监听）按钮状态
		
		//设置点击事件
		btnPhone.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(AppContext.isPhone())
				{
					AppContext.setPhone(false);
					//btnPhone.setTextColor(0XFF0000FF);
					btnPhone.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
					btnPhone.setText("监控手机");	
					btnPhone.setBackgroundResource(R.drawable.e1);
					//btnPhone.getBackground().setAlpha(255);
					//手机里获得的背景透明度为满值
				}
				else
				{
					AppContext.setPhone(true);
					btnPhone.setTextColor(Color.BLACK);
					btnPhone.setText("停止监控手机");
					btnPhone.setBackgroundResource(R.drawable.e12);
					
					//btnPhone.getBackground().setAlpha(100);
					//手机里获得的背景透明度为20/51，半透明
				}
			}
		});
		
		btnSms.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub 系统自动生成代码
					if(AppContext.isSms())
					{
						AppContext.setSms(false);
						btnSms.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
						btnSms.setText("监控短信");
						btnSms.setBackgroundResource(R.drawable.e2);
						//btnSms.getBackground().setAlpha(250);
						//短信服务使用getBackground().setAlpha来改变透明度后，关联的透明度也会跟着被改变。此处应为高透明度
					}
					else
					{
						AppContext.setSms(true);
						btnSms.setTextColor(Color.BLACK);
						btnSms.setText("停止监控短信");						
						btnSms.setBackgroundResource(R.drawable.e23);
					}
				}
		});
		
		btnCallBack.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if(AppContext.isCallback())
				{
					AppContext.setCallback(false);
					btnCallBack.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
					btnCallBack.setText("回拨电话");
					btnCallBack.setBackgroundResource(R.drawable.e3);
				}
				else
				{
						AppContext.setCallback(true);
						btnCallBack.setTextColor(Color.BLACK);
						btnCallBack.setText("停止回拨电话");
						btnCallBack.setBackgroundResource(R.drawable.e32);
				}
			}
		});
		
		btnRing.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(AppContext.isRing())
				{
					AppContext.setRing(false);
					btnRing.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
					btnRing.setText("响铃");
					btnRing.setBackgroundResource(R.drawable.e4);
				}
				else
				{
					AppContext.setRing(true);
					btnRing.setTextColor(Color.BLACK);
					btnRing.setText("停止响铃");
					btnRing.setBackgroundResource(R.drawable.e42);
				}
			}
		});
		
		btnVibra.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if(AppContext.isVibra())
				{
					AppContext.setVibra(false);
					btnVibra.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));//////////////
					btnVibra.setText("震动");
					btnVibra.setBackgroundResource(R.drawable.e5);
				}
				else
				{
					AppContext.setVibra(true);
					btnVibra.setTextColor(Color.BLACK);
					btnVibra.setText("停止震动");
					btnVibra.setBackgroundResource(R.drawable.e52);
				}
			}
		});
		
		btnListen.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(AppContext.isListen())
				{
					AppContext.setListen(false);
					btnListen.setText("开始监控");
					btnListen.setTextColor(Color.RED);
					if(intent!=null){
					stopService(intent);
					}
				}
				else
				{
					String number = editNumber.getText().toString();
					if(number.equals(""))
					{
						Toast.makeText(MainActivity.this, "请输入电话号码", 3000).show();
						return ;
					}
					else if(AppContext.isPhone()||AppContext.isSms()||AppContext.isCallback()
							||AppContext.isRing()||AppContext.isVibra())
					{
					AppContext.setListen(true);
					btnListen.setTextColor(Color.BLACK);
					btnListen.setText("停止监控");
					//btnListen.getBackground().setAlpha(0);
					//监听器服务，解释同上
					
					//启动服务
					intent = new Intent();
					
					AppContext.setNumber(editNumber.getText().toString());
					intent.setClass(MainActivity.this, ListenService.class);
					startService(intent);
					}
					// 当没有选择功能的时候，跳出提示
					else
					{
						Toast.makeText(MainActivity.this, "哥们，好歹选一个", 3000).show();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
