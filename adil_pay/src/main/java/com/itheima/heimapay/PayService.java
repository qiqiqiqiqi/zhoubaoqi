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
	 * 安全支付的核心代码
	 * 
	 * @param name
	 *            用户名
	 * @param pwd
	 *            密码
	 * @param money
	 *            支付的金额
	 * @return 返回支付的状态码，如果是404：账号密码错误 ， 200 ： 支付成功 ， 250：余额不足
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
