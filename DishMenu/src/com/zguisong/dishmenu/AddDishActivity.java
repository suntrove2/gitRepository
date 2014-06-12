package com.zguisong.dishmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AddDishActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_dish_layout);
		
		//获得主活动传过来的意图
		Intent intent = getIntent();
		//获得意图中的数据
		Bundle bundle = intent.getExtras();
		String picturePath="";
		String pictureTitle="";
		if(bundle != null)
		{
			picturePath = (String) bundle.getCharSequence("PATH");
			pictureTitle = (String) bundle.getCharSequence("TITLE");
		}
		Toast.makeText(getApplicationContext(),
				picturePath + "\n" + pictureTitle, Toast.LENGTH_SHORT).show();
	
	}

}
