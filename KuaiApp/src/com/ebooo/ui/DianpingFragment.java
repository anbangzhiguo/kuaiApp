package com.ebooo.ui;

import com.ebooo.R;
import com.ebooo.R.layout;

import butterknife.ButterKnife;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DianpingFragment extends Fragment{
	View dianpingView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	dianpingView = inflater.inflate(R.layout.dianping_fragment, container, false);
        ButterKnife.inject(this, dianpingView);
        return dianpingView;
    }
}
