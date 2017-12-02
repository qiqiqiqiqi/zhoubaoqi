package com.itheima.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class ServiceDemo extends Service{
	
	
	private class MyBinder extends IService.Stub{
		@Override
		public void callMethodInService() {
			methodInService();
		}

		@Override
		public void callMethodInService2() throws RemoteException {
			methodInService2();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind....");
		return new MyBinder();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate....");
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("onDestroy....");
	}
	
	
	public void methodInService(){
		System.out.println("--name==="+Thread.currentThread().getName());
//		Toast.makeText(this, "我是服务的方法，我被调用了.", 0).show();
		System.out.println("remote service methodInService()");
	}
	public void methodInService2(){
		System.out.println("--name==="+Thread.currentThread().getName());
//		Toast.makeText(this, "我是服务的方法，我被调用了.", 0).show();
		System.out.println("remote service methodInService2()");
	}
}
