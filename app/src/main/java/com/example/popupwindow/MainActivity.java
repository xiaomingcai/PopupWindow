package com.example.popupwindow;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zmz
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View view;
    private PopupWindow myPop;
    private RelativeLayout rlMain;
    private CardView cvMain;
    private ImageView imageView;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlMain = findViewById(R.id.rl_main);
        Button btnPopPicSelect = findViewById(R.id.btn_pop_pic_select);
        Button btnPopQq = findViewById(R.id.btn_pop_qq);
        Button btnPagerCenter = findViewById(R.id.btn_pager_center);
        imageView = findViewById(R.id.iv);
        cvMain = findViewById(R.id.cv_app);
        btnPopPicSelect.setOnClickListener(this);
        btnPopQq.setOnClickListener(this);
        btnPagerCenter.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pop_pic_select:
                showPicSelect();
                break;
            case R.id.btn_pop_qq:
                showQq();
                break;
            case R.id.btn_pager_center:
                showPager();
                break;
            case R.id.btn_pic_camera:
                Toast.makeText(this, "相机拍摄", Toast.LENGTH_LONG).show();
                myPop.dismiss();
                break;
            case R.id.btn_pic_photo:
                Toast.makeText(this, "相机选择", Toast.LENGTH_LONG).show();
                myPop.dismiss();
                break;
            case R.id.btn_pic_cancel:
                myPop.dismiss();
                break;
            case R.id.tv_be_top:
                Toast.makeText(this, "置顶成功", Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.VISIBLE);
                myPop.dismiss();
                break;
            case R.id.tv_delete:
                Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show();
                imageView.setVisibility(View.GONE);
                myPop.dismiss();
                break;
            default:
                break;
        }
    }

    @SuppressLint("InflateParams")
    private void showPager() {
        views = new ArrayList<>();
        view = LayoutInflater.from(this).inflate(R.layout.item_pager, null, false);
        ViewPager vpPop = view.findViewById(R.id.vp_pop);
        View picView01 = LayoutInflater.from(this).inflate(R.layout.item_pop_vp_01, null, false);
        View picView02 = LayoutInflater.from(this).inflate(R.layout.item_pop_vp_02, null, false);
        View picView03 = LayoutInflater.from(this).inflate(R.layout.item_pop_vp_03, null, false);
        View picView04 = LayoutInflater.from(this).inflate(R.layout.item_pop_vp_04, null, false);
        views.add(picView01);
        views.add(picView02);
        views.add(picView03);
        views.add(picView04);
        vpPop.setAdapter(new MyPopAdapter());
        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setOutsideTouchable(true);
        myPop.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        //设置悬浮阴影但是不明显和没有一样
        myPop.setElevation(200.0f);

        myPop.showAtLocation(rlMain, Gravity.CENTER, 0, 0);
    }

    @SuppressLint("InflateParams")
    private void showQq() {
        view = LayoutInflater.from(this).inflate(R.layout.item_qq, null, false);
        TextView tvTop = view.findViewById(R.id.tv_be_top);
        TextView tvDelete = view.findViewById(R.id.tv_delete);
        tvDelete.setOnClickListener(this);
        tvTop.setOnClickListener(this);
        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.setOutsideTouchable(true);
        myPop.getContentView().measure(0, 0);
        myPop.showAsDropDown(cvMain, cvMain.getWidth() - myPop.getContentView().getMeasuredWidth() / 2,
                -(cvMain.getWidth() + myPop.getContentView().getMeasuredHeight()));
    }

    @SuppressLint("InflateParams")
    private void showPicSelect() {
        view = LayoutInflater.from(this).inflate(R.layout.item_pic_select, null, false);
        LinearLayout llPop =  view.findViewById(R.id.ll_pic);
        Button btnCamera = view.findViewById(R.id.btn_pic_camera);
        Button btnPhoto = view.findViewById(R.id.btn_pic_photo);
        Button btnCancel = view.findViewById(R.id.btn_pic_cancel);
        btnCamera.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        llPop.setOnClickListener(this);
        myPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.showAsDropDown(rlMain, Gravity.BOTTOM, 0, 0);
    }

    class MyPopAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            Log.d("PopUpWindow", "instantiateItem position=" + position);
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            Log.d("PopUpWindow", "destroyItem position=" + position);
            container.removeView(views.get(position));
        }
    }
}