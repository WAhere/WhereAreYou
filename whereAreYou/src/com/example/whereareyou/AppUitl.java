package com.example.whereareyou;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;		//导入电话窃听器管理包

public class AppUitl {
	//打电话的功能模块
	public void call(Context context, String number){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		Uri data = Uri.parse("tel:"+number);
		intent.setData(data);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);///////////////////
	}
	//发短信的共模块
	public void sendSms(String number , String sms){
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, sms, null, null);
	}
	
	
	
	//获取系统的时间
	public String getTime(){
		String time = " ";
		Date date = new Date();					// java获得date的当前的时间。
		time += (date.getYear()+1900)+"/";		//1900是系统默认造出的时间，添加后才是正常计年
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
		//静态方法强制当前正在执行的线程休眠（暂停执行），以“减慢线程”。
		//线程睡眠的原因：线程执行太快，或者需要强制进入下一轮，因为Java规范不保证合理的轮换。所以睡眠的实现：要调用静态方法。
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
