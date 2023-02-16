package com.example.studyapp.ui.todolist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    //오류발생 시 태그
    private static final String TAG = "MainActivity";

    Fragment mainFragment;
    EditText inputToDo;
    Context context;

    public static NoteDatabase noteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MainFragment 연결
        mainFragment = new com.example.todolist.MainFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        //버튼생성
        ImageButton saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveToDo();

                Toast.makeText(getApplicationContext(), "추가되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        openDatabase();

    }

    private void saveToDo() {
        inputToDo = findViewById(R.id.inputToDo);

        //EditText 에 적힌 글을 가져오기
        String todo = inputToDo.getText().toString();

        //테이블에 값을 추가하는 sql구문 insert...
        String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                "'" + todo + "')";
        //sql문 실행
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sqlSave);

        //저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");
    }

    public void openDatabase() {
        // open database
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }

}
