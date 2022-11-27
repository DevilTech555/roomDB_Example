package com.deviltech.roomdb_example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deviltech.roomdb_example.R;
import com.deviltech.roomdb_example.adapter.ToDoListAdapter;
import com.deviltech.roomdb_example.interfaces.PopupWindowOnClickLisneters;
import com.deviltech.roomdb_example.persistancedb.database.AppDatabase;
import com.deviltech.roomdb_example.persistancedb.entities.ToDoItem;
import com.deviltech.roomdb_example.popupwindow.AddTaskPopupWindow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ToDoListFragment extends Fragment implements ToDoListAdapter.ToDoListOnClickListener {

    private static final String TAG = "TODOLIST";
    private static final String TEST = "TDK";

    private View rootView;
    private RecyclerView to_do_list;
    private AppDatabase appDatabase;
    private FloatingActionButton addBtn;
    private ToDoListAdapter toDoListAdapter;

    public ToDoListFragment() {
    }

    public static ToDoListFragment newInstance() {
        return new ToDoListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.appDatabase = AppDatabase.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        findViewByIds();
        setOnClickListeners();
        setUpAdapter();
        return rootView;
    }

    private void findViewByIds() {
        this.to_do_list = rootView.findViewById(R.id.to_do_recycler_view);
        this.addBtn = rootView.findViewById(R.id.add_btn);
    }

    private void setOnClickListeners() {
        this.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskPopupWindow addTaskPopupWindow = new AddTaskPopupWindow();
                addTaskPopupWindow.showPopupWindow(v, new PopupWindowOnClickLisneters() {
                    @Override
                    public void onClickAdd(View v, Object data) {
                        ToDoItem toDoItem = (ToDoItem) data;
                        appDatabase.toDoItemDao().insert(toDoItem);
                        updateList();
                    }
                });
            }
        });
    }

    private void setUpAdapter() {
        List<ToDoItem> items = appDatabase.toDoItemDao().getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        toDoListAdapter = new ToDoListAdapter(items);
        toDoListAdapter.setItemClick(this);
        to_do_list.setLayoutManager(layoutManager);
        to_do_list.setAdapter(toDoListAdapter);
    }

    private void updateList(){
        List<ToDoItem> items = appDatabase.toDoItemDao().getAll();
        toDoListAdapter.updateList(items);
    }

    @Override
    public void onClickCheckBox(ToDoItem data) {
        appDatabase.toDoItemDao().update(data);
    }

    @Override
    public void onLongPress(ToDoItem data) {
        appDatabase.toDoItemDao().delete(data);
        updateList();
    }
}