package com.sohee.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.addPlace);
        Button button2 = (Button)findViewById(R.id.find);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View target) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", 0);
                    Intent intent = new Intent(getApplicationContext(), AddPlace.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StoragePlace.class);
                startActivity(intent);
            }
        });

    }
}
