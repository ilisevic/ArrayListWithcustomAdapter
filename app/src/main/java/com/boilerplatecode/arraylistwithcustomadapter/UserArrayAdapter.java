package com.boilerplatecode.arraylistwithcustomadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<User> {
    public UserArrayAdapter(Activity context, ArrayList<User> objects) {
        super(context, R.layout.user_layout ,objects);//ovo je bitno - pumpa user_layout
    }


    @Override
    public View getView(int position,   View convertView,  ViewGroup parent) {


        User_Holder user_holder = new User_Holder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.user_layout,parent,false);//user_layout predstavlja pojedinačnu stavku array liste
//kasnije se UserArrayAdapter koristi u MainActivity
//***********************************************
// kod iz Main klase ispod
//        ListView lv2 = findViewById(R.id.listView2);
//        user_adapter2 = new UserArrayAdapter(this, fromDB2ArrayList());
//        lv2.setAdapter(user_adapter2);
//*****************************************************



        user_holder.txtName = convertView.findViewById(R.id.tv_name);//povezuje textView holder klase
        user_holder.txtNote = convertView.findViewById(R.id.tv_note);//povezuje textView holder klase
        user_holder.txtRowid= convertView.findViewById(R.id.tv_rowid);
        User usr_tmp_data = getItem(position);


         String name = usr_tmp_data.getName();
         String note = usr_tmp_data.getNote();
         String rowid = usr_tmp_data.getRow_id();
         user_holder.txtName.setText("\n Ima korisnika je "+ name);
         user_holder.txtNote.setText("\n Zabilješka: "+note);
        user_holder.txtRowid.setText("\n RowId "+rowid);




        return convertView;
    }


    private  static class User_Holder
    {
        TextView txtName;
        TextView txtNote;
        TextView txtRowid;
    }
}
