package com.ebooo.service;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 页面的的异步服务抽象
 */
public abstract class BaseService extends IntentService {
	private final String TAG = this.getClass().getName();
	private String activityName;
	private ExecutorService pool;
	@Override
	public void onCreate() {
		super.onCreate();
		pool = Executors.newCachedThreadPool();
	}

	public String getActivityName() {

		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public BaseService() {
		super("BaseService");
	}
	
	public BaseService(String name) {
		super(name);
	}

	protected abstract void executeCommand(String cmdId, HashMap params, long stamp);

	@Override
	protected void onHandleIntent(Intent arg0) {
		String cmdId = arg0.getStringExtra("cmdId");
		long stamp = arg0.getLongExtra("stamp", 0);
		activityName = arg0.getStringExtra("activity");
		HashMap params = (HashMap) arg0.getSerializableExtra("params");
		pool.execute(new Task(cmdId, params, stamp));
	}

	protected void returnMsgActivity(String cmdId, HashMap map, long stamp) {
		Log.d(TAG, "Return command");
		Intent myIntent = new Intent();// 创建Intent对象
		myIntent.setAction(activityName + String.valueOf(stamp));
		myIntent.putExtra("cmdId", cmdId);
		myIntent.putExtra("rtn", map);
		sendBroadcast(myIntent);// 发广播
	}

	private class Task implements Runnable {

		private final String cmdId;
		private final HashMap params;
		private final long stamp;

		public Task(String cmdId, HashMap params, long stamp) {
			this.cmdId = cmdId;
			this.params = params;
			this.stamp = stamp;
		}

		@Override
		public void run() {
			executeCommand(cmdId, params, stamp);
		}

	}

}
