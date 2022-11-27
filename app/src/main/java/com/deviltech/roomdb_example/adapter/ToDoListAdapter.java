package com.deviltech.roomdb_example.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deviltech.roomdb_example.R;
import com.deviltech.roomdb_example.persistancedb.entities.ToDoItem;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private static final String TAG = "todolist-adapter";
    private static final String TEST = "TDK";

    private List<ToDoItem> itemList;
    private ToDoListOnClickListener toDoListOnClickListener;

    public ToDoListAdapter(List<ToDoItem> itemList) {
        this.itemList = itemList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<ToDoItem> itemList) {
        this.itemList.clear();
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.todolistitem, parent, false);
        return new ViewHolder(view, this.toDoListOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.toDoItem = itemList.get(position);
        holder.descriptionTextView.setText(itemList.get(position).getDescription());
        holder.isCompleted.setChecked(itemList.get(position).getCompleted());
    }

    @Override
    public int getItemCount() {
        if (itemList.isEmpty()) {
            return 0;
        } else {
            return itemList.size();
        }
    }

    public interface ToDoListOnClickListener {
        void onClickCheckBox(ToDoItem data);
        void onLongPress(ToDoItem data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView descriptionTextView;
        private final CheckBox isCompleted;
        private ToDoItem toDoItem;
        private ToDoListOnClickListener toDoListOnClickListener;

        public ViewHolder(@NonNull View view, ToDoListOnClickListener toDoListOnClickListener) {
            super(view);
            this.descriptionTextView = view.findViewById(R.id.to_do_description);
            this.isCompleted = view.findViewById(R.id.to_do_checkBox);
            this.toDoListOnClickListener = toDoListOnClickListener;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            boolean value = !this.isCompleted.isChecked();
            this.isCompleted.setChecked(value);
            this.toDoItem.setCompleted(value);
            this.toDoListOnClickListener.onClickCheckBox(toDoItem);
        }

        @Override
        public boolean onLongClick(View view) {
            AlertDialog.Builder alertMessage = new AlertDialog.Builder(view.getContext());
            alertMessage.setMessage("Are you sure do you want to delete it.....!");
            alertMessage.setCancelable(true);
            alertMessage.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            toDoListOnClickListener.onLongPress(toDoItem);
                            dialog.cancel();
                        }
                    });

            alertMessage.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = alertMessage.create();
            alert.show();
            return false;
        }
    }

    public ToDoListOnClickListener getItemClick() {
        return toDoListOnClickListener;
    }

    public void setItemClick(ToDoListOnClickListener toDoListOnClickListener) {
        this.toDoListOnClickListener = toDoListOnClickListener;
    }
}
