package com.deviltech.roomdb_example.popupwindow;

import android.content.Context;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.deviltech.roomdb_example.R;
import com.deviltech.roomdb_example.interfaces.PopupWindowOnClickLisneters;
import com.deviltech.roomdb_example.persistancedb.entities.ToDoItem;

public class AddTaskPopupWindow {

    private static final String TAG = "addtaskpopupwindow";
    private static final String TEST = "TDK";

    public void showPopupWindow(final View view, PopupWindowOnClickLisneters popupWindowOnClickLisneters) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.todo_add_popwindow, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button addBtn = popupView.findViewById(R.id.add_btn);
        Button cancelBtn = popupView.findViewById(R.id.cancel_button);
        EditText taskDescription = popupView.findViewById(R.id.description_edittext);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable value = taskDescription.getText();
                if (!value.equals("")) {
                    ToDoItem data = new ToDoItem(value.toString(), false);
                    popupWindowOnClickLisneters.onClickAdd(v, data);
                    popupWindow.dismiss();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}