package com.example.project_1;

import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CallBookClick extends Fragment {

    int jsonChoice = 0;

    TextView titleText = null;
    TextView explainText;
    TextView numText;
    TextView ageText;

    String name;
    String num;
    String age;
    String explain;

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

        titleText.setText(name);
        explainText.setText(explain);
        numText.setText(num);
        ageText.setText(age);

        return rootView;
    }

    public void jsonNum(String name, String num, String explain, String age){
        this.name = name;
        this.num = num;
        this.explain = explain;
        this.age = age;
    }
}