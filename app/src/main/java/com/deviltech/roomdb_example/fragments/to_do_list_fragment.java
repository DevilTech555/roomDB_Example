package com.deviltech.roomdb_example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deviltech.roomdb_example.R;

public class to_do_list_fragment extends Fragment {

    private static final String TAG = "TODOLIST";
    private static final String TEST = "TDK";

    private View rootView;
    private RecyclerView to_do_list;

    public to_do_list_fragment() {
    }

    public static to_do_list_fragment newInstance() {
        return new to_do_list_fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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