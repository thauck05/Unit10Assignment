package edu.css.thauck.unit10assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is a helper class to open and create a database.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";     // table name
    public static final String COLUMN_ID = "_id";               // primary key column
    public static final String COLUMN_COMMENT = "comment";      // comment column name
    public static final String COLUMN_RATING = "rating";        // rating column name

    private static final String DATABASE_NAME = "commments.db"; // DB name
    private static final int DATABASE_VERSION = 2;              // DB version

    // Database creation sql statement
    /**
     * SQL table Create-----
     * CREATE TABLE comments(
     * COMMENT_ID string,
     * COMMENT string,
     * );
     */
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null, " + COLUMN_RATING + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates database. Executes query to create table.
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /**
     * Updates database. Drops existing DB and calls onCreate to create new DB.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}