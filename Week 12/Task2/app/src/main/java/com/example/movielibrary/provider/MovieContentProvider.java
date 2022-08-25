package com.example.movielibrary.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieContentProvider extends ContentProvider {
    MovieDatabase db;

    public static final String CONTENT_AUTHORITY = "fit2081.app.enming";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    @Override
    public boolean onCreate() {
        db = MovieDatabase.getDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // SQLiteQuery allows me to help manipulate my data
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Movie.tableName);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);

        // querying the object with the selection argument
        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        // access the database
        long rowId = db
                .getOpenHelper() // open library/ android
                .getWritableDatabase() // write/manipulate the items in the database
                .insert(Movie.tableName, 0, contentValues);

        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;

        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(Movie.tableName, selection, selectionArgs);

        return deletionCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(Movie.tableName, 0, values, selection, selectionArgs);

        return updateCount;
    }

}
