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
		
		//����������������ͼ
		Intent intent = getIntent();
		//�����ͼ�е�����
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
