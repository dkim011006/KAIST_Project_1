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
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_Address extends Fragment {

    private ListView listview;
    private AddressAdapter adapter;
    MainActivity mainActivity;


    Button addButton;
    Button clearButton;

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
        MainActivity mainActivity = (MainActivity) getActivity();

//        arrayList = new ArrayList<AddressData>();
        arrayList = mainActivity.getUserAddressList();
        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
        adapter = new AddressAdapter(arrayList);

        addButton = (Button) rootView.findViewById(R.id.addCallbookButton);
        clearButton = (Button) rootView.findViewById(R.id.clearCallbookButton);

        //Add Btn Click 시 Add Page로 이동
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.moveToAddPage();
            }
        });

        //Add Btn Touch 시 키보드 내림
//        addButton.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                if (getActivity() != null && getActivity().getCurrentFocus() != null)
//                {
//                    // 프래그먼트기 때문에 getActivity() 사용
//                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                }
//                return false;
//            }
//        });

        //Clear Btn Click 시 item list clear
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        //list rendering
        listview = (ListView) rootView.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //item click 시 상세 페이지로 이동
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainActivity.moveToDetailPage(arrayList.get(i).getTitle(),arrayList.get(i).getContent(),arrayList.get(i).getExplain(),arrayList.get(i).getAge());
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
