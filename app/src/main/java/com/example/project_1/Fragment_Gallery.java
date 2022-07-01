package com.example.project_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_Gallery extends Fragment {

    private ArrayList<GalleryData> arrayList;
    private GalleryAdapter galleryAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;


    public Fragment_Gallery(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        //  Gallery의 RecyclerView 구현 부분 시작
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        gridLayoutManager = new GridLayoutManager(v.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0) return 2;
                return 1;
            }
        });
        recyclerView.setLayoutManager((gridLayoutManager));
        arrayList = new ArrayList<>();

        galleryAdapter = new GalleryAdapter(arrayList);
        recyclerView.setAdapter(galleryAdapter);

        Button btn_gallery_addphoto = (Button) v.findViewById(R.id.btn_gallery_addphoto);
        btn_gallery_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GalleryData galleryData = new GalleryData(R.mipmap.ic_launcher);

                arrayList.add(galleryData);
                galleryAdapter.notifyDataSetChanged();
            }
        });
        //  Gallery의 RecyclerView 구현 부분 종료




        return v;
    }
}
