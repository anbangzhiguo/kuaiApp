package com.ebooo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.ebooo.R;

public class TongxunluFragment extends Fragment{
	View tongxunluView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	tongxunluView = inflater.inflate(R.layout.tongxunlu_fragment, container, false);
        ButterKnife.inject(this, tongxunluView);
        return tongxunluView;
    }
}
