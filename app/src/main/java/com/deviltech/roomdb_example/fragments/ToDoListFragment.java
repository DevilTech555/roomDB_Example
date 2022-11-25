package com.deviltech.roomdb_example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deviltech.roomdb_example.R;
import com.deviltech.roomdb_example.persistancedb.database.AppDatabase;

public class ToDoListFragment extends Fragment {

    private static final String TAG = "TODOLIST";
    private static final String TEST = "TDK";
    public static final String METADATAKEY = "metadatakey";

    private View rootView;
    private RecyclerView to_do_list;
    private AppDatabase appDatabase;

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
        return rootView;
    }

    private void findViewByIds(){
        this.to_do_list = rootView.findViewById(R.id.to_do_recycler_view);
    }
}