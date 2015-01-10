package com.ebooo.ui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;

import android.content.res.Configuration;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.ebooo.R;

public class MainActivity extends BaseFragmentActivity {
	private ActionBarDrawerToggle mDrawerToggle;
	@InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
	@InjectView(R.id.left_drawer) LinearLayout mDrawerList;
	@InjectView(R.id.main_content) View main_content_frame;
	@InjectView(R.id.main_menu) LinearLayout main_menu;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		ButterKnife.inject(this);
		initViews();
		menuChange(main_menu.getChildAt(0));
	}
	
	private void initViews(){
		LayoutInflater from = LayoutInflater.from(this);
		ArrayList<HashMap<String, String>> menus = (ArrayList<HashMap<String, String>>)getMenus().get(0).get("items");
		for(int i=0;i<menus.size();i++){
			LinearLayout inflate = (LinearLayout)from.inflate(R.layout.menu_item, main_menu, false);
			ImageView iv = ButterKnife.findById(inflate, R.id.menu_item_image);
			TextView tv = ButterKnife.findById(inflate, R.id.menu_item_text);
			iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(getPackageName()+":"+menus.get(i).get("icon"), null, null)));
			tv.setText(menus.get(i).get("title"));
			main_menu.addView(inflate);
			inflate.setTag(R.id.menu_fragment_id,menus.get(i).get("spec"));
			inflate.setTag(R.id.menu_fragment_class,menus.get(i).get("intent"));
			inflate.setTag(R.id.menu_fragment_icon,menus.get(i).get("icon"));
			inflate.setTag(R.id.menu_fragment_picon,menus.get(i).get("picon"));
			inflate.setTag(R.id.menu_fragment_title,menus.get(i).get("title"));
			inflate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					menuChange(v);
				}
			});
			fragmentMap.put(menus.get(i).get("spec"), null);
		}
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(eTitle);
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle("设置");
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	
	public void menuChange(View view) {
		setTitle(view.getTag(R.id.menu_fragment_title).toString());
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		
		ImageView iv = ButterKnife.findById(view, R.id.menu_item_image);
		iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(getPackageName()+":"+view.getTag(R.id.menu_fragment_picon).toString(), null, null)));
		
		Fragment f = fragmentMap.get(view.getTag(R.id.menu_fragment_id).toString());
		if (f == null) {
			String fragmentStr = view.getTag(R.id.menu_fragment_class).toString();
			Class<?> forName;
			try {
				forName = Class.forName(fragmentStr);
				Fragment newInstance = (Fragment) forName.newInstance();
				transaction.add(R.id.main_content, newInstance);
				fragmentMap.put(view.getTag(R.id.menu_fragment_id).toString(), newInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			List<Fragment> fragments = fragmentManager.getFragments();
			for (Iterator iterator = fragments.iterator(); iterator.hasNext();) {
				Fragment fragment = (Fragment) iterator.next();
				if (f==fragment) {
					transaction.show(fragment);
					break;
				}
			}
		}
		transaction.commit();

	}
	
	private void clearSelection() {
		for(int i=0,j=main_menu.getChildCount();i<j;i++){
			ImageView iv = ButterKnife.findById(main_menu.getChildAt(i), R.id.menu_item_image);
			TextView tv = ButterKnife.findById(main_menu.getChildAt(i), R.id.menu_item_text);
			iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(getPackageName()+":"+main_menu.getChildAt(i).getTag(R.id.menu_fragment_icon).toString(), null, null)));
			tv.setText(main_menu.getChildAt(i).getTag(R.id.menu_fragment_title).toString());
		}
	}
	
	@Override
	public void setTitle(CharSequence title) {
		eTitle = title;
		getSupportActionBar().setTitle(title);
	}
	
	private void hideFragments(FragmentTransaction transaction) {
		List<Fragment> fragments = fragmentManager.getFragments();
		if(fragments==null)return;
		for (Iterator iterator = fragments.iterator(); iterator.hasNext();) {
			Fragment fragment = (Fragment) iterator.next();
			transaction.hide(fragment);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {

		default:
			return super.onOptionsItemSelected(item);
		}

	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	
	public List<HashMap<String, Object>> getMenus(){
		XmlResourceParser xrp = getResources().getXml(R.xml.menu);
		List<HashMap<String, Object>> menuList = null;
		HashMap<String, Object> menuMap = null;
		HashMap<String, String> item = null;
		try {
			int event = xrp.getEventType();
			while (event != XmlResourceParser.END_DOCUMENT) {
				String tagname = xrp.getName();
				switch (event) {
				case XmlPullParser.START_TAG:
					if ("menus".equals(tagname)) {
						menuList = new ArrayList<HashMap<String, Object>>();
					} else if ("menu".equals(tagname)) {
						menuMap = new HashMap<String, Object>();
						menuMap.put("role", xrp.getAttributeValue(0));
						menuMap.put("rolecode", xrp.getAttributeValue(1));
						menuMap.put("items", new ArrayList<HashMap<String, String>>());
					} else if ("item".equals(tagname)) {
						item = new HashMap<String, String>();
					} else if ("spec".equals(tagname)) {
						item.put("spec", xrp.nextText());
					} else if ("title".equals(tagname)) {
						item.put("title", xrp.nextText());
					} else if ("icon".equals(tagname)) {
						item.put("icon", xrp.nextText());
					} else if ("picon".equals(tagname)) {
						item.put("picon", xrp.nextText());
					} else if ("intent".equals(tagname)) {
						item.put("intent", xrp.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					String name = tagname;
					if (name.equals("item")) {
						((ArrayList<HashMap<String, String>>)menuMap.get("items")).add(item);
						item = null;
					}
					if (name.equals("menu")) {
						menuList.add(menuMap);
						menuMap = null;
					}
					break;
				}
				event = xrp.next();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return menuList;
	}
	
}
