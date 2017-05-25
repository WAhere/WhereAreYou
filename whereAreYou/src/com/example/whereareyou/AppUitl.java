package com.example.whereareyou;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;		//导入电话窃听器管理包

public class AppUitl 
{
	//打电话的功能模块
	public void call(Context context, String number)
	{
		Intent intent = new Intent();			//定义
		intent.setAction(Intent.ACTION_CALL);	//活动
		//这里的parse方法返回的是一个URI类型，通过这个URI可以访问一个网络上或者是本地的资源，
		//android中指定了uri是tel:number号码是对应的打电话的资源。
		Uri data = Uri.parse("tel:"+number);	
		intent.setData(data);					//表示获取数据
		/*在一个Activity环境中用该方法启动一个一个activity不会出任何问题，
		 * 但在activity之外的其他组件中使用该方法就会出现错误！
		 * Context 中有一个startActivity方法，Activity继承自Context，
		 * 重载了startActivity方法。如果使用Activity的 startActivity方法，
		 * 不会有任何限制，而如果使用Context的startActivity方法的话，
		 * 就需要开启一个新的task，遇到上 面那个异常的，
		 * 都是因为使用了Context的startActivity方法。解决办法是，加一个flag。
		 */
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	//发短信的功能模块
	public void sendSms(String number , String sms)
	{
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(number, null, sms, null, null);
	}
	
	//获取系统的时间
	public String getTime()
	{
		String time = " ";		//设标记为空
		Date date = new Date();					// java获得date的当前的时间。
		//年月日的设定
		time += (date.getYear()+1900)+"/";		//1900是系统默认造出的时间，添加后才是正常计年
		time += (date.getMonth()+1)+"/";		//在国外的系统中，取值是从0开始的，我们国人不习惯，所以，+1
		time += (date.getDate())+"/";			//日期没有变化
		time += "   ";
		//时分秒的设定
		// 设定显示时间的格式如果是小于0,比如3,那么就显示03，大于10的正常显示
		if(date.getHours()<10)
		{
			time += "0";
		}
		time += (date.getHours())+":";
		if(date.getMinutes()<10)
		{
			time += "0";
		}
		time += (date.getMinutes())+":";
		if(date.getSeconds()<10)
		{
			time += "0";
		}
		time += (date.getSeconds());
		//获得最终取值后返回时间最终值
		return time;
	}
	
	
	//响铃程序的正常实现
	public void ring(Context context)
	{
		MediaPlayer player = MediaPlayer.create(context, R.raw.mi);
		//启动程序
		player.start();
					
		try
		{
			Thread.sleep(30*1000);	//睡眠
		}
		catch(Exception e){}	//错误提示
		player.stop();		//运行停止			
	}
	
	//振动程序的正常实现
	public void vibra(Context context)
	{
		Vibrator vibrator =(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		//手机振动器的设置		
		vibrator.vibrate(30*1000);
	}
}