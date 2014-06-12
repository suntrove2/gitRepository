package com.zguisong.dishmenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;

import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class NavigationFragment extends Fragment implements OnItemClickListener {

	OnNavigationSelectedListener mCallback;

	// The container activity must implement this interface so the fragment can
	// deliver the message.
	public interface OnNavigationSelectedListener {
		public void onNavigationSelected(int position);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			

		
//		Toast.makeText(this.getActivity(), "Fragment.onCreat()",Toast.LENGTH_SHORT );

	}

	@Override
	public void onStart() {
		super.onStart();

		// When in two-plane layout, set the listview the highlight the selected
		// list item
		// (We do this during onStart because at the point listview is
		// available)

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented the
		// callback interface. If not, it throws an exception.
		try {
			mCallback = (OnNavigationSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement interface OnNavigationSelectedListener");
		}

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
	        View rootView = inflater.inflate(R.layout.navigation_layout, container, false);
	        /*		        GridView gvNavigation = (GridView) rootView.findViewById(R.id.navigation_main);
	        
			// 为NavigationFrgment的布局文件R.layout.navigation_main准备grid元素
//			GridView gvNavigation = (GridView) getActivity().findViewById(R.id.navigation_main);
			ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
			if (UtilConstant.navigationImage.length == UtilConstant.MENU_CATEGORY
					&& UtilConstant.navigationText.length == UtilConstant.MENU_CATEGORY) {
				for (int i = 0; i < UtilConstant.MENU_CATEGORY; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("icon", UtilConstant.navigationImage[i]);// 添加图像资源的ID
					map.put("name", UtilConstant.navigationText[i]);// 按序号做ItemText
					al.add(map);
				}
				
				SimpleAdapter sa = new SimpleAdapter(getActivity(), al,
						R.layout.navigation_item, new String[] { "icon", "name" },
						new int[] { R.id.itemImage, R.id.itemText });
				gvNavigation.setAdapter(sa);
	//			gvNavigation.setOnItemClickListener(this);
			} else {
				// 配置文件出错，导航栏的图标、文字个数与列数不等
			}
*/			
			return rootView;
 //		return inflater.inflate(R.layout.navigation_main, container, false);

	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		if(parent != null ){
			switch (position){
			case 0:
				Toast.makeText(getActivity(), "单击第一个按键",Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getActivity(), "单击第er个按键",Toast.LENGTH_SHORT).show();
				break;
				
			}
		}
		
		// Intent intent =new Intent(this,AboutHeroActivty.class);

		// intent.putExtra("HERO", heros[position]);

		// this.startActivity(intent);

	}

}
