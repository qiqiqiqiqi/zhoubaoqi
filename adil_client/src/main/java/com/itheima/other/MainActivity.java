package com.itheima.other;

import com.itheima.remote.IService;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	MyConn conn ; 
	IService binder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
//			binder = (IService)service; �޷�����ֱ��ǿ������ת�������׳�ת�������쳣
			binder = IService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	}

	//��06�еķ���
	public void bind(View v){
		Intent  service = new Intent();
		service.setAction("com.itheima.remote.SERVICE");
		
		conn = new MyConn();
		bindService(service, conn, BIND_AUTO_CREATE);
	}
	
	//����06�����еķ���
	public void call(View v){
		try {
			binder.callMethodInService();
			binder.callMethodInService2();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	//�����
	public void unbind(View v){
		unbindService(conn);
	}
}
