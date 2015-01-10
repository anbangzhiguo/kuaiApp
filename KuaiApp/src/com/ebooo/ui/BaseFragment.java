package com.ebooo.ui;

import java.util.Date;
import java.util.HashMap;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;

abstract public class BaseFragment extends Fragment{
	private final long stamp = (new Date()).getTime();
	private final String className = this.getClass().getName();
	String serviceName;
	DataReceiver dataReceiver;
	
	@Override
	public void onStart() {
		if (dataReceiver == null) {
			dataReceiver = new DataReceiver();
		}
		regisistBroadcast(className + String.valueOf(stamp));
		super.onStart();
	}

	@Override
	public void onStop() {
		this.getActivity().unregisterReceiver(dataReceiver);
		super.onStop();
	}

	public void doAsyncCommnad(Class<?> serviceFullName, String cmdId, HashMap<String, Object> params) {
		Intent intent;
		intent = new Intent(this.getActivity(), serviceFullName);
		intent.putExtra("activity", this.getClass().getName());
		intent.putExtra("cmdId", cmdId);
		intent.putExtra("params", params);
		intent.putExtra("stamp", stamp);
		this.getActivity().startService(intent);

	}
	protected abstract void onExcutedCommand(String commandId, HashMap rtnExtra);
	
	protected void regisistBroadcast(String action) {
		if (dataReceiver == null) {
			dataReceiver = new DataReceiver();
		}
		IntentFilter filter = new IntentFilter();// ����IntentFilter����
		filter.addAction(action);
		this.getActivity().registerReceiver(dataReceiver, filter);// ע��Broadcast Receiver

	}
	
	protected class DataReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {// ��дonReceive����
			String a = className + String.valueOf(stamp);
			if (intent.getAction().equals(a)) {
				String cmdId = intent.getStringExtra("cmdId");
				HashMap map = (HashMap) intent.getSerializableExtra("rtn");
				onExcutedCommand(cmdId, map);
			} else {
				HashMap map = (HashMap) intent.getSerializableExtra("rtn");
				onRecieveBroadCast(intent.getAction(), map);
			}
		}

	}
	
	protected void onRecieveBroadCast(String action, HashMap result) {
		return;
	}
	
	protected BaseFragmentActivity getBaseActivity(){
		return (BaseFragmentActivity)this.getActivity();
	}

}
