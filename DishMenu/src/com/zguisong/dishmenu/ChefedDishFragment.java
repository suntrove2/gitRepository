package com.zguisong.dishmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zguisong.dishmenu.DishStyleFragment.PicFromSdcardAdapter;
import com.zguisong.dishmenu.DishStyleFragment.PicFromSdcardAdapter.ViewHolder;
import com.zguisong.dishmenu.utility.DishMenuProvider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ChefedDishFragment extends Fragment  implements OnItemClickListener{
	
	private List<String> recommendDishUrils = new ArrayList<String>();
	private List<String> recommendDishName = new ArrayList<String>();
	private PicFromSdcardAdapter sa;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;	
	
	
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
			String[] filePathColumn = { "dish_photo", "dish_name", "dish_recommend"};
			//访问DishMenu数据库dish表的所有条目
			if (dishCursor.moveToFirst()) {
				do {
					int photoIndex = dishCursor.getColumnIndex(filePathColumn[0]);
					int nameIndex = dishCursor.getColumnIndex(filePathColumn[1]);
					int recommendIndex = dishCursor.getColumnIndex(filePathColumn[2]);					
					String dishPhoto = dishCursor.getString(photoIndex);
					String dishName = dishCursor.getString(nameIndex);
					String dishRecommend = dishCursor.getString(recommendIndex);

					if (dishRecommend.equals("是")) {
						recommendDishUrils.add("file://" + dishPhoto);
						recommendDishName.add(dishName);
					} else {
						// 如果该图片路径无法读取到文件，则从DishMenu.db中删除
					}
				} while (dishCursor.moveToNext());
			}
			dishCursor.close();
		}
		sa = new PicFromSdcardAdapter(recommendDishUrils,recommendDishName);
		/*
		 * 查询DishMenu数据库获取各种dish_style字段的图片路径dish_photo及其dish_title
		*并将其添加到对应的适配器中
		************************************End*******************************
		*/
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.chefeddish_layout, container,
				false);

		TextView title = (TextView) rootView.findViewById(R.id.chefDishText);
		title.setText(R.string.chefedDish_Title);
		
		GridView gvNavigation = (GridView) rootView
				.findViewById(R.id.chefDishGridView);
		gvNavigation.setAdapter(sa);
		
		return rootView;
		// return inflater.inflate(R.layout.navigation_main, container, false);

	}	
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}
	public class PicFromSdcardAdapter extends BaseAdapter {
		   
		public PicFromSdcardAdapter(List<String> urls,List<String> names) {
	                 this.imageUrls = urls;
	                 this.imageNames = names;
	          }
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
/*
			final View layout;
			if (convertView == null) {
				layout=getActivity().getLayoutInflater().inflate(R.layout.chefeddish_item,null);

			} else {
				layout = convertView;
			}
			ImageView image=(ImageView)layout.findViewById(R.id.chefedDishImage);
			imageLoader.displayImage(imageUrls.get(position), image, options);
			//image.setImageResource(R.drawable.icon);
			TextView text=(TextView)layout.findViewById(R.id.chefedDishText);
			text.setText(imageNames.get(position));
			return layout;			
*/
			ViewHolder holder;
			if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.chefeddish_item, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.chefedDishImage);
//                    holder.imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                holder.contentView = (TextView) convertView.findViewById(R.id.chefedDishText);   				

                convertView.setTag(holder);
			} else {

			}
			
            holder = (ViewHolder) convertView.getTag();
            imageLoader.displayImage(imageUrls.get(position), holder.imageView, options);
            holder.contentView.setText(imageNames.get(position));

            return convertView;
       
		}
        class ViewHolder {
            ImageView imageView;
            TextView contentView;
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
	}
	
}
