package com.example.project_1;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Fragment_Address extends Fragment {

    private ListView listview;
    private AddressAdapter adapter;
    MainActivity mainActivity;



    public Fragment_Address(){

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String json = "";
        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;

        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
        adapter = new AddressAdapter();

        listview = (ListView) rootView.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivity.change_to_Detail(i);
            }
        });



        try {
            inputStream = assetManager.open("callbook.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("callbook");
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                adapter.addItem(userData.getString("name"), R.drawable.avocado, userData.getString("number"));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

       adapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

}
