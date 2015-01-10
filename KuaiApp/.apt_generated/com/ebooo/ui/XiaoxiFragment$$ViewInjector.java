// Generated code from Butter Knife. Do not modify!
package com.ebooo.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class XiaoxiFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ebooo.ui.XiaoxiFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361868, "field 'msglist'");
    target.msglist = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131361867, "field 'xiaoxicon'");
    target.xiaoxicon = (android.widget.TextView) view;
  }

  public static void reset(com.ebooo.ui.XiaoxiFragment target) {
    target.msglist = null;
    target.xiaoxicon = null;
  }
}
