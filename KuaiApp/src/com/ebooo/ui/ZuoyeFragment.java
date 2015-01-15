package com.ebooo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.ebooo.R;

public class ZuoyeFragment extends Fragment{
	View zuoyeView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	zuoyeView = inflater.inflate(R.layout.zuoye_fragment, container, false);
        ButterKnife.inject(this, zuoyeView);
        return zuoyeView;
    }
    
}
