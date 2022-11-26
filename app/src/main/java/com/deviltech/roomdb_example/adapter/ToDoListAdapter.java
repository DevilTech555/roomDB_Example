package com.deviltech.roomdb_example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deviltech.roomdb_example.R;
import com.deviltech.roomdb_example.persistancedb.entities.ToDoItem;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private static final String TAG = "todolist-adapter";
    private static final String TEST = "TDK";

    private Context context;
    private List<ToDoItem> itemList;

    public ToDoListAdapter(Context context, List<ToDoItem> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    public void updateList(List<ToDoItem> itemList){
        this.itemList.clear();
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.todolistitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.descriptionTextView.setText(itemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (itemList.isEmpty()) {
            return 0;
        } else {
            return itemList.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView descriptionTextView;

        public ViewHolder(@NonNull View view) {
            super(view);
            descriptionTextView = view.findViewById(R.id.to_do_description);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
