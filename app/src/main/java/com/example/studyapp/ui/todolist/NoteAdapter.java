package com.example.studyapp.ui.todolist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private static final String TAG = "NoteAdapter";

    ArrayList<com.example.todolist.Note> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.example.todolist.Note item = items.get(position);
        holder.setItem(item);
        holder.setLayout();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layoutTodo;
        CheckBox checkBox;
        Button deleteButton;


        public ViewHolder(View itemView) {
            super(itemView);

            layoutTodo = itemView.findViewById(R.id.layoutTodo);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.setPaintFlags(checkBox.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

                }
            });

            deleteButton = itemView.findViewById(R.id.deleteBtn);

            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String TODO = (String) checkBox.getText();
                    deleteTodo(TODO);
                    Toast.makeText(v.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }

                Context context;

                private void deleteTodo(String TODO) {
                    String deleteSql = "delete from " + com.example.todolist.NoteDatabase.TABLE_NOTE + " where " + "  TODO = '" + TODO+"'";
                    com.example.todolist.NoteDatabase database = com.example.todolist.NoteDatabase.getInstance(context);
                    database.execSQL(deleteSql);
                }
            });
        }

        public void setItem(com.example.todolist.Note item){checkBox.setText(item.getTodo());}

        public void setLayout(){layoutTodo.setVisibility(View.VISIBLE);}

    }
    public void setItems(ArrayList<com.example.todolist.Note> items){this.items = items;}

}
