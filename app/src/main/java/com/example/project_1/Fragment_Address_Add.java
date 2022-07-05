package com.example.project_1;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Address_Add extends Fragment {
    MainActivity mainActivity;
    Button commit;
    EditText name;
    EditText num;
    EditText age;
    EditText explain;

    public Fragment_Address_Add() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_add, container, false);

        name = (EditText) rootView.findViewById(R.id.addCallbookName);
        num = (EditText) rootView.findViewById(R.id.addCallbookNum);
        age = (EditText) rootView.findViewById(R.id.addCallbookAge);
        explain = (EditText) rootView.findViewById(R.id.addCallbookExplain);
        commit = (Button) rootView.findViewById(R.id.commitCallbookButton);


//        @Override
//        public boolean onKeyDown(int keyCode, KeyEvent event) {
//            if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
//                webView.goBack();
//                return true;
//            }
//
//            return super.onKeyDown(keyCode, event);
//        }

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("") || num.getText().toString().equals("")) {
                    Toast.makeText(mainActivity, "Fill In Name & Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                //adapter.addItem(name.getText().toString(), R.drawable.avocado, num.getText().toString());
                mainActivity.moveToAddPage();
                AddressData listViewItem = new AddressData();
                listViewItem.setTitle(name.getText().toString());
                listViewItem.setAge(age.getText().toString());
                listViewItem.setExplain(explain.getText().toString());
                listViewItem.setContent(num.getText().toString());
                listViewItem.setIcon(R.drawable.ic_baseline_person_24);
                mainActivity.getUserAddressList().add(listViewItem);
//                adapter.notifyDataSetChanged();
                mainActivity.moveBackToAddressPage();
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
}
