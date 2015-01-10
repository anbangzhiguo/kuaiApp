// Generated code from Butter Knife. Do not modify!
package com.ebooo.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.ebooo.ui.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361862, "field 'main_menu'");
    target.main_menu = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131361860, "field 'mDrawerLayout'");
    target.mDrawerLayout = (android.support.v4.widget.DrawerLayout) view;
    view = finder.findRequiredView(source, 2131361863, "field 'mDrawerList'");
    target.mDrawerList = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131361861, "field 'main_content_frame'");
    target.main_content_frame = view;
  }

  public static void reset(com.ebooo.ui.MainActivity target) {
    target.main_menu = null;
    target.mDrawerLayout = null;
    target.mDrawerList = null;
    target.main_content_frame = null;
  }
}
