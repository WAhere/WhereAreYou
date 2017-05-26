package com.example.whereareyou;

import java.text.DecimalFormat;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class ListenService extends Service
{
	@Override
	/*onBind方法就是当试图绑定服务时做的事，作用一般情况下主要是返回IBinder对象，
	 * 为后面服务成功绑定时的操作做准备，也可以做一些服务初始化之类的事。
	 * 只有在onBind方法里面返回一个IBinder对象的时候onServiceConnected才会被调用，说明服务成功绑定，
	 */
	public IBinder onBind(Intent intent) 
	{
		return null;
	}

    @Override
    //开始的命令
    public int onStartCommand(Intent intent, int flags, int startId) 
    {
    	/*Toast:是一个类，主要管理消息的提示。
    	 * makeText()，是Toast的一个方法，用来显示信息，分别有三个参数。
    	 * 第一个参数：this，是上下文参数，指当前页面显示;
    	 * 第二个参数：“string”是你想要显示的内容，也可以是“服务已经启动”。这个是随便定义的，显示你想要显示的内容。
    	 * 第三个参数：Toast.LENGTH_LONG，是你指你提示消息，显示的时间，这个是稍微长点儿，
    	 * 对应的另一个是ToastLENGTH_SHORT，这个时间短点儿，大概2秒钟。
    	 * show()，表示显示这个Toast消息提醒，当程序运行到这里的时候，就会显示出来，
    	 * 如果不调用show()方法，这个Toast对象存在，但是并不会显示，所以一定不要忘记。
    	 */
    	Toast.makeText(this, "服务已经启动", Toast.LENGTH_LONG).show();
    	if(AppContext.isPhone())
    	{
    		// 这里是监控电话的功能
    		final TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    		manager.listen(new PhoneStateListener()
    		{
    			public void onCallStateChanged(int state, String incomingNumber) 
    			{
    				if(state == TelephonyManager.CALL_STATE_RINGING)
    				{
    					String serviceString = Context.LOCATION_SERVICE;// 获取的是位置服务
    					LocationManager locationManager = (LocationManager) getSystemService(serviceString);// 调用getSystemService()方法来获取LocationManager对象
    					String provider = LocationManager.GPS_PROVIDER;// 指定LocationManager的定位方法
    					android.location.Location location = locationManager.getLastKnownLocation(provider);// 调用getLastKnownLocation()方法获取当前的位置信息
    					double lat = location.getLatitude();//获取纬度
    					double lng = location.getLongitude();//获取经度
    					DecimalFormat  df  = new DecimalFormat("######0.00"); 
    					String d1 = df.format(lat);
    					String d2 = df.format(lng);
    					// 发短信给主控手机
    					//Toast.makeText(ListenService.this, "有电话了", 3000).show();
    					if(!AppContext.getNumber().substring(AppContext.getNumber().length()-4).equals(incomingNumber)
    							&&!AppContext.getNumber().substring(AppContext.getNumber().length()-4).equals(incomingNumber.substring(incomingNumber.length()-4))){
    					AppUitl util = new AppUitl();
    		    		String time = util.getTime();
    		    		String sms = time + ":" + incomingNumber + " to " + manager.getLine1Number()+",经度是 ："+d1+",纬度是："+d2;
    		    		util.sendSms(AppContext.getNumber(), sms);
    					}
    				}
    			};
    		}	
    		, PhoneStateListener.LISTEN_CALL_STATE);
    	}
    	//当选择任意选项时触发结果
    	if(AppContext.isSms() || AppContext.isCallback() || AppContext.isRing() || AppContext.isVibra())
    	{
    		// 监控短信
    		//Toast.makeText(this, "短信已经启动", Toast.LENGTH_LONG).show();
    		SmsReceiver receiver = new SmsReceiver();
    		IntentFilter filter = new IntentFilter();
    		filter.addAction("android.provider.Telephony.SMS_RECEIVED");  		
    		filter.setPriority(Integer.MAX_VALUE);
    		registerReceiver(receiver,filter);	
    	}
    	//返回变量值
    	return super.onStartCommand(intent, flags, startId);
    }
}

