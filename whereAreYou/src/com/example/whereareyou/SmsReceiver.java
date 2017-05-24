package com.example.whereareyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;	//导入小部件信息提示框

public class SmsReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(context, "收到短信", 3000).show();
		Bundle bundle = intent.getExtras();		//把数据取出来
		Object objs[] = (Object[])bundle.get("pdus");	//接收到的东西放在objs上，多条短信用数组来接收
		
		//处理短信数据
		SmsMessage mess[] = new SmsMessage[objs.length];
		for(int i = 0; i<objs.length; i++){
			mess[i] = SmsMessage.createFromPdu((byte[])objs[i]);
			//objs数组转换成mess数组
		}
		//提取短信电话号码和内容
		for(int i = 0; i<mess.length; i++){
			SmsMessage message = mess[i];
			
			String number = message.getDisplayOriginatingAddress();		//消息过来的号码
			String sms = message.getDisplayMessageBody();
			
			if(AppContext.isSms()){
				if(!AppContext.getNumber().equals(number)
						&&!AppContext.getNumber().equals(number.substring(number.length()-4))){//从第几个开始取
					//转发短信	
					AppUitl util = new AppUitl();
					util.sendSms(AppContext.getNumber(), util.getTime()+" "+number+"send sms: "+sms);
					
				}
			}
		
			//回拨电话
			if (AppContext.isCallback()) {
				if (AppContext.getNumber

				().equals(number) ||

				AppContext.getNumber().equals(number.substring

				(number.length() - 4))) {
					if (sms.equals

					("Callback")) {
						AppUitl util =

						new AppUitl();
						util.call

						(context, number);
					}

				}

			}
			

			//响铃
			if (AppContext.isRing()) {
				if (AppContext.getNumber

				().equals(number) ||

				AppContext.getNumber().equals(number.substring

				(number.length() - 4))) {
					if (sms.equals

					("Ring")) {
						AppUitl

						util = new AppUitl();

						util.ring(context);
					}

				}

			}

			// 震动
			if (AppContext.isVibra()) {
				if (AppContext.getNumber

				().equals(number) ||

				AppContext.getNumber().equals(number.substring

				(number.length() - 4))) {
					if (sms.equals

					("Vibra")) {
						AppUitl

						util = new AppUitl();

						util.vibra(context);
					}
				}
			}
			
			
			}
		}	
	}


