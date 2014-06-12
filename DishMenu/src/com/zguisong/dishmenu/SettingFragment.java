package com.zguisong.dishmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingFragment extends Fragment {
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.setting_layout, container,
				false);

		TextView title = (TextView) rootView.findViewById(R.id.settingText);
		title.setText("模仿微信的设置界面");

		
		return rootView;
		// return inflater.inflate(R.layout.navigation_main, container, false);

	}	
}
