package com.zguisong.dishmenu;

import com.zguisong.dishmenu.NavigationFragment.OnNavigationSelectedListener;
import com.zguisong.dishmenu.utility.DishMenuProvider;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class OperatingActivity extends FragmentActivity implements OnNavigationSelectedListener{
    private FragmentTabHost mTabHost = null;;  
    private View indicator = null;  	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * 
		 * ***********预装载图片，供测试用
		 * ****************************Begin********************
		 */
		ContentResolver cr = getContentResolver();
	    // Return all the saved Dish information
		Cursor dishCursor = cr.query(DishMenuProvider.DISH_URI, null, null, null, null);
		if(dishCursor.getCount() ==0 ){  //将系统图库中所有图片插入到DishMenu.db的Table dish中
			//从系统图库中查询所有图片并返回 _data、title字段
			String[] filePathColumn = { MediaStore.Images.Media.DATA ,MediaStore.Images.Media.TITLE};
			Cursor mediaCursor = cr.query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
			filePathColumn, null, null, null);
			int picCounter = 0;
			//如果可以访问系统图库并且查询结果不为0
			if (mediaCursor != null && mediaCursor.moveToFirst()) {
				
				do {
					int dataIndex = mediaCursor.getColumnIndex(filePathColumn[0]);
					int titleIndex = mediaCursor.getColumnIndex(filePathColumn[1]);
					String picturePath = mediaCursor.getString(dataIndex);
					String pictureTitle = mediaCursor.getString(titleIndex);
					//去掉图片Universal Image Loader @#&=+-_.,!()~'%20.jpg
					if(pictureTitle != "Universal Image Loader @#&=+-_.,!()~'%20"){
			      	ContentValues values = new ContentValues();
			      	String title= "";
			      	switch( Integer.parseInt(String.valueOf(pictureTitle.charAt(0)))){
			      	case 1:
			      		title= UtilConstant.dishStyleText[0];   
			      		break;
			      	case 2:
			      		title= UtilConstant.dishStyleText[1];  
			      		break;
			      	case 3:
			      		title= UtilConstant.dishStyleText[2];  
			      		break;
			      	case 4:
			      		title= UtilConstant.dishStyleText[3];   
			      		break;
			      	case 5:
			      		title= UtilConstant.dishStyleText[4];  
			      		break;
			      	case 6:
			      		title= UtilConstant.dishStyleText[5];  
			      		break;
			      		default:
			      			title = "没有读取到菜品类型";   	
			      	}
			      	values.put(DishMenuProvider.KEY_PHOTO, picturePath); 			      		        
			      	values.put(DishMenuProvider.KEY_NAME, pictureTitle.substring(1, pictureTitle.length()));
			      	values.put(DishMenuProvider.KEY_STYLE, title);   	 	
			    	values.put(DishMenuProvider.KEY_PRICE, (picCounter)*30%80+16);
			    	values.put(DishMenuProvider.KEY_COUNTER, picCounter);
			    	if(2 == picCounter%3){
			    		values.put(DishMenuProvider.KEY_RECOMMEND, "是");
			    	}else{
			    		values.put(DishMenuProvider.KEY_RECOMMEND, "否");
			    	}
			    	
			    	
			    	cr.insert(DishMenuProvider.DISH_URI, values);
			    	
			    	picCounter++;
					}
				} while(mediaCursor.moveToNext());			
		    }
			mediaCursor.close();
			dishCursor.close();
		}		
		/*
		 * 
		 * 预装载图片，供测试用
		 * ****************************End********************
		 */	
		setContentView(R.layout.activity_operating);

		/*		
        // Create an instance of ExampleFragment
//		ChefedDishFragment detailedDishFrm = new ChefedDishFragment();
		NavigationFragment navigationFrm = new NavigationFragment();
		DishStyleFragment dishStyleFrm = new DishStyleFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		//add the fragment detailedDishFrm to the mainActivity
//		fragmentTransaction.add(R.id.chefedDish_container, detailedDishFrm);
		fragmentTransaction.add(R.id.dishStyle_container, dishStyleFrm);
		fragmentTransaction.add(R.id.navigationFragment_container,navigationFrm);
		fragmentTransaction.commit();
*/
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);  
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);  
 
        // 添加tab名称和图标  
        indicator = getIndicatorView(0, R.layout.navigation_item); 
        mTabHost.addTab(mTabHost.newTabSpec("chefeddish")  
                .setIndicator(indicator), ChefedDishFragment.class, null);  
         
        indicator = getIndicatorView(1, R.layout.navigation_item); 
        mTabHost.addTab(  
                mTabHost.newTabSpec("dishstyle").setIndicator(indicator),  
                DishStyleFragment.class, null);  
             
        indicator = getIndicatorView(2, R.layout.navigation_item);  
        mTabHost.addTab(  
                mTabHost.newTabSpec("mydish").setIndicator(indicator),  
                MyDishFragment.class, null);
        indicator = getIndicatorView(3, R.layout.navigation_item);  
        mTabHost.addTab(  
                mTabHost.newTabSpec("populardish").setIndicator(indicator),  
                PopularDishFragment.class, null);
        indicator = getIndicatorView(4, R.layout.navigation_item);  
        mTabHost.addTab(  
                mTabHost.newTabSpec("setting").setIndicator(indicator),  
                SettingFragment.class, null);

	}
	
    private View getIndicatorView(int i, int layoutId) {  
        View v = getLayoutInflater().inflate(layoutId, null);
        ImageView imgView = (ImageView)v.findViewById(R.id.itemImage);
        imgView.setImageResource(UtilConstant.navigationImage[i]);
        TextView tv = (TextView) v.findViewById(R.id.itemText);  
        tv.setText(UtilConstant.navigationText[i]);  
        return v;  
    }  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.operating, menu);
		return true;
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// Intent intent =new Intent(this,AboutHeroActivty.class);

		// intent.putExtra("HERO", heros[position]);

		// this.startActivity(intent);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(resultCode, requestCode, data);
/*		
		if(resultCode == RESULT_OK && null != data){
			switch (requestCode) {
			case UtilConstant.PHOTO_PICKED_WITH_DATA://从系统图库media中取出图片，并查到对应表中
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA ,MediaStore.Images.Media.TITLE};
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();
				int dataIndex = cursor.getColumnIndex(filePathColumn[0]);
				int titleIndex = cursor.getColumnIndex(filePathColumn[1]);
				String picturePath = cursor.getString(dataIndex);
				String pictureTitle = cursor.getString(titleIndex);
				cursor.close();
				
				Intent addDishintent = new Intent(this, AddDishActivity.class);
				addDishintent.putExtra("PATH", picturePath);
				addDishintent.putExtra("TITLE", pictureTitle);
				startActivity(addDishintent);

				break;
 
			}
					
		}

*/		
			
		
	}
	
	@Override
	public void onNavigationSelected(int position) {
		// TODO Auto-generated method stub
		
	}
}
