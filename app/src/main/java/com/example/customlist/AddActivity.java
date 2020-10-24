package com.example.customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private Button them;
    private EditText monhoc;

    public static final String SUBJECT = "SUBJECT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        them = (Button)findViewById(R.id.btthem);
        monhoc = (EditText)findViewById(R.id.edtmonhoc);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giatri = monhoc.getText().toString();
                byExtras(giatri);
            }
        });
    }
    public void byExtras(String subject){
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        intent.putExtra(SUBJECT,subject);
        startActivity(intent);
    }
}