package com.sohee.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class AddPlace extends AppCompatActivity {

    private DBHelper mydb;
    TextView name;
    TextView placename;
    TextView area;
    TextView classify;
    TextView content;
    TextView cost;
    Spinner spinner;
    Spinner spinner2;
    TextView areareal;
    TextView classifyreal;
    int id = 0;
    private static final int REQUEST_CODE = 0;
    private ImageView imageView;
    String[] items = {"지역", "서울특별시", "인천광역시","울산광역시","충청북도","전라남도"
            , "부산광역시", "광주광역시", "경기도", "충청남도", "경상북도", "경상남도", "대구광역시", "대전광역시", "강원도"
            , "전라북도", "제주특별자치도"};
    String[] classifys = {"분류", "카페", "식당", "공원", "놀이공원", "노래방"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        areareal = (TextView)findViewById(R.id.textView1);
        classifyreal = (TextView)findViewById(R.id.textView4);
        name = (TextView) findViewById(R.id.editTextLocation);
        placename = (TextView) findViewById(R.id.editTextName);
        area = (TextView)findViewById(R.id.TextArea);
        classify = (TextView)findViewById(R.id.TextClassify);
        content = (TextView) findViewById(R.id.editTextContent);
        cost = (TextView) findViewById(R.id.editTextCost);
        imageView = findViewById(R.id.image);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);


        mydb = new DBHelper(this);

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id = Value;
                rs.moveToFirst();
                String n = rs.getString(rs.getColumnIndex(DBHelper.NOTE_LOCATION));
                String p = rs.getString(rs.getColumnIndex(DBHelper.NOTE_NAME));
                String a = rs.getString(rs.getColumnIndex(DBHelper.NOTE_AREA));
                String con = rs.getString(rs.getColumnIndex(DBHelper.NOTE_CONTENT));
                String co = rs.getString(rs.getColumnIndex(DBHelper.NOTE_COST));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) n);
                placename.setText((CharSequence) p);
                area.setText((CharSequence)a);
                content.setText((CharSequence) con);
                cost.setText((CharSequence) co);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  //text에 spinner값 넣는 부분
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areareal.setText(items[position]);
                if(items[position] == "서울특별시") {
                    area.setText("서울특별시");
                } else if(items[position] == "인천광역시") {
                    area.setText("인천광역시");
                } else if(items[position] == "울산광역시") {
                    area.setText("울산광역시");
                } else if(items[position] == "충청북도") {
                    area.setText("충청북도");
                } else if(items[position] == "충청남도") {
                    area.setText("충청남도");
                } else if(items[position] == "전라북도") {
                    area.setText("전라북도");
                } else if(items[position] == "전라남도") {
                    area.setText("전라남도");
                } else if(items[position] == "광주광역시") {
                    area.setText("광주광역시");
                } else if(items[position] == "부산광역시") {
                    area.setText("부산광역시");
                } else if(items[position] == "경기도") {
                    area.setText("경기도");
                } else if(items[position] == "경상북도") {
                   area.setText("경상남도");
                } else if(items[position] == "대구광역시") {
                    area.setText("대구광역시");
                } else if(items[position] == "대전광역시") {
                    area.setText("대전광역시");
                } else if(items[position] == "강원도") {
                    area.setText("강원도");
                } else if(items[position] == "제주특별자치도") {
                    area.setText("제주특별자치도");
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                area.setText("");
            }
        });
    }



    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateNote(id, area.getText().toString(), name.getText().toString(), placename.getText().toString(), content.getText().toString(), cost.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), StoragePlace.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertNote(area.getText().toString(), name.getText().toString(), placename.getText().toString(), content.getText().toString(), cost.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                mydb.deleteNote(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void edit(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("id");
            if (value > 0) {
                if (mydb.updateNote(id, area.getText().toString(), name.getText().toString(), placename.getText().toString(), content.getText().toString(), cost.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}