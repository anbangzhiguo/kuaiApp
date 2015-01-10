package com.ebooo.ui;

import com.ebooo.R;
import com.ebooo.R.layout;

import butterknife.ButterKnife;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ZuoyeFragment extends Fragment{
	View zuoyeView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	zuoyeView = inflater.inflate(R.layout.zuoye_fragment, container, false);
        ButterKnife.inject(this, zuoyeView);
        return zuoyeView;
    }
    
}
