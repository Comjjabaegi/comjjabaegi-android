package com.example.studyapp;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

private ActionBar actionBar;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        // 툴바 활성화
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 햄버거 버튼 이미지 불러오기
        actionBar.setHomeAsUpIndicator(R.drawable.ksu_logo);
        // 툴바에 적힐 제목
        actionBar.setTitle("Subscriber");
        actionBar.setHomeButtonEnabled(true);
    }
}

