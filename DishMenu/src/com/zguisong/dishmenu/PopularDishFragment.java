package com.zguisong.dishmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PopularDishFragment extends Fragment {

	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.populardish_layout, container,
				false);

		TextView title = (TextView) rootView.findViewById(R.id.popularDishText);
		title.setText("最受欢迎的菜品");

		
		return rootView;
		// return inflater.inflate(R.layout.navigation_main, container, false);

	}	
}
