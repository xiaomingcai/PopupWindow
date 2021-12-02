package com.example.popupwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private View view;
private PopupWindow myPop;
private RelativeLayout rlMain;
private Button btnPopPicSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlMain=findViewById(R.id.rl_main);
        btnPopPicSelect = findViewById(R.id.btn_pop_pic_select);
        btnPopPicSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pop_pic_select:
                showPicSelect();
                break;
            case R.id.btn_pic_camera:
                Toast.makeText(this,"相机拍摄",Toast.LENGTH_LONG).show();
                myPop.dismiss();
                break;
            case R.id.btn_pic_photo:
                Toast.makeText(this,"相机选择",Toast.LENGTH_LONG).show();
                myPop.dismiss();
                break;
            case R.id.btn_pic_cancel:
                myPop.dismiss();
                break;
        }
    }

    private void showPicSelect() {
      view=LayoutInflater.from(this).inflate(R.layout.item_pic_select,null,false);
      LinearLayout llPop = (LinearLayout)view.findViewById(R.id.ll_pic);
      Button btnCamera = view.findViewById(R.id.btn_pic_camera);
      Button btnPhoto=view.findViewById(R.id.btn_pic_photo);
      Button btnCancel = view.findViewById(R.id.btn_pic_cancel);
      btnCamera.setOnClickListener(this);
      btnPhoto.setOnClickListener(this);
      btnCancel.setOnClickListener(this);
      llPop.setOnClickListener(this);
      myPop=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
      myPop.setBackgroundDrawable(new ColorDrawable());
      myPop.showAsDropDown(rlMain, Gravity.BOTTOM,0,0);
    }
}