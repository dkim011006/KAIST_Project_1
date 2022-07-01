package com.example.project_1;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class CallBookClick extends Fragment {

    int jsonChoice = 0;

    TextView titleText;
    TextView explainText;
    TextView numText;
    TextView ageText;

    public CallBookClick() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call_book_click, container, false);
        InputStream inputStream = null;
        AssetManager assetManager = getResources().getAssets();
        String json = "";

        titleText = (TextView) rootView.findViewById(R.id.title_text);
        explainText = (TextView) rootView.findViewById(R.id.explain_text);
        numText = (TextView) rootView.findViewById(R.id.num_text);
        ageText = (TextView) rootView.findViewById(R.id.age_text);

        try {
            inputStream = assetManager.open("callbook.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("callbook");
            JSONObject userData = jsonArray.getJSONObject(jsonChoice);
            titleText.setText(userData.getString("name"));
            explainText.setText(userData.getString("explain"));
            numText.setText(userData.getString("number"));
            ageText.setText((String)userData.get("age"));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    public void jsonNum(int i){
        jsonChoice = i;
    }
}