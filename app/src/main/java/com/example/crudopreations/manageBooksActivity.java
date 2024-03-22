package com.example.crudopreations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class manageBooksActivity extends AppCompatActivity {
    EditText title,auth,price;
    Spinner subject;
    Button btn;
    String til,aut,sub="";
    int prc =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);
        title=findViewById(R.id.btitle);
        auth=findViewById(R.id.author);
        price=findViewById(R.id.price);
        btn = findViewById(R.id.submit);
        database db = new database(this);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                til = title.getText().toString();
                aut = auth.getText().toString();
                sub = subject.getSelectedItem().toString();
                prc = Integer.parseInt(price.getText().toString());
                long i = db.insert_data(til,aut,sub,prc);
                Toast.makeText(manageBooksActivity.this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(manageBooksActivity.this,showBooks.class);
                startActivity(in);

            }
        });


    }
}