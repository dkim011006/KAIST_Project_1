package com.example.project_1;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Address extends Fragment {

    private ListView listview;
    private AddressAdapter adapter;
    MainActivity mainActivity;

    EditText name;
    EditText num;
    EditText age;
    EditText explain;
    Button addButton;

    FrameLayout frameLayout;

    ArrayList<AddressData> arrayList;


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

        arrayList = new ArrayList<AddressData>();

        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
        adapter = new AddressAdapter();

        name = (EditText) rootView.findViewById(R.id.addCallbookName);
        num = (EditText) rootView.findViewById(R.id.addCallbookNum);
        age = (EditText) rootView.findViewById(R.id.addCallbookAge);
        explain = (EditText) rootView.findViewById(R.id.addCallbookExplain);
        addButton = (Button) rootView.findViewById(R.id.addCallbookButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(name.getText().toString(), R.drawable.avocado, num.getText().toString());
                AddressData listViewItem = new AddressData();
                listViewItem.setTitle(name.getText().toString());
                listViewItem.setAge(age.getText().toString());
                listViewItem.setExplain(explain.getText().toString());
                listViewItem.setContent(num.getText().toString());
                arrayList.add(listViewItem);
                adapter.notifyDataSetChanged();
            }
        });

        addButton.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (getActivity() != null && getActivity().getCurrentFocus() != null)
                {
                    // 프래그먼트기 때문에 getActivity() 사용
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });

        listview = (ListView) rootView.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivity.change_to_Detail(arrayList.get(i).getTitle(),arrayList.get(i).getContent(),arrayList.get(i).getExplain(),arrayList.get(i).getAge());
            }
        });

       adapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

}
