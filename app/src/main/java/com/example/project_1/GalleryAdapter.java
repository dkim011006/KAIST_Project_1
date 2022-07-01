package com.example.project_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.CustomViewHolder> {

    private ArrayList<GalleryData> arrayList;

    public GalleryAdapter(ArrayList<GalleryData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override

    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_list, parent, false);

        CustomViewHolder holder = new CustomViewHolder((view));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.iv_photo.setImageResource(arrayList.get(position).getIv_photo());

        holder.itemView.setTag(position);

//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                int PICK_IMAGE = 1111;
//                startActivityForResult(intent, PICK_IMAGE);
//            }
//        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return null != arrayList ? arrayList.size() : 0;
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyDataSetChanged();
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_photo;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }
}
