package com.sohee.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StoragePlace2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_place2);

        TextView tx1 = (TextView)findViewById(R.id.textView1); /*TextView선언*/
        TextView tx2 = (TextView)findViewById(R.id.textView2);
        TextView tx3 = (TextView)findViewById(R.id.textView3);
        TextView tx4 = (TextView)findViewById(R.id.textView4);
        TextView tx5 = (TextView)findViewById(R.id.textView5);
        TextView tx6 = (TextView)findViewById(R.id.textView6);
        TextView tx7 = (TextView)findViewById(R.id.textView7);
        TextView tx8 = (TextView)findViewById(R.id.textView8);

        Intent intent = getIntent(); /*데이터 수신*/

        String name1 = intent.getExtras().getString("name1"); /*String형*/
        tx1.setText(name1);
        String name2 = intent.getExtras().getString("name2"); /*String형*/
        tx2.setText(name2);
        String name3 = intent.getExtras().getString("name3"); /*String형*/
        tx3.setText(name3);
        String name4 = intent.getExtras().getString("name4"); /*String형*/
        tx4.setText(name4);

        String age = intent.getExtras().getString("age"); /*int형*/
        tx5.setText(age);

        String array[] = intent.getExtras().getStringArray("array"); /*배열*/
        String add_array="";
        for(int i=0;i<array.length;i++){
            add_array+=array[i]+",";
        }
        tx6.setText(add_array);

        Option option = (Option)intent.getSerializableExtra("class"); /*클래스*/
        tx7.setText(option.getPhone());
        tx8.setText(option.getAddr());

    }
}
