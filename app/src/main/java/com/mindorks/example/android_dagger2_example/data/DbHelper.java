package com.mindorks.example.android_dagger2_example.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mindorks.example.android_dagger2_example.data.model.User;
import com.mindorks.example.android_dagger2_example.di.ApplicationContext;
import com.mindorks.example.android_dagger2_example.di.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by janisharali on 25/12/16.
 */

/*
* DbHelper class will be used by DataManager to access the SQLite database.
*
* SQLiteOpenHelper 를 상속한 Db Handling 클래스 입니다.
*
* @Singleton : globally single instance 만 사용하도록 세팅됩니다
*              어디서라도 DbHelper 와의 관계를 요청한다면, 어디서라도 app 내의 1개의 instance 를 사용합니다.
* */
@Singleton
public class DbHelper extends SQLiteOpenHelper {

    //USER TABLE
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "usr_name";
    public static final String USER_COLUMN_USER_ADDRESS = "usr_add";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";

    /*
    * @Inject at Constructor
    * 생성자의 파라미터들의 dependencies 를 dagger 가 모아서 주입 해줍니다.
    *
    * @ApplicationContext 라는 Qualifier 가 붙어있으면,
    * dagger2 에서는 Application Context 를 가져옵니다 (Activity Context 나 그 외 Context 가 아닌)
    *
    * 동일한 @DatabaseInfo 를 사용하더라도 현재 dagger 의 dependency graph 에서
    * int, String 을 구분해서 값을 넣어줍니다
    *
    * TODO: ApplicationModule 을 보면 provideDatabaseName, provideDatabaseVersion 와 같이
    * TODO: DatabaseName -> dbName, DatabaseVersion -> version 처럼 네이밍 룰이 지켜지지 않는데 괜찮나?
    *
    * */
    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreateStatements(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    private void tableCreateStatements(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + USER_TABLE_NAME
                            + " WHERE "
                            + USER_COLUMN_USER_ID
                            + " = ? ",
                    new String[]{userId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            } else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    protected Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
            return db.insert(USER_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
