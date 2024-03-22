package com.example.crudopreations;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class showBooks extends AppCompatActivity {
    ListView lv;
    ArrayList<String>  arr = new ArrayList<String>() ;
    ArrayAdapter<String> adp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        lv= findViewById(R.id.lv);
        database db = new database(this);
        Cursor cr = db.show_data();
        if(cr.getCount()>0){
            while(cr.moveToNext())
            {
                arr.add("\n ID:"+cr.getInt(0)+" Title:'"+cr.getString(1)+"',Author:'"+cr.getString(2)+"',Subject:'"+cr.getString(3)+"',Price"+cr.getInt(4));
            }
            adp = new ArrayAdapter<>(showBooks.this, android.R.layout.simple_dropdown_item_1line,arr);
            lv.setAdapter(adp);
        }
        else{
            Toast.makeText(showBooks.this, "No Records Found", Toast.LENGTH_SHORT).show();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu pop = new PopupMenu(showBooks.this,view);
                pop.getMenuInflater().inflate(R.menu.menu,pop.getMenu());
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if(id == R.id.delete){
                            String ans = (String)lv.getItemAtPosition(i);
                            String bookid[] = ans.split(":",2);
                            int bid = Integer.parseInt(bookid[1]);
                            int rows = db.delete_data(id);
                            if(rows>0){
                                arr.remove(i);
                                adp = new ArrayAdapter<>(showBooks.this, android.R.layout.simple_dropdown_item_1line,arr);
                                lv.setAdapter(adp);
                            }
                        }
                        return true;
                    }
                });
            }
        });
    }
}