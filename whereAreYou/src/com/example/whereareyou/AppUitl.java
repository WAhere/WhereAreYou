package com.example.whereareyou;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class AppUitl {
	//打电话
	public void call(Context context, String number){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		Uri data = Uri.parse("tel:"+number);
		intent.setData(data);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);///////////////////
	}
	//发短信
	public void sendSms(String number , String sms){
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, sms, null, null);
	}
	
	//获取时间
	public String getTime(){
		String time = " ";
		Date date = new Date();
		time += (date.getYear()+1900)+"/";
		time += (date.getMonth()+1)+"/";
		time += (date.getDate())+"/";
		time += "   ";
		time += (date.getHours())+":";
		if(date.getMinutes()<10){
			time += "0";
		}
		time += (date.getMinutes())+":";
		if(date.getSeconds()<10){
			time += "0";
		}
		time += (date.getSeconds());
		return time;
	}
	public void ring(Context context){
		MediaPlayer player = MediaPlayer.create(context, R.raw.mi);
		player.start();
		try{
		Thread.sleep(30*1000);
		}catch(Exception e){}
		player.stop();
		
	}
	public void vibra(Context context){
		Vibrator vibrator =(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(30*1000);
	}
}
