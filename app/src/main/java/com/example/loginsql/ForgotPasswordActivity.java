package com.example.loginsql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etUsername, etNewPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Inisialisasi EditText
        etUsername = findViewById(R.id.etUsername);
        etNewPassword = findViewById(R.id.etNewPassword);

        // Inisialisasi DBHelper
        dbHelper = new DBHelper(this);
    }

    // Fungsi untuk reset password
    public void resetPassword(View view) {
        String username = etUsername.getText().toString();
        String newPassword = etNewPassword.getText().toString();

        if (username.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "Tolong isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_USER, null,
                DBHelper.COLUMN_USERNAME + "=?",
                new String[]{username}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Update password jika username ditemukan
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_PASSWORD, newPassword);
            db.update(DBHelper.TABLE_USER, values,
                    DBHelper.COLUMN_USERNAME + "=?", new String[]{username});
            Toast.makeText(this, "Password berhasil direset", Toast.LENGTH_SHORT).show();
            cursor.close();
        } else {
            // Jika username tidak ditemukan
            Toast.makeText(this, "Username tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}
