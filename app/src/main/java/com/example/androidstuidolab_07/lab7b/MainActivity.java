package com.example.androidstuidolab_07.lab7b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidstuidolab_07.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandle databaseHandle;
    List<Place> places;
    Button btnSave, btnCancel;
    EditText editTextPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_7b);

        databaseHandle = new DatabaseHandle(this);
        List<Place> places = databaseHandle.getPlaces();
        ListView listView = (ListView) findViewById(R.id.blistView);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.activity_item_7b, places);
        listView.setAdapter(adapter);
        btnSave = (Button) findViewById(R.id.bbtnSave);
        btnCancel = (Button) findViewById(R.id.bbtnCancel);
        editTextPlace = (EditText) findViewById(R.id.editTextPlace);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Đã save xuống SQLite.", Toast.LENGTH_LONG).show();
                databaseHandle.addPlace(new Place(editTextPlace.getText().toString()));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Xáo rỗng", Toast.LENGTH_LONG).show();
                editTextPlace.setText("");
            }
        });
    }
}