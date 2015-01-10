package com.ebooo.ui;

import java.util.HashMap;

import com.ebooo.R;
import com.ebooo.service.XiaoxiService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class XiaoxiFragment extends BaseFragment{
	MyAdapter adapter;
	View xiaoxiView;
	@InjectView(R.id.msglist) ListView msglist;
	@InjectView(R.id.xiaoxicon) TextView xiaoxicon;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		xiaoxiView = inflater.inflate(R.layout.xiaoxi_fragment, container, false);
		ButterKnife.inject(this, xiaoxiView);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("aaa", "kkkkkkkkkkkkkk");
		this.doAsyncCommnad(XiaoxiService.class, "YGYY", params);
		return xiaoxiView;
	}
	
	

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
	
	
	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context mContext;
		private static final String TAG = "MyAdapter";
		private ListView list;
		

		public MyAdapter(Context context, ListView xListView) {
			this.mContext = context;
			this.list = xListView;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}
	}

	@Override
	protected void onExcutedCommand(String commandId, HashMap rtnExtra) {
		xiaoxicon.setText(rtnExtra.get("aaa").toString());
		
	}
}
