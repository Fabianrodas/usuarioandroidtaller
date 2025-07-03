package com.example.usuariosapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nombre, email;
    Button guardar, verusuarios;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);
        guardar = findViewById(R.id.guardar);
        verusuarios = findViewById(R.id.verusuarios);
        dbHelper = new DBHelper(this);

        guardar.setOnClickListener(v -> {
            String nombretxt = nombre.getText().toString();
            String emailtxt = email.getText().toString();
            guardarUsuarios(nombretxt, emailtxt);
        });

        verusuarios.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListaUsuarios.class));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void guardarUsuarios(String nombretxt, String emailtxt) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombretxt);
        values.put("email", emailtxt);
        db.insert("usuarios", null, values);
        Toast.makeText(this, "Usuario guardado", Toast.LENGTH_LONG).show();
        nombre.setText("");
        email.setText("");
    }
}