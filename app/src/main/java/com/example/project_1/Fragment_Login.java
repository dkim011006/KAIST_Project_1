package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Fragment_Login extends Fragment {
    Button btnsignin, btnlogin, btnsaveinfo, btnlogout;
    EditText inputid, inputpw;
    TextView label;
    Boolean userListExists;
    JSONObject jo = null;
    String username;

    public Fragment_Login(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //check if file exists
        System.out.println("GOES IN,,,,,,,");
        try {
            File path = getActivity().getApplicationContext().getFilesDir();
            File file1 = new File(path + "/userlist.json");
            if (file1.exists()) {
                if (file1.isDirectory()) {
                    System.out.println("file1: dir exits");
                } else {
                    System.out.println("file1: file exits");
                    userListExists = true;
                    jo = getJsonObjectFromFile("userlist.json");
                }
            } else {
                System.out.println("file1: does not exits");
                userListExists = false;
                jo = createNewJsonObject();
            }
        } catch (Exception e) {
            System.out.println("SERIOUS ERROR OCCURRED!!!!");
        }finally {
            System.out.println("FINALLLYU!!!!!");
        }

        btnsignin = view.findViewById(R.id.btnsignin);
        btnlogin = view.findViewById(R.id.btnlogin);
        btnsaveinfo = view.findViewById(R.id.saveinfo);
        btnlogout = view.findViewById(R.id.logout);
        inputid = view.findViewById(R.id.inputid);
        inputpw = view.findViewById(R.id.inputpw);
        label = view.findViewById(R.id.label);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sign In
                if(!validateUserId(inputid.getText().toString())){
                    if(inputid.getText().toString().equals("")){
                        label.setText("Enter Username");
                    }else if(inputpw.getText().toString().equals("")) {
                        label.setText("Enter Password");
                    }else{
                        //user register...
                        try {
                            System.out.println(inputid.getText().toString());
                            System.out.println(inputpw.getText().toString());
                            jo.put(inputid.getText().toString(), inputpw.getText().toString());
                            label.setText("Signed In!");
                            inputid.setText("");
                            inputpw.setText("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            label.setText(e.getMessage());
                        }
                    }

                }else{
                    label.setText("User Already Exists!");
                    inputid.setText("");
                    inputpw.setText("");
                }
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUserId(inputid.getText().toString())){
                    if(validateUserPw(inputid.getText().toString(), inputpw.getText().toString())){
                        //user login...
//                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
//                        intent.putExtra("username", inputid.getText().toString());
//                        startActivity(intent);
                        inputid.setVisibility(View.GONE);
                        inputpw.setVisibility(View.GONE);
                        btnsignin.setVisibility(View.GONE);
                        btnlogin.setVisibility(View.GONE);
                        label.setText("hello, " + inputid.getText().toString() + "!");
                        btnsaveinfo.setVisibility(View.VISIBLE);
                        btnlogout.setVisibility(View.VISIBLE);
                        //MainActivity(Set USERNAME, ADDRESSES, GALLERY)
                    }else{
                        label.setText("Wrong Password");
                        inputpw.setText("");
                    }

                }else{
                    label.setText("User Doesn't Exist");
                    inputid.setText("");
                    inputpw.setText("");
                }
            }
        });
        btnsaveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity(GET USERNAME, ADDRESSES, GALLERY and SAVE THEM)
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputid.setText("");
                inputpw.setText("");
                inputid.setVisibility(View.VISIBLE);
                inputpw.setVisibility(View.VISIBLE);
                btnsignin.setVisibility(View.VISIBLE);
                btnlogin.setVisibility(View.VISIBLE);
                label.setText("");
                btnsaveinfo.setVisibility(View.GONE);
                btnlogout.setVisibility(View.GONE);
                //MainActivity(Reset USERNAME, ADDRESSES, GALLERY)
            }
        });


        return view;
    }

    private boolean validateUserId(String inputid) {
        if (jo.has(inputid)) return true;
        return false;
    }

    private boolean validateUserPw(String inputid, String inputpw) {
        try {
//            if ((jo.getJSONObject(inputid)).getString("password") == inputpw) return true;
            System.out.println("ID is :" + jo.getString(inputid));
            System.out.println("PW is : " + inputpw);
            if (jo.getString(inputid).equals(inputpw)) return true;
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private JSONObject createNewJsonObject(){
        return new JSONObject();
    }

    private JSONObject getJsonObjectFromFile(String fileName){
        FileInputStream fileInputStream = null;
        JSONObject jo = null;
        try {
            fileInputStream = getActivity().openFileInput(fileName);
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read = fileInputStream.read())!=  -1){
                buffer.append((char)read);
            }
            Log.d("FILEREAD", buffer.toString());
            jo = new JSONObject(buffer.toString());
            System.out.println(jo);
            System.out.println("HERE WE GO!!!");
//            for(int i = 0;i<readja.length();i++){
//                System.out.println(readja.getJSONObject(i));
//            }
            System.out.println(jo);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }

    private ArrayList convertJsonArrayToArrayList(ArrayList arrayList, JSONArray jsonArray){
        if (jsonArray != null) {

            //Iterating JSON array
            for (int i=0;i<jsonArray.length();i++){

                //Adding each element of JSON array into ArrayList
                try {
                    arrayList.add(jsonArray.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        //Iterating ArrayList to print each element

        System.out.println("Each element of ArrayList");
        for(int i=0; i<arrayList.size(); i++) {
            //Printing each element of ArrayList
            System.out.println(arrayList.get(i));
        }
        return arrayList;
    }
}
