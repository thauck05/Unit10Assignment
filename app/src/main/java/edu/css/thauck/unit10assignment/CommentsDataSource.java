package edu.css.thauck.unit10assignment;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    /**
     * Constructor to instantiate the database helper
     * @param context
     */
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /**
     * Opens SQLite database connection
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Closes SQLite database connection
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Saves new comment to the table and creates a record.
     * Will also return the comment.
     * @param comment
     * @return
     */
    public Comment createComment(String comment) {
        // store new value in comment object
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        // insert new value into table to create record
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    /**
     * Delete first comment listed from database table
     * @param comment
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * Get a list of all comments in the database
     * @return
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();
        // query the database for all fields in all records in table
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);
        // loop through the cursor converting each row into comment object
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    /**
     * Convert the current record the cursor points to into an object
     * @param cursor
     * @return
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        comment.setComment(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_COMMENT)));
        return comment;
    }
}