package com.zguisong.dishmenu;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zguisong.dishmenu.ChefedDishFragment.PicFromSdcardAdapter;
import com.zguisong.dishmenu.utility.DishMenuProvider;

import android.content.ContentResolver;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyDishFragment extends Fragment {
	private List<String> orderedDishUrils = new ArrayList<String>();
	private List<String> orderedDishName = new ArrayList<String>();
	private List<Integer> orderedDishCounters = new ArrayList<Integer>();
	private List<Double> orderedDishPrices = new ArrayList<Double>();
	private PicFromSdcardAdapter sa;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;	
	
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.mydish_layout, container,
				false);
		ListView orderedDish = (ListView) rootView.findViewById(R.id.mydish_listview);
		orderedDish.setAdapter(sa);
		return rootView;
		// return inflater.inflate(R.layout.navigation_main, container, false);

	}
	  
	 @Override
	public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			
			//getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
			imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
			options = new DisplayImageOptions.Builder()
//			.showImageOnLoading(R.drawable.ic_stub)
//			.showStubImage(R.drawable.ic_stub)
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
				String[] filePathColumn = { "dish_photo", "dish_name", "dish_price"};
				//访问DishMenu数据库dish表的所有条目
				int counters =0;
				int totals = dishCursor.getCount();
				if (dishCursor.moveToFirst()) {
					do {
						int photoIndex = dishCursor.getColumnIndex(filePathColumn[0]);
						int nameIndex = dishCursor.getColumnIndex(filePathColumn[1]);
						int orderIndex = dishCursor.getColumnIndex(filePathColumn[2]);
						String dishPhoto = dishCursor.getString(photoIndex);
						String dishName = dishCursor.getString(nameIndex);
						double dishPrice = dishCursor.getDouble(orderIndex);


						if ((counters % 8) == 2 ) {
							orderedDishUrils.add("file://" + dishPhoto);
							orderedDishName.add(dishName);
							orderedDishCounters.add(1);
							orderedDishPrices.add(dishPrice);
						} else {
							// 如果该图片路径无法读取到文件，则从DishMenu.db中删除
						}
						counters++;
					} while (dishCursor.moveToNext());
				}
				dishCursor.close();
			}
			sa = new PicFromSdcardAdapter(orderedDishUrils,orderedDishName,orderedDishCounters,orderedDishPrices);
			/*
			 * 查询DishMenu数据库获取各种dish_style字段的图片路径dish_photo及其dish_title
			*并将其添加到对应的适配器中
			************************************End*******************************
			*/
			
		}

	public class PicFromSdcardAdapter extends BaseAdapter {
				   
			public PicFromSdcardAdapter(List<String> urls,List<String> names,List<Integer> dishCounters,List<Double> dishPrices) {
		                 this.imageUrls = urls;
		                 this.imageNames = names;
		                 this.dishCounters = dishCounters;
		                 this.dishPrices= dishPrices;
		          }

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				MyDishViewHolder holder;
				if (convertView == null) {
		            convertView = getActivity().getLayoutInflater().inflate(R.layout.mydish_listview_item, null);
		            holder = new MyDishViewHolder();
		            holder.imageView = (ImageView) convertView.findViewById(R.id.mydish_dishPhotoImgView);
		            holder.nameTxView = (TextView) convertView.findViewById(R.id.mydish_dishNameText);
		            holder.counterTxView = (TextView)convertView.findViewById(R.id.mydish_dishNumberText);
		            holder.priceTxView = (TextView)convertView.findViewById(R.id.mydish_dishPriceText);
		            convertView.setTag(holder);
				} else {

				}
				
		        holder = (MyDishViewHolder) convertView.getTag();
		        imageLoader.displayImage(imageUrls.get(position), holder.imageView, options);
		        holder.nameTxView.setText(imageNames.get(position));
		        holder.counterTxView.setText(dishCounters.get(position).toString());
		        holder.priceTxView.setText(dishPrices.get(position).toString());

		        return convertView;
		   
			}
		    class MyDishViewHolder {
		        ImageView imageView;
		        TextView nameTxView;
		        TextView counterTxView;
		        TextView priceTxView;
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
			private List<Integer> dishCounters ;
			private List<Double> dishPrices;
		}	 
}

