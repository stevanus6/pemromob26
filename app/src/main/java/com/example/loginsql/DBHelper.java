package com.example.loginsql;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_database"; // Nama database
    private static final int DB_VERSION = 1; // Versi database

    // Nama tabel dan kolom
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_LENGKAP = "nama_lengkap";
    public static final String COLUMN_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String COLUMN_NOMOR_TELEPON = "nomor_telepon";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL untuk membuat tabel user
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA_LENGKAP + " TEXT, " +
                COLUMN_TANGGAL_LAHIR + " TEXT, " +
                COLUMN_NOMOR_TELEPON + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tabel jika ada perubahan versi database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    // Menambahkan user baru
    public long addUser(String namaLengkap, String tanggalLahir, String nomorTelepon, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA_LENGKAP, namaLengkap);
        values.put(COLUMN_TANGGAL_LAHIR, tanggalLahir);
        values.put(COLUMN_NOMOR_TELEPON, nomorTelepon);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        return db.insert(TABLE_USER, null, values);
    }

    // Mengambil user berdasarkan username dan password
    public Cursor getUserByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USER, null,
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);
    }
}
