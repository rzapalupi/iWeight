package com.efpro.iweight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "foodInfo";
    // Contacts table name
    private static final String TABLE_FOODS = "Foods";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CAL = "calorie";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_FOODS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CAL + " INTEGER" + ")";
        db.execSQL(CREATE_FOODS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);
        // Creating tables again
        onCreate(db);
    }

    public void dropFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        String dropTabel= "DROP TABLE " + TABLE_FOODS;
        db.execSQL(dropTabel);
        onCreate(db);
    }

    // Adding new shop
    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName()); // Shop Name
        values.put(KEY_CAL, food.getCalorie()); // Shop Phone Number

        // Inserting Row
        db.insert(TABLE_FOODS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one food
    public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOODS, new String[]{KEY_ID,
                        KEY_NAME, KEY_CAL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Food kalori = new Food(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return kalori
        return kalori;
    }
    // Getting All Foods
    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<Food>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setCalorie(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }
    // Getting foods Count
    public int getFoodCount() {
        String countQuery = "SELECT * FROM " + TABLE_FOODS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating a food
    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_CAL, food.getCalorie());

        // updating row
        return db.update(TABLE_FOODS, values, KEY_ID + "=?",
                new String[]{String.valueOf(food.getId())});
    }

    // Deleting a shop
    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOODS, KEY_ID + "= ?",
                new String[] { String.valueOf(food.getId()) });
        db.close();
    }
}
