package com.itheima.heimapay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class PayService extends Service {
	
	private class MyBinder extends IService.Stub{
		@Override
		public int callSafePay(String name, String pwd, int money) {
			return safePay(name, pwd, money);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind---");
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("oncreate---");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}

	/**
	 * ��ȫ֧���ĺ��Ĵ���
	 * 
	 * @param name
	 *            �û���
	 * @param pwd
	 *            ����
	 * @param money
	 *            ֧���Ľ��
	 * @return ����֧����״̬�룬�����404���˺�������� �� 200 �� ֧���ɹ� �� 250������
	 */
	public int safePay(String name, String pwd, int money) {
		if ("zhangsan".equals(name) && "10086".equals(pwd)) {
			if (money < 300) {
				return 200;
			}
			return 250;
		}
		return 404;
	}

}
