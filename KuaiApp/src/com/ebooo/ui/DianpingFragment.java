package com.ebooo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.ebooo.R;

public class DianpingFragment extends Fragment{
	View dianpingView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	dianpingView = inflater.inflate(R.layout.dianping_fragment, container, false);
        ButterKnife.inject(this, dianpingView);
        return dianpingView;
    }
}
