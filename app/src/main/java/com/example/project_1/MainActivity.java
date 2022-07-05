package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;
    private View drawerView;

    Button btn1, btn2, btn3;

    Boolean isLogin = false;
    JSONObject jo = null;
    String username = "";
    String password = "";
    ArrayList<AddressData> addressList = null;
    ArrayList<GalleryData> galleryList = null;


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
//        getSupportFragmentManager().popBackStackImmediate();
//        transaction.add(fragmentLogin, "home_login");
        transaction.replace(R.id.frame, fragmentLogin);
        transaction.commit();


        btn1 = (Button) findViewById(R.id.btn_1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_Login fragmentFree = new Fragment_Login();
//                getSupportFragmentManager().popBackStackImmediate();
                transaction.replace(R.id.frame, fragmentFree);
//                transaction.add(fragmentFree, "fragmentFree");
                transaction.commit();
                drawerLayout.closeDrawers();
            }
        });

        btn2 = (Button) findViewById(R.id.btn_2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLogin){
                    Toast.makeText(MainActivity.this, "Login First", Toast.LENGTH_SHORT).show();
                    return;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_Address fragmentAddress = new Fragment_Address();
//                getSupportFragmentManager().popBackStackImmediate();
                transaction.replace(R.id.frame, fragmentAddress);
//                transaction.add(fragmentAddress, "fragmentAddress");
                transaction.commit();
                drawerLayout.closeDrawers();
            }
        });

        btn3 = (Button) findViewById(R.id.btn_3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLogin){
                    Toast.makeText(MainActivity.this, "Login First", Toast.LENGTH_SHORT).show();
                    return;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_Gallery fragmentGallery = new Fragment_Gallery();
//                getSupportFragmentManager().popBackStackImmediate();
                transaction.replace(R.id.frame, fragmentGallery);
//                transaction.add(fragmentGallery, "fragmentGallery");
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

    public void moveToDetailPage(String name, String num, String explain, String age){
        CallBookClick callBookClick = new CallBookClick();
        callBookClick.jsonNum(name, num, explain, age);
//        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().beginTransaction().replace(R.id.listFragment, callBookClick).commit();
//        getSupportFragmentManager().beginTransaction().add(callBookClick, "callBookClick").commit();
//        getSupportFragmentManager().beginTransaction().remove(R.id.listFragment);
    }

    public void moveToAddPage(){
        Fragment_Address_Add fragment_address_add = new Fragment_Address_Add();
//        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().beginTransaction().replace(R.id.listFragment, fragment_address_add).commit();
//        getSupportFragmentManager().beginTransaction().add(fragment_address_add, "fragment_address_add").commit();
    }

    public void moveBackToAddressPage(){
        Fragment_Address fragment_address = new Fragment_Address();
//        getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().beginTransaction().replace(R.id.listFragment, fragment_address).commit();
//        getSupportFragmentManager().beginTransaction().add(fragment_address, "fragment_address").commit();
//        Fragment fragment = getFragmentManager().findFragmentById(R.id.listFragment);
//        if(fragment instanceof MyFragment) {
//            // todo
//        }
    }

    public void updateUserInfo(Boolean isLogin, JSONObject jo, String username, String password, ArrayList addressList, ArrayList galleryList){
        this.isLogin = isLogin;
        this.jo = jo;
        this.username = username;
        this.password = password;
        this.addressList = addressList;
        this.galleryList = galleryList;
        System.out.println("The User Info are as below \n:\nisLogin = " + this.isLogin);
        System.out.println("jo = ");
        System.out.println(jo);
        System.out.println("username = ");
        System.out.println(username);
        System.out.println("addressList = ");
        System.out.println(addressList);
        System.out.println("galleryList = ");
        System.out.println(galleryList);
    }

    public Boolean getIslogin(){
        return this.isLogin;
    }

    public JSONObject getJo(){
        return this.jo;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public ArrayList getUserAddressList(){
        return this.addressList;
    }

    public ArrayList getUserGalleryList(){
        return this.galleryList;
    }
}