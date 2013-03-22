package de.azapps.mirakel;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;

public class Mirakel extends Application {
	public static final int LIST_ALL = 0;
	public static final int LIST_DAILY = -1;
	public static final int LIST_WEEKLY = -2;
	public static final int[] PRIO_COLOR = { Color.parseColor("#008000"),
			Color.parseColor("#00c400"), Color.parseColor("#3377FF"),
			Color.parseColor("#FF7700"), Color.parseColor("#FF3333") };
	public static final String ORDER_BY_PRIO = "priority desc";
	public static final String ORDER_BY_DUE = " CASE WHEN due='' OR due=0 THEN 1 ELSE 0 END, due ASC";
	public static final String ORDER_BY_ID = "_id asc";
	public final static short SYNC_STATE_NOTHING = 0;
	public final static short SYNC_STATE_DELETE = -1;
	public final static short SYNC_STATE_ADD = 1;
	public final static short SYNC_STATE_NEED_SYNC = 2;
	public static final int DATABASE_VERSION = 3;
	
	public class Http_Mode {
		final public static int GET = 0;
		final public static int POST = 1;
		final public static int PUT = 2;
		final public static int DELETE = 3;
	}
	
	private static final String TAG = "Mirakel";

	private static SQLiteOpenHelper openHelper;

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
		openHelper = new DatabaseHelper(this);
		Mirakel.getWritableDatabase().execSQL("PRAGMA foreign_keys=ON;");
	}

	public static SQLiteDatabase getWritableDatabase() {
		return openHelper.getWritableDatabase();
	}

	public static SQLiteDatabase getReadableDatabase() {
		return openHelper.getReadableDatabase();
	}
	

}