package com.boilerplatecode.arraylistwithcustomadapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> users;
    private ArrayAdapter<User> user_adapter, user_adapter2;
    DBHelperImportovan db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<User>();
        users.add(new User("0", "rucno unijet u arraylist 1", "note"));
        users.add(new User("0", "rucno unijet u arraylist 1", "note"));
        users.add(new User("0", "rucno unijet u arraylist 1", "note"));
        users.add(new User("0", "rucno unijet u arraylist 1", "note"));
        users.add(new User("0", "rucno unijet u arraylist 1", "note"));
        users.add(new User("0", "rucno unijet u arraylist 1", "note"));


//        user_adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);

        user_adapter = new UserArrayAdapter(this, users);


        ListView lv = findViewById(R.id.listView);

        lv.setAdapter(user_adapter);

        ///OnItemClickLister<
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Kliknuta stavka", Toast.LENGTH_LONG).show();
            }
        });
        ///OnItemClickLister>
        ///TODO ispod dodavanje podataka iz baze u bazu itd

        db = new DBHelperImportovan(this);
        db.addData("ime1", "bilješka1");
        db.addData("ime2", "bilješka1");
        db.addData("ime3", "bilješka1");
        db.addData("ime4", "bilješka1");
//dodavanje iz pomoćne procedure MainActivity klase
//        addData("iz baze 1", "bilješka4");
//        addData("iz baze 2", "bilješka5");
//        addData("iz baze 3", "bilješka6");
//        addData("iz baze 4", "bilješka7");


        ListView lv2 = findViewById(R.id.listView2);
        user_adapter2 = new UserArrayAdapter(this, fromDB2ArrayList());
        lv2.setAdapter(user_adapter2);
//TODO kod iznad konačno vezuje listView sa user_layout
    }



    private ArrayList<User> fromDB2ArrayList()///ovo puni arrayList iz SQLite baze
    {
        Cursor data = db.getData();
        ArrayList<User> listDataUser = new ArrayList<>();
        while (data.moveToNext()) {
            listDataUser.add(new User(data.getString(0), data.getString(1), data.getString(2)));
        }
        return listDataUser;
    }
}
