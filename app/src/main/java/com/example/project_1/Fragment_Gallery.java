package com.example.project_1;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

//    Button btn_gallery_getImages;
//    Button btn_gallery_clearAll;


    public Fragment_Gallery(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        //  Gallery의 RecyclerView 구현 부분 시작
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        gridLayoutManager = new GridLayoutManager(v.getContext(), 3);
        recyclerView.setLayoutManager((gridLayoutManager)); // 리사이클러뷰 그리드레이아웃 적용
        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge)); //decorate recyclerview's image layout


//        arrayList = new ArrayList<>();
        System.out.println("Bringing Gallery");
        System.out.println(mainActivity.getUserGalleryList());
        arrayList = mainActivity.getUserGalleryList();
        System.out.println("Brought Gallery");


        galleryAdapter = new GalleryAdapter(arrayList);
        System.out.println("created gallery adapter");
        recyclerView.setAdapter(galleryAdapter);
        System.out.println("adapter setted");

        Button btn_gallery_getImages = (Button) v.findViewById(R.id.btn_gallery_getImages);
        btn_gallery_getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                System.out.println("Intent Created");
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                System.out.println("Type setted");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                System.out.println("Extra putted");
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2222);
                System.out.println("startActivityForResulted");
            }
        });
        System.out.println("click listener setted");

//        btn_gallery_clearAll = v.findViewById(R.id.btn_gallery_clearAll);
//        btn_gallery_clearAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                arrayList.clear();
//            }
//        });

        //  Gallery의 RecyclerView 구현 부분 종료




        return v;
    }

    // 앨범에서 액티비티로 돌아온 후 실행되는 메서드
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null){   // 어떤 이미지도 선택하지 않은 경우
            Toast.makeText(getActivity().getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
        }
        else{   // 이미지를 하나라도 선택한 경우

//            arrayList= new ArrayList<>();

            if(data.getClipData() == null){     // 이미지를 하나만 선택한 경우
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri imageUri = data.getData();
                GalleryData galleryData = new GalleryData(imageUri);
                arrayList.add(galleryData);

            }
            else{      // 이미지를 여러장 선택한 경우 -- 라고 블로그에서말했으나 로그보면 하나인 경우도 여기에 해당하는 듯
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));


                Log.e(TAG, "multiple choice");

                for (int i = 0; i < clipData.getItemCount(); i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();  // 선택한 이미지들의 uri를 가져온다.
                    try {
                        GalleryData galleryData = new GalleryData(imageUri);
                        arrayList.add(galleryData);

                    } catch (Exception e) {
                        Log.e(TAG, "File select error", e);
                    }
                }

                galleryAdapter = new GalleryAdapter(arrayList);
                recyclerView.setAdapter(galleryAdapter);
            }
        }
    }

}
