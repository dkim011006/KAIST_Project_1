package com.example.project_1;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Fragment_Login extends Fragment {
    TextView btnsignin, btnlogin, btnsaveinfo, btnlogout;
    EditText inputid, inputpw;
    TextView label;
    Boolean userListExists;
    JSONObject jo = null;

    public Fragment_Login(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();


        btnsignin = view.findViewById(R.id.btnsignin);
        btnlogin = view.findViewById(R.id.btnlogin);
        btnsaveinfo = view.findViewById(R.id.saveinfo);
        btnlogout = view.findViewById(R.id.logout);
        inputid = view.findViewById(R.id.inputid);
        inputpw = view.findViewById(R.id.inputpw);
        label = view.findViewById(R.id.label);

        //check if already login
        if(mainActivity.getIslogin()){
            jo = mainActivity.getJo();
            inputid.setVisibility(View.GONE);
            inputpw.setVisibility(View.GONE);
            btnsignin.setVisibility(View.GONE);
            btnlogin.setVisibility(View.GONE);
            label.setTextColor(Color.parseColor("#FF9575CD"));
            label.setTextSize(2, 50);
            label.setText("Hello, " + mainActivity.getUsername() + "!");
            btnsaveinfo.setVisibility(View.VISIBLE);
            btnlogout.setVisibility(View.VISIBLE);
        }else{
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


        }
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sign In
                if(!validateUserId(inputid.getText().toString())){
                    if(inputid.getText().toString().equals("")){
                        label.setTextColor(Color.RED);
                        label.setTextSize(2, 23);
                        label.setText("Enter Username");
                    }else if(inputpw.getText().toString().equals("")) {
                        label.setTextColor(Color.RED);
                        label.setTextSize(2, 23);
                        label.setText("Enter Password");
                    }else{
                        //user register...
                        try {
                            System.out.println(inputid.getText().toString());
                            System.out.println(inputpw.getText().toString());
                            //Default Address Hard Coding
                            JSONObject joUserDefaultAddress = new JSONObject();
                            joUserDefaultAddress.put("name", "Bruno Fernandes");
                            joUserDefaultAddress.put("age", "27");
                            joUserDefaultAddress.put("number", "01048674395");
                            joUserDefaultAddress.put("explain", "World's Best Player");

                            JSONArray jaUserAddress = new JSONArray();
                            jaUserAddress.put(joUserDefaultAddress);

                            JSONArray jaUserGallery = new JSONArray();

                            JSONObject joUserInfo = new JSONObject();

                            joUserInfo.put("password", inputpw.getText().toString());
                            joUserInfo.put("address", jaUserAddress);
                            joUserInfo.put("gallery", jaUserGallery);

                            jo.put(inputid.getText().toString(), joUserInfo);

                            System.out.println(jo);

                            label.setTextColor(Color.RED);
                            label.setTextSize(2, 23);
                            label.setText("Signed In!");
                            inputid.setText("");
                            inputpw.setText("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            label.setTextColor(Color.RED);
                            label.setTextSize(2, 23);
                            label.setText(e.getMessage());
                        }
                    }

                }else{
                    label.setTextColor(Color.RED);
                    label.setTextSize(2, 23);
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
                        String id = inputid.getText().toString();
                        String pw = inputpw.getText().toString();
                        //user login...
//                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
//                        intent.putExtra("username", inputid.getText().toString());
//                        startActivity(intent);
                        inputid.setVisibility(View.GONE);
                        inputpw.setVisibility(View.GONE);
                        btnsignin.setVisibility(View.GONE);
                        btnlogin.setVisibility(View.GONE);
                        label.setTextColor(Color.parseColor("#FF9575CD"));
                        label.setTextSize(2, 50);
                        label.setText("Hello, " + id + "!");
                        btnsaveinfo.setVisibility(View.VISIBLE);
                        btnlogout.setVisibility(View.VISIBLE);
                        //MainActivity(Set USERNAME, ADDRESSES, GALLERY)
                        try {
                            JSONArray userAddressJsonList = jo.getJSONObject(id).getJSONArray("address");
                            JSONArray userGalleryJsonList = jo.getJSONObject(id).getJSONArray("gallery");
                            ArrayList<AddressData> userAddressArrayList = new ArrayList<>();
                            ArrayList<GalleryData> userGalleryArrayList = new ArrayList<>();
                            userAddressArrayList = convertJsonArrayToArrayList_AddressData(userAddressArrayList, userAddressJsonList);
                            userGalleryArrayList = convertJsonArrayToArrayList_GalleryData(userGalleryArrayList, userGalleryJsonList);
                            mainActivity.updateUserInfo(true, jo, id, pw, userAddressArrayList, userGalleryArrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        label.setTextColor(Color.RED);
                        label.setTextSize(2, 23);
                        label.setText("Wrong Password");
                        inputpw.setText("");
                    }

                }else{
                    label.setTextColor(Color.RED);
                    label.setTextSize(2, 23);
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
                String username = mainActivity.getUsername();
                String password = mainActivity.getPassword();
                System.out.println("Password by getPassword() = " + password);
                ArrayList<AddressData> userAddressArrayList_forsave = mainActivity.getUserAddressList();
                ArrayList<GalleryData> userGalleryArrayList_forsave = mainActivity.getUserGalleryList();
                JSONArray userAddressJsonList_forsave = new JSONArray();
                JSONArray userGalleryJsonList_forsave = new JSONArray();
                userAddressJsonList_forsave = convertArrayListToJsonArray_AddressData(userAddressArrayList_forsave, userAddressJsonList_forsave);
                userGalleryJsonList_forsave = convertArrayListToJsonArray_GalleryData(userGalleryArrayList_forsave, userGalleryJsonList_forsave);
                JSONObject joForUserInfoSave = new JSONObject();
                try {
                    joForUserInfoSave.put("password", password);
                    joForUserInfoSave.put("address", userAddressJsonList_forsave);
                    joForUserInfoSave.put("gallery", userGalleryJsonList_forsave);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(joForUserInfoSave);
                try {
                    jo.put(username, joForUserInfoSave);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("The Final Jo... is!");
                    System.out.println(jo);
                    writeJsonFile("userlist.json", jo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                mainActivity.updateUserInfo(false, jo,null, null, null, null);
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
            if ((jo.getJSONObject(inputid).getString("password")).equals(inputpw)) return true;
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private JSONObject createNewJsonObject(){
        JSONObject jo = new JSONObject();
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

    private ArrayList convertJsonArrayToArrayList_AddressData(ArrayList arrayList, JSONArray jsonArray){
        if (jsonArray != null) {

            //Iterating JSON array
            for (int i=0;i<jsonArray.length();i++){

                //Adding each element of JSON array into ArrayList
                try {
                    JSONObject userData = jsonArray.getJSONObject(i);
                    AddressData addressData = new AddressData(R.drawable.ic_baseline_person_24, userData.getString("number"), userData.getString("name"), userData.getString("explain"), userData.getString("age"));
                    arrayList.add(addressData);
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

    private ArrayList convertJsonArrayToArrayList_GalleryData(ArrayList<GalleryData> arrayList, JSONArray jsonArray){
        if (jsonArray != null) {

            //Iterating JSON array
            for (int i=0;i<jsonArray.length();i++){

                //Adding each element of JSON array into ArrayList
                try {
                    System.out.println("JSON URI DATA = " + jsonArray.getString(i));
                    Uri imageUri = Uri.parse(jsonArray.getString(i));  // 선택한 이미지들의 uri를 가져온다.
                    System.out.println("PARSED URI = " + imageUri);
                    GalleryData galleryData = new GalleryData(imageUri);
                    arrayList.add(galleryData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        //Iterating ArrayList to print each element

        System.out.println("Each element of ArrayList");
        for(int i=0; i<arrayList.size(); i++) {
            //Printing each element of ArrayList
            GalleryData gData = arrayList.get(i);
            System.out.println(gData.getIv_photo());
        }
        return arrayList;
    }

    private JSONArray convertArrayListToJsonArray_AddressData(ArrayList<AddressData> arrayList, JSONArray jsonArray) {
        if (arrayList != null) {

            //Iterating JSON array
            for (int i=0;i<arrayList.size();i++){

                //Adding each element of JSON array into ArrayList
                try {
                    AddressData addressData = arrayList.get(i);
                    String name = addressData.getTitle();
                    String number = addressData.getContent();
                    String explain = addressData.getExplain();
                    String age = addressData.getAge();
                    JSONObject jo_forsaveAddress = new JSONObject();
                    jo_forsaveAddress.put("name", name);
                    jo_forsaveAddress.put("number", number);
                    jo_forsaveAddress.put("explain", explain);
                    jo_forsaveAddress.put("age", age);
                    jsonArray.put(jo_forsaveAddress);


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
        return jsonArray;
    }

    private JSONArray convertArrayListToJsonArray_GalleryData(ArrayList<GalleryData> arrayList, JSONArray jsonArray) {
        if (arrayList != null) {

            //Iterating JSON array
            for (int i=0;i<arrayList.size();i++){

                //Adding each element of JSON array into ArrayList
                GalleryData galleryData = arrayList.get(i);
                String uriToString = galleryData.getIv_photo().toString();
                jsonArray.put(uriToString);


            }
        }
        //Iterating ArrayList to print each element

        System.out.println("Each element of ArrayList");
        for(int i=0; i<arrayList.size(); i++) {
            //Printing each element of ArrayList
            System.out.println(arrayList.get(i));
        }
        return jsonArray;
    }

    public void writeJsonFile(String fileName, JSONObject jsonObject) throws JSONException {

//        JSONArray ja = new JSONArray();
//
//        //Inserting key-value pairs into the json object
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("hey", true);
//        ja.put(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("Park", "1");
//        ja.put(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("Kim", "12");
//        ja.put(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("Cheong", "123");
//        ja.put(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("Paek", "1234");
//        ja.put(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("Byun", "12345");
//        ja.put(jsonObject);
//
//        jsonObject = new JSONObject();
//        jsonObject.put("Kang", "123456");
//        ja.put(jsonObject);

        File path = getActivity().getApplicationContext().getFilesDir();
        System.out.println("The file path is :");
        System.out.println(path);
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(jsonObject.toString().getBytes());
            Toast.makeText(getActivity().getApplicationContext(), "Wrote to file: " + fileName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
    }
}
