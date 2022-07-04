package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;
    private View drawerView;

    Button btn1, btn2, btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  Tab 화면을 구성하는 DrawerLayout 구현 부분 시작
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);

        ImageButton btn_open = (ImageButton) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);


            }
        });

        Button btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment_Login fragmentLogin = new Fragment_Login();
        transaction.replace(R.id.frame, fragmentLogin);
        transaction.commit();


        btn1 = (Button) findViewById(R.id.btn_1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_Login fragmentFree = new Fragment_Login();
                transaction.replace(R.id.frame, fragmentFree);
                transaction.commit();
                drawerLayout.closeDrawers();
            }
        });

        btn2 = (Button) findViewById(R.id.btn_2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_Address fragmentAddress = new Fragment_Address();
                transaction.replace(R.id.frame, fragmentAddress);
                transaction.commit();
                drawerLayout.closeDrawers();
            }
        });

        btn3 = (Button) findViewById(R.id.btn_3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_Gallery fragmentGallery = new Fragment_Gallery();
                transaction.replace(R.id.frame, fragmentGallery);
                transaction.commit();
                drawerLayout.closeDrawers();

            }
        });

        //  Tab 화면을 구성하는 DrawerLayout 구현 부분 종료




    }

    //DrawerListener 구현파트
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    public void change_to_Detail(int i){
        CallBookClick callBookClick = new CallBookClick();
        callBookClick.jsonNum(i);
        getSupportFragmentManager().beginTransaction().replace(R.id.listFragment, callBookClick).commit();
    }
}