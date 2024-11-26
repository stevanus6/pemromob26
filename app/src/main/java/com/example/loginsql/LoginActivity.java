package com.example.loginsql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi EditText
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        // Inisialisasi DBHelper
        dbHelper = new DBHelper(this);
    }

    // Fungsi untuk login
    public void login(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        // Cek jika input kosong
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Tolong isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cek apakah username dan password cocok
        Cursor cursor = dbHelper.getUserByUsernameAndPassword(username, password);

        if (cursor != null && cursor.moveToFirst()) {
            // Jika login berhasil
            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();
            cursor.close();
            // Pindah ke halaman utama setelah login
        } else {
            // Jika login gagal
            Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
        }
    }

    // Fungsi untuk membuka halaman registrasi
    public void goToRegister(View view) {
        // Pindah ke halaman registrasi
        startActivity(new Intent(this, RegisterActivity.class));
    }

    // Fungsi untuk membuka halaman forgot password
    public void goToForgotPassword(View view) {
        // Pindah ke halaman forgot password
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}

