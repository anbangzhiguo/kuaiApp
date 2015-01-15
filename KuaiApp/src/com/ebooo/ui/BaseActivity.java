package com.ebooo.ui;

import java.util.Date;
import java.util.HashMap;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
@SuppressWarnings("rawtypes")
abstract public class BaseActivity extends ActionBarActivity{
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
		this.unregisterReceiver(dataReceiver);
		super.onStop();
	}
	
	protected void regisistBroadcast(String action) {
		if (dataReceiver == null) {
			dataReceiver = new DataReceiver();
		}
		IntentFilter filter = new IntentFilter();
		filter.addAction(action);
		this.registerReceiver(dataReceiver, filter);

	}
	
	protected class DataReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
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
	
	public void doAsyncCommnad(Class<?> serviceFullName, String cmdId, HashMap<String, Object> params) {
		Intent intent;
		intent = new Intent(this, serviceFullName);
		intent.putExtra("activity", this.getClass().getName());
		intent.putExtra("cmdId", cmdId);
		intent.putExtra("params", params);
		intent.putExtra("stamp", stamp);
		this.startService(intent);
	}
	
	protected abstract void onExcutedCommand(String commandId, HashMap rtnExtra);

	protected void onRecieveBroadCast(String action, HashMap result) {
		return;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {
				hideSoftInput(v.getWindowToken());
				v.clearFocus();
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	private boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
			if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param token the token
	 */
	private void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * Show toast.
	 * 
	 * @param content the content
	 */
	protected void showToast(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}
}
