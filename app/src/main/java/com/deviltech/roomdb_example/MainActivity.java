package com.deviltech.roomdb_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.deviltech.roomdb_example.fragments.ToDoListFragment;
import com.deviltech.roomdb_example.persistancedb.database.AppDatabase;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAINACTIVITY";
    private static final String TEST = "TDK";

    private TextView title;
    private ImageButton sideBar_btn;
    private AppDatabase appDatabase;
    private FragmentContainerView main_fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        setUpViews();
        setUpButtons();
        setUpDB();
        loadToDoList();
    }

    @SuppressLint("SetTextI18n")
    private void setUpViews(){
        this.title.setText("Today's Tasks");
    }

    private void setUpButtons(){
        this.sideBar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "Working");
            }
        });
    }

    private void findViewByIds(){
        this.title = findViewById(R.id.main_title);
        this.sideBar_btn = findViewById(R.id.side_menu_btn);
        this.main_fragment_container = findViewById(R.id.fragment_container_view);
    }

    private void setUpDB(){
        this.appDatabase = AppDatabase.getInstance(this);
    }


    private void loadFragment(int containerId, Fragment fragment, Object object, String key){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (null != object) {
            Bundle transactionData = new Bundle();
            transactionData.putParcelable(key, (Parcelable) object);
            fragment.setArguments(transactionData);
        }
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.commit();
    }

    private void loadToDoList(){
        loadFragment(R.id.fragment_container_view, new ToDoListFragment(), null, null);
    }
}