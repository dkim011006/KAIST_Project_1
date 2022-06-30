package com.example.project_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Address extends Fragment {

    private ListView listview;
    private ListViewAdapter adapter;

    public Fragment_Address(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
        adapter = new ListViewAdapter();

        listview = (ListView) rootView.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItem("김대연", R.drawable.ic_launcher_foreground,"010-2507-7308");
        adapter.addItem("아보카도", R.drawable.avocado,"010-1010-1010");
        adapter.addItem("미나리", R.drawable.minari,"010-1111-1111");
        adapter.addItem("텔레토비", R.drawable.ic_launcher_background,"010-2323-3434");
        adapter.addItem("뚱이", R.drawable.ic_launcher_background,"010-9999-8888");
        adapter.addItem("소고기", R.drawable.ic_launcher_background,"010-2343-2453");
        adapter.addItem("꿀꿀이", R.drawable.ic_launcher_background,"010-2324-1241");
        adapter.addItem("넙쭉이", R.drawable.ic_launcher_background,"010-1000-1000");
        adapter.addItem("포닉스", R.drawable.ic_launcher_background,"010-3434-2392");
        adapter.addItem("김무환", R.drawable.ic_launcher_background,"010-3049-2938");
        adapter.addItem("소보로", R.drawable.ic_launcher_background,"010-2984-2938");
        adapter.addItem("이재용", R.drawable.ic_launcher_background,"010-9092-2839");
       adapter.notifyDataSetChanged();


        return rootView;
    }
}
