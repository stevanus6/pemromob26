package com.example.loginsql;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNamaLengkap, etTanggalLahir, etNomorTelepon, etUsername, etPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi EditText
        etNamaLengkap = findViewById(R.id.etNamaLengkap);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        etNomorTelepon = findViewById(R.id.etNomorTelepon);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        // Inisialisasi DBHelper
        dbHelper = new DBHelper(this);
    }

    // Fungsi untuk mendaftar
    public void register(View view) {
        String namaLengkap = etNamaLengkap.getText().toString();
        String tanggalLahir = etTanggalLahir.getText().toString();
        String nomorTelepon = etNomorTelepon.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        // Cek jika ada input yang kosong
        if (namaLengkap.isEmpty() || tanggalLahir.isEmpty() || nomorTelepon.isEmpty() ||
                username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Tolong isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        // Menambahkan data ke SQLite
        long result = dbHelper.addUser(namaLengkap, tanggalLahir, nomorTelepon, username, password);

        if (result == -1) {
            Toast.makeText(this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
            finish();  // Kembali ke halaman login setelah berhasil register
        }
    }
}

