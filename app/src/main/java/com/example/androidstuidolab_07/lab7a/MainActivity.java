package com.example.androidstuidolab_07.lab7a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidstuidolab_07.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button abtnAdd;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_7a);

        DatabaseHandle db = new DatabaseHandle(this);
        ListView listView = (ListView)findViewById(R.id.blistView);

        Log.d("read data","Reading database getStudents...");
        List<Student> students = db.getStudents();

        for (Student s : students) {
            String log = "id: "+s.getId() + ", name: "+s.getName();
            Log.d("Student is", log);
        }

        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        listView.setAdapter(arrayAdapter);

        editTextName = findViewById(R.id.editTextTextPersonName);
        abtnAdd = findViewById(R.id.abtnAdd);
        abtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Thêm thành công xuống SQLite", Toast.LENGTH_LONG).show();
                String studentName = editTextName.getText().toString();
                Log.d("insert data","Inserting data.....");
                db.addStudent(new Student(studentName));
                finish();
                startActivity(getIntent());
            }
        });
    }
}