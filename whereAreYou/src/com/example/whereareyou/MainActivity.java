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

public class MainActivity extends Activity {
	Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取所有组件的问题
		final EditText editNumber = (EditText)this.findViewById(R.id.editNumber);
		final Button btnPhone = (Button)this.findViewById(R.id.btnPhone);
		final Button btnSms = (Button)this.findViewById(R.id.btnSms);
		final Button btnCallBack = (Button)this.findViewById(R.id.btnCallBack);
		final Button btnRing = (Button)this.findViewById(R.id.btnRing);
		final Button btnVibra = (Button)this.findViewById(R.id.btnVibra);
		final Button btnListen = (Button)this.findViewById(R.id.btnListen);
		
		btnPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(AppContext.isPhone()){
					AppContext.setPhone(false);
					btnPhone.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
					btnPhone.setText("监控手机");	
					btnPhone.setBackgroundResource(R.drawable.e1);
					//btnPhone.getBackground().setAlpha(255);
					
					
					
				}else{
					AppContext.setPhone(true);
					btnPhone.setTextColor(Color.BLACK);
					btnPhone.setText("ֹͣ����ֻ�");
					btnPhone.setBackgroundResource(R.drawable.e12);
					
					//btnPhone.getBackground().setAlpha(100);
					
					
				}
			}
		});
		
		btnSms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					if(AppContext.isSms()){
						AppContext.setSms(false);
						btnSms.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
						btnSms.setText("��ض���");
						btnSms.setBackgroundResource(R.drawable.e2);
						//btnSms.getBackground().setAlpha(250);
					}else{
						AppContext.setSms(true);
						btnSms.setTextColor(Color.BLACK);
						btnSms.setText("ֹͣ��ض���");						
						btnSms.setBackgroundResource(R.drawable.e23);
						
					}
				}
		});
		

		
		btnRing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(AppContext.isRing()){
					AppContext.setRing(false);
					btnRing.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));
					btnRing.setText("����");
					btnRing.setBackgroundResource(R.drawable.e4);
				}else{
					AppContext.setRing(true);
					btnRing.setTextColor(Color.BLACK);
					btnRing.setText("ֹͣ����");
					btnRing.setBackgroundResource(R.drawable.e42);
				}
				
			}
		});
		
		btnVibra.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(AppContext.isVibra()){
					AppContext.setVibra(false);
					btnVibra.setTextColor(android.graphics.Color.argb(255, 141, 209, 239));//////////////
					btnVibra.setText("��");
					btnVibra.setBackgroundResource(R.drawable.e5);
				}else{
					AppContext.setVibra(true);
					btnVibra.setTextColor(Color.BLACK);
					btnVibra.setText("ֹͣ��");
					btnVibra.setBackgroundResource(R.drawable.e52);
				}
			}
		});
		
		btnListen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(AppContext.isListen()){
					AppContext.setListen(false);
					btnListen.setText("��ʼ���");
					btnListen.setTextColor(Color.RED);
					if(intent!=null){
					stopService(intent);
					}
				}else{
					String number = editNumber.getText().toString();
					if(number.equals("")){
						Toast.makeText(MainActivity.this, "������绰����", 3000).show();
						return ;
					}
					else if(AppContext.isPhone()||AppContext.isSms()||AppContext.isCallback()
							||AppContext.isRing()||AppContext.isVibra()){
					AppContext.setListen(true);
					btnListen.setTextColor(Color.BLACK);
					btnListen.setText("ֹͣ���");
					//btnListen.getBackground().setAlpha(0);
					
					//��������
					intent = new Intent();
					
					AppContext.setNumber(editNumber.getText().toString());
					intent.setClass(MainActivity.this, ListenService.class);
					startService(intent);
					}else{
						Toast.makeText(MainActivity.this, "���ǣ��ô�ѡһ��", 3000).show();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
