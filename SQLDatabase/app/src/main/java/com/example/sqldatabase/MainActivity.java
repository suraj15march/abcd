package com.example.sqldatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText name, contact, age;
    Button insert, update, delete, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        age = findViewById(R.id.age);

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String ageTXT = age.getText().toString();
                boolean qrystatus = DB.insertdata(nameTXT, contactTXT, ageTXT);
                if (qrystatus == true) {
                    Toast.makeText(MainActivity.this, "New Record Created", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(MainActivity.this, "New Record Creation failed", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String ageTXT = age.getText().toString();
                boolean qryStatus = DB.updateData(nameTXT, contactTXT, ageTXT);
                if (qryStatus == true)
                    Toast.makeText(MainActivity.this, "Record updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Record updation failed", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                boolean qryStatus = DB.deleteData(nameTXT);
                if (qryStatus == true)
                    Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Record Deletion failed", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.viewData();
                if (res.getCount() == 0)

                    Toast.makeText(MainActivity.this, "No record exist", Toast.LENGTH_SHORT).show();
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("name :" + res.getString(0) + "\n");
                        buffer.append("contact :" + res.getString(1) + "\n");
                        buffer.append("age :" + res.getString(2) + "\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Data");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
    }
}
