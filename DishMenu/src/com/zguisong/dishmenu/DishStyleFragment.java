package com.zguisong.dishmenu;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zguisong.dishmenu.utility.DishMenuProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class DishStyleFragment extends Fragment{	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	private  List<Map<String, Object>> dishStyleList;

	private PicFromSdcardAdapter sa1;
	private PicFromSdcardAdapter sa2;
	private PicFromSdcardAdapter sa3;
	private PicFromSdcardAdapter sa4;
	private PicFromSdcardAdapter sa5;
	private PicFromSdcardAdapter sa6;
	private List<String> imageUrls1 = new ArrayList<String>();
	private List<String> imageName1 = new ArrayList<String>();
	private List<String> imagePrices1 = new ArrayList<String>();
	private List<String> imageUrls2 = new ArrayList<String>();
	private List<String> imageName2 = new ArrayList<String>();
	private List<String> imagePrices2 = new ArrayList<String>();
	private List<String> imageUrls3 = new ArrayList<String>();
	private List<String> imageName3 = new ArrayList<String>();
	private List<String> imagePrices3 = new ArrayList<String>();
	private List<String> imageUrls4 = new ArrayList<String>();
	private List<String> imageName4 = new ArrayList<String>();
	private List<String> imagePrices4 = new ArrayList<String>();
	private List<String> imageUrls5 = new ArrayList<String>();
	private List<String> imageName5 = new ArrayList<String>();
	private List<String> imagePrices5 = new ArrayList<String>();
	private List<String> imageUrls6 = new ArrayList<String>();
	private List<String> imageName6 = new ArrayList<String>();
	private List<String> imagePrices6 = new ArrayList<String>();
	private GridView gvNavigation = null ;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		options = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.ic_stub)
//		.showStubImage(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();  		
    
		/*
		 * 查询DishMenu数据库获取各种dish_style字段的图片路径dish_photo及其dish_title
		*并将其添加到对应的适配器中
		*/
		ContentResolver cr = getActivity().getContentResolver();
		Cursor dishCursor = cr.query(DishMenuProvider.DISH_URI, null, null, null, null);
		if(dishCursor != null && dishCursor.getCount() !=0 ){ 
			//访问DishMenu数据库dish表的所有条目
			String[] filePathColumn = { "dish_photo", "dish_name",
					"dish_style", "dish_price" };
			if (dishCursor.moveToFirst()) {
				do {
					int photoIndex = dishCursor
							.getColumnIndex(filePathColumn[0]);
					int nameIndex = dishCursor
							.getColumnIndex(filePathColumn[1]);
					int styleIndex = dishCursor
							.getColumnIndex(filePathColumn[2]);
					int priceIndex = dishCursor
							.getColumnIndex(filePathColumn[3]);
					String dishPhoto = dishCursor.getString(photoIndex);
					String dishName = dishCursor.getString(nameIndex);
					String dishStyle = dishCursor.getString(styleIndex);
					String dishPrice = dishCursor.getString(priceIndex);

					if (dishStyle.equals(UtilConstant.dishStyleText[0])) {
						imageUrls1.add("file://" + dishPhoto);
						imageName1.add(dishName);
						imagePrices1.add(dishPrice);
					} else if (dishStyle.equals(UtilConstant.dishStyleText[1])) {
						imageUrls2.add("file://" + dishPhoto);
						imageName2.add(dishName);
						imagePrices2.add(dishPrice);
					} else if (dishStyle.equals(UtilConstant.dishStyleText[2])) {
						imageUrls3.add("file://" + dishPhoto);
						imageName3.add(dishName);
						imagePrices3.add(dishPrice);
					} else if (dishStyle.equals(UtilConstant.dishStyleText[3])) {
						imageUrls4.add("file://" + dishPhoto);
						imageName4.add(dishName);
						imagePrices4.add(dishPrice);
					} else if (dishStyle.equals(UtilConstant.dishStyleText[4])) {
						imageUrls5.add("file://" + dishPhoto);
						imageName5.add(dishName);
						imagePrices5.add(dishPrice);
					} else if (dishStyle.equals(UtilConstant.dishStyleText[5])) {
						imageUrls6.add("file://" + dishPhoto);
						imageName6.add(dishName);
						imagePrices6.add(dishPrice);
					} else {
						// 如果该图片路径无法读取到文件，则从DishMenu.db中删除
					}
				} while (dishCursor.moveToNext());
			}
			dishCursor.close();
		}
		
		sa1 = new PicFromSdcardAdapter(imageUrls1,imageName1,imagePrices1);
		sa2 = new PicFromSdcardAdapter(imageUrls2,imageName2,imagePrices2);
		sa3 = new PicFromSdcardAdapter(imageUrls3,imageName3,imagePrices3);
		sa4 = new PicFromSdcardAdapter(imageUrls4,imageName4,imagePrices4);
		sa5 = new PicFromSdcardAdapter(imageUrls5,imageName5,imagePrices5);
		sa6 = new PicFromSdcardAdapter(imageUrls6,imageName6,imagePrices6);

	}
		/*
		 * 查询DishMenu数据库获取各种dish_style字段的图片路径dish_photo及其dish_title
		*并将其添加到对应的适配器中
		*
		************************************End*******************************
		*/

		
   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
	        View rootView = inflater.inflate(R.layout.dishstyle_layout, container, false);
	        //利用GridView显示详细的每种菜式
	       gvNavigation = (GridView) rootView.findViewById(R.id.chefDishGridView);
			// 为gridview准备元素
	       gvNavigation.setAdapter(sa1);
	       
	        Button dishStyleButton1 = (Button)rootView.findViewById(R.id.dishStyleButton1);
	        dishStyleButton1.setText(UtilConstant.dishStyleText[0]);
	        dishStyleButton1.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 gvNavigation.setAdapter(sa1);
	            	 
	             }
	         });
	        Button dishStyleButton2 = (Button)rootView.findViewById(R.id.dishStyleButton2);
	        dishStyleButton2.setText(UtilConstant.dishStyleText[1]);
	        dishStyleButton2.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View v){					
	        		Intent getPicIntent = new Intent();
	        		getPicIntent.setType("image/*");
	        		getPicIntent.setAction("android.intent.action.GET_CONTENT");
//	        		Intent getPicIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	        		getActivity().startActivityForResult(getPicIntent,UtilConstant.PHOTO_PICKED_WITH_DATA);
	        	}
	        });
	        Button dishStyleButton3 = (Button)rootView.findViewById(R.id.dishStyleButton3);
	        dishStyleButton3.setText(UtilConstant.dishStyleText[2]);
	        dishStyleButton3.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 gvNavigation.setAdapter(sa3);
	             }
	         });
	        Button dishStyleButton4 = (Button)rootView.findViewById(R.id.dishStyleButton4);
	        dishStyleButton4.setText(UtilConstant.dishStyleText[3]);
	        dishStyleButton4.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 gvNavigation.setAdapter(sa4);
	             }
	         });
	        Button dishStyleButton5 = (Button)rootView.findViewById(R.id.dishStyleButton5);
	        dishStyleButton5.setText(UtilConstant.dishStyleText[4]);
	        dishStyleButton5.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 gvNavigation.setAdapter(sa5);
	             }
	         });
	        Button dishStyleButton6 = (Button)rootView.findViewById(R.id.dishStyleButton6);
	        dishStyleButton6.setText(UtilConstant.dishStyleText[5]);
	        dishStyleButton6.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
					gvNavigation.setAdapter(sa6);
			}
	         });        
	
			return rootView;
 //		return inflater.inflate(R.layout.navigation_main, container, false);

	}	
  
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//			ImageView dishImage = (ImageView) view.findViewById(R.id.dishImage);
			
	}
		

	private int getFragmentWidth() {  //参数为图片在sdcard中的存储路径
		WindowManager manager = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
		return manager.getDefaultDisplay().getWidth(); 
		    }
	private int getFragmentHeight() {  //参数为图片在sdcard中的存储路径
		WindowManager manager = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
		return manager.getDefaultDisplay().getHeight();
		    }
	public class PicFromSdcardAdapter extends BaseAdapter {
		   
		public PicFromSdcardAdapter(List<String> urls,List<String> names, List<String> prices) {
	                 this.imageUrls = urls;
	                 this.imageNames = names;
	                 this.imagePrices = prices;
	          }

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
	
			ViewHolder holder;
			
			if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.dishstyle_item, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.dishImage);
//                holder.orderImage = (ImageView)convertView.findViewById(R.id.orderedImage);
                holder.imageView.setOnClickListener(new OnClickListener(){
                	public void onClick(View v){
                		Toast.makeText(getActivity(), "点击选中此菜肴", Toast.LENGTH_SHORT).show();
                	}
                });

                holder.contentText = (TextView) convertView.findViewById(R.id.dishText);
 /*               holder.contentText.setOnClickListener(new OnClickListener(){
                	public void onClick(View v){
   //             		holder.contentText.setTextColor(getResources().getColor(R.color.myDishBackground));
                		
                	}
                });
*/                
                holder.priceText = (TextView) convertView.findViewById(R.id.priceText);
  /*              holder.priceText.setOnClickListener(new OnClickListener(){
                	public void onClick(View v){

                	}
                });  
  */                            
                final LinearLayout markedLayout =  (LinearLayout)convertView.findViewById(R.id.identifierDishStyle);
                
                markedLayout.setOnClickListener(new OnClickListener(){
                	public void onClick(View v){
                		markedLayout.setBackgroundColor(Color.RED);
                		Toast.makeText(getActivity(), "点击菜价", Toast.LENGTH_LONG).show();
                	}
                });
                
                convertView.setTag(holder);
			} else {

			}
			
            holder = (ViewHolder) convertView.getTag();
            imageLoader.displayImage(imageUrls.get(position), holder.imageView, options);
            //holder.orderImage.setImageResource(R.drawable.ic_unselected);
            holder.contentText.setText(imageNames.get(position));
            holder.priceText.setText(imagePrices.get(position));
            
            return convertView;
       
		}
        class ViewHolder {
            ImageView imageView;
            TextView contentText;
            TextView priceText;
//            TextView timeView;
        }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageUrls.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		List<String> getImageUrls(){
			return imageUrls;
		}
		List<String> getImageNames(){
			return imageNames;
		}
		void setImageUrls(List<String> newImageUrls){
			imageUrls= newImageUrls;
		}
		void setImageNames(List<String> newImageNames){
			imageNames = newImageNames;
		}
		private List<String> imageUrls ;
		private List<String> imageNames;
		private List<String> imagePrices;
	}

}

