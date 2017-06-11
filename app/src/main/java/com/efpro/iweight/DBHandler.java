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
    private static final int DATABASE_VERSION = 9;
    // Database Name
    private static final String DATABASE_NAME = "foodInfo";
    // Foods and User table name
    private static final String TABLE_FOODS = "Foods";
    private static final String TABLE_USERS = "User";
    // Foods Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CAL = "calorie";

    // User Table Columns names
    //private static  final String KEY_IDU            = "id";
    private static  final String KEY_USERNAME       = "username";
    private static  final String KEY_PASSWORD       = "password";
    private static  final String KEY_NAMALENGKAP    = "namalengkap";
    private static  final String KEY_UMUR           = "umur";
    private static  final String KEY_GENDER         = "gender";
    private static  final String KEY_BB             = "beratbadan";
    private static  final String KEY_TB             = "tinggibadan";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_FOODS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CAL + " INTEGER" + ")";

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_NAMALENGKAP + " TEXT,"
                + KEY_UMUR + " INTEGER,"
                + KEY_GENDER + " TEXT,"
                + KEY_BB + " REAL,"
                + KEY_TB + " REAL" + ")";

        db.execSQL(CREATE_FOODS_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Creating tables again
        onCreate(db);
    }

    public void dropFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        String dropTabel= "DROP TABLE " + TABLE_FOODS;
        db.execSQL(dropTabel);
        onCreate(db);
    }

    public void dropUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        String dropTabel= "DROP TABLE " + TABLE_USERS;
        db.execSQL(dropTabel);
        onCreate(db);
    }

    public void ClearData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteTable = "DELETE FROM " + TABLE_FOODS;
        String deleteAdmin = "DELETE FROM " + TABLE_USERS + " WHERE username = 'admin' ";
        db.execSQL(deleteTable);
        db.execSQL(deleteAdmin);
    }

    // Adding new food
    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName()); // food Name
        values.put(KEY_CAL, food.getCalorie()); // food calorie

        // Inserting Row
        db.insert(TABLE_FOODS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new User
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NAMALENGKAP, user.getNamalengkap());
        values.put(KEY_UMUR, user.getUmur());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_BB, user.getBeratbadan());
        values.put(KEY_TB, user.getTinggibadan());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection

    }

    // Getting user data
    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_USERNAME,
                         KEY_NAMALENGKAP, KEY_UMUR, KEY_GENDER,
                        KEY_BB, KEY_TB }, KEY_USERNAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User data = new User(cursor.getString(0),cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),cursor.getString(3),
                Double.parseDouble(cursor.getString(4)),
                Double.parseDouble(cursor.getString(5)));
        // return data
        return data;
    }

    //check user
    public boolean checkUser(String username) {
        // array of columns to fetch
        String[] columns = {
                KEY_USERNAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = KEY_USERNAME + " = ?";
        // selection argument
        String[] selectionArgs = {username};
        // query user table with condition
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //check username and password
    public boolean checkUser(String username, String password) {
        // array of columns to fetch
        String[] columns = {
                KEY_USERNAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = KEY_USERNAME + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
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
                // Adding food to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return food list
        return foodList;
    }

}
