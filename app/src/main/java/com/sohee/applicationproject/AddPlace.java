package com.sohee.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        Button sub = (Button)findViewById(R.id.sub);

        sub.setOnClickListener(new View.OnClickListener() {
            EditText T1 = (EditText)findViewById(R.id.aaa);
            EditText T2 = (EditText)findViewById(R.id.name);
            EditText T3 = (EditText)findViewById(R.id.read1);
            EditText T4 = (EditText)findViewById(R.id.cost1);


            @Override
            public void onClick(View v) {
                String[] array = {"홍길순","김춘추","김유신"}; /*송신 할 배열*/
                Option option = new Option("010xxxxxxxx","서울특별시xxxx"); /*송신 할 클래스*/

                Intent intent = new Intent(getApplicationContext(), StoragePlace.class);

                intent.putExtra("name1", T1.getText().toString());
                intent.putExtra("name2", T2.getText().toString());
                intent.putExtra("name3", T3.getText().toString());
                intent.putExtra("name4", T4.getText().toString());

                intent.putExtra("array",array);
                intent.putExtra("class",option);

                startActivity(intent);
            }
        });
    }
}