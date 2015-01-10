package com.ebooo.ui;

import com.ebooo.R;
import com.ebooo.R.layout;

import butterknife.ButterKnife;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TongxunluFragment extends Fragment{
	View tongxunluView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	tongxunluView = inflater.inflate(R.layout.tongxunlu_fragment, container, false);
        ButterKnife.inject(this, tongxunluView);
        return tongxunluView;
    }
}
