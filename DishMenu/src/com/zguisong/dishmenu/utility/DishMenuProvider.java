package com.zguisong.dishmenu.utility;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class DishMenuProvider extends ContentProvider {

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		int count;
		
		switch(uriMatcher.match(arg0)){
		case DISH:
			count = dishMenuDB.delete(DISH_TABLE, arg1, arg2);
			break;
		case DISH_ID:
			String segment = arg0.getPathSegments().get(1);
			count = dishMenuDB.delete(DISH_TABLE, KEY_ID + "=" 
					+ segment 
					+ (!TextUtils.isEmpty(arg1) ? " AND (" 
					+ arg1 + ')' : ""), arg2);
			break;
		default:throw new IllegalArgumentException("Unsupported URI:" + arg0);
	}
	
	getContext().getContentResolver().notifyChange(arg0, null);
	return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)){
		case DISH: return "vnd.android.cursor.dir/vnd.zguisong.dishmenu.utility.dishprovider";
		case DISH_ID: return "vnd.android.cursor.item/vnd.zguisong.dishmenu.utility.dishprovider";
		default: throw new IllegalArgumentException("Unsupported URI: " + uri);
		}	
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = dishMenuDB.insert(DISH_TABLE, "contactInfo",values);

		if (rowID > 0) {
			Uri currentUri = ContentUris.withAppendedId(DISH_URI, rowID);
			getContext().getContentResolver().notifyChange(currentUri, null);
			return uri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		Context context = getContext();
		
		DishMenuDatabaseHelper dbHelper = new DishMenuDatabaseHelper(context, DATABASE_NAME, 
				null, DATABASE_VERSION);
		dishMenuDB = dbHelper.getWritableDatabase();
		return (dishMenuDB == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(DISH_TABLE);
		switch (uriMatcher.match(uri)) {
		case DISH_ID:
			qb.appendWhere(KEY_ID + "=" + uri.getPathSegments().get(1));
			break;
		default:
			break;
		}

		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = KEY_ID;
		} else {
			orderBy = sortOrder;
		}

		Cursor c = qb.query(dishMenuDB, projection, selection, selectionArgs,
				null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
	    int count;
	    switch (uriMatcher.match(uri)) {
	      case DISH: count = dishMenuDB.update(DISH_TABLE, values, 
	                                               selection, selectionArgs);
	                   break;

	      case DISH_ID: String segment = uri.getPathSegments().get(1);
	                     count = dishMenuDB.update(DISH_TABLE, values, KEY_ID 
	                             + "=" + segment 
	                             + (!TextUtils.isEmpty(selection) ? " AND (" 
	                             + selection + ')' : ""), selectionArgs);
	                     break;

	      default: throw new IllegalArgumentException("Unknown URI " + uri);
	    }

	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;
	
	}

	public static final Uri DISH_URI = 
			Uri.parse("content://com.zguisong.dishmenu.utility.dishprovider/dish");	
	public static final String AUTHORITY ="com.zguisong.dishmenu.utility.dishprovider";
	
	private static final int DISH = 1;
	private static final int DISH_ID = 2;
	
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "dish", DISH);
		uriMatcher.addURI(AUTHORITY, "dish/#", DISH_ID);
	}
		
	private SQLiteDatabase dishMenuDB;
	private static final String TAG = "DishMenuContentProvider";
	private static final String DATABASE_NAME = "DishMenu.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DISH_TABLE = "tb_dish";
	
	public static final String KEY_ID = "_id";
	public static final String KEY_PHOTO = "dish_photo";
	public static final String KEY_NAME = "dish_name";
	public static final String KEY_STYLE = "dish_style";
	public static final String KEY_PRICE = "dish_price";
	public static final String KEY_COUNTER = "dish_counter";
	public static final String KEY_RECOMMEND = "dish_recommend";
//	public static final String KEY_INGREDIENTS_DETAILS= "dish_ingredients_details";
//	public static final String KEY_CHEF = "dish_chef";
//	public static final String KEY_TOTAL_TIME = "dish_total_time";
	
	
	public static final int PHOTO_COLUM = 1;
	public static final int NAME_COLUM = 2;
	public static final int STYLE_COLUM = 3;
	public static final int PRICE_COLUMN  = 4;
	public static final int COUNTER_COLUMN  = 5;
	public static final int RECOMMEND_COLUMN  = 6;
//	public static final int INGREDIENTS_DETAILS_COLUM = 6;
//	public static final int CHEF_COLUMN  = 7;
//	public static final int TOTAL_TIME_COLUM  = 8;
		
	private static class DishMenuDatabaseHelper extends SQLiteOpenHelper{
		private static final String DISH_TABLE_CREATE = "create table " + DISH_TABLE + "("
				+ KEY_ID + " integer primary key autoincrement, "
				+ KEY_PHOTO + " TEXT, "
				+ KEY_NAME + " TEXT, "
				+ KEY_STYLE + " TEXT, "
//				+ KEY_INGREDIENTS_DETAILS + " NVARCHAR(40),"
//				+ KEY_CHEF + " NVARCHAR(10),"
				+ KEY_PRICE + " FLOAT, "
				+ KEY_COUNTER + " INTEGER, "
				+ KEY_RECOMMEND + " TEXT); ";
					
		public DishMenuDatabaseHelper(Context context, String name, 
				CursorFactory factory, int version){
			super(context, name, factory, version);
		}
		
		@Override 
		public void onCreate(SQLiteDatabase db){
			
//			db.execSQL("DROP IF EXIST " + DISH_TABLE);
			db.execSQL(DISH_TABLE_CREATE);
			
		}
		
				
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			Log.w(TAG, "Upgarding database from version" + oldVersion + "to" + newVersion +
					", which will destroy all old date");
			db.execSQL("DROP IF EXIST " + DISH_TABLE);
			onCreate(db);
	}

		
		
}

}		
	

