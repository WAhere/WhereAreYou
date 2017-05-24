package com.example.whereareyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;	//导入小部件信息提示框

public class SmsReceiver extends BroadcastReceiver
{
	@Override
	//context与intent就是发送广播时的:收到context，执行intent.
	public void onReceive(Context context, Intent intent) 
	{
		// TODO Auto-generated method stub
		//Toast.makeText(context, "收到短信", 3000).show();
		Bundle bundle = intent.getExtras();		//把数据取出来
		Object objs[] = (Object[])bundle.get("pdus");	//接收到的东西放在objs上，多条短信用数组来接收
		
		//处理短信数据
		SmsMessage mess[] = new SmsMessage[objs.length];
		for(int i = 0; i<objs.length; i++)
		{
			mess[i] = SmsMessage.createFromPdu((byte[])objs[i]);
			//objs的数组转换成mess的数组
		}
		//提取短信电话号码和内容
		for(int i = 0; i<mess.length; i++)
		{
			SmsMessage message = mess[i];
			
			//消息过来的号码
			String number = message.getDisplayOriginatingAddress();
			String sms = message.getDisplayMessageBody();
			
			if(AppContext.isSms())
			{
				if(!AppContext.getNumber().equals(number)
						&& !AppContext.getNumber().equals(number.substring(number.length()-4)))
				{//从第几个开始取
					//转发短信	
					AppUitl util = new AppUitl();
					util.sendSms(AppContext.getNumber(), util.getTime()+
							" "+number+"send sms: "+sms);	
				}
			}
		
			//当选中回拨电话时
			if (AppContext.isCallback()) 
			{
				if (AppContext.getNumber().equals(number) ||
						AppContext.getNumber().
						equals(number.substring
						(number.length() - 4))) 
				{
					if (sms.equals("Callback")) 
					{
						AppUitl util =new AppUitl();
						util.call(context, number);
					}
				}
			}
			
			//当选择响铃时
			if (AppContext.isRing()) 
			{
				if (AppContext.getNumber().equals(number) ||
						AppContext.getNumber().
						equals(number.substring
						(number.length() - 4))) 
				{
					if (sms.equals("Ring")) 
					{
						AppUitl util = new AppUitl();
						util.ring(context);
					}
				}
			}

			// 当选择振动时
			if (AppContext.isVibra()) 
			{
				if (AppContext.getNumber().equals(number) ||
						AppContext.getNumber().
						equals(number.substring
						(number.length() - 4))) 
				{
					if (sms.equals("Vibra")) 
					{
						AppUitl util = new AppUitl();
						util.vibra(context);
					}
				}
			}
		}
	}	
}
