package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* 创建一个名字为MyDatabaseHelper的数据库，
 * 新建一张User表，表中有id(主键)，user，pwd属性。
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
	//创建USER的表
	public static final String CREATE_USER = "create table User ("
			+"id integer primary key autoincrement, "		//主键（整数），自动增长
			+"user text, "					//用户名（文本）
			+"pwd text)";					//密码（文本）
	
	private Context mContext;				//私有对象参数
	
	//在SQLiteOepnHelper的子类当中，必须有该构造函数 
	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) 
	{
		//必须通过super（表示调用父类的构造函数）调用父类当中的构造函数
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	/* 该函数是在第一次创建数据库的时候执行,
	 * 实际上是在第一次得到SQLiteDatabse对象的时候，
	 * 才会调用这个方法
	 */
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		//execSQL函数用于执行SQL语句“创建表 ”
		db.execSQL(CREATE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub
		//execSQL函数用于执行SQL语句
		db.execSQL("drop table if exists User");
		onCreate(db);
	}
}
