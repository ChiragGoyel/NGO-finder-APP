package com.example.maps;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private Context context;
    List<Upload> uploadList;

    public ImageAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_card,parent,false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Upload currentUpload = uploadList.get(position);
        holder.tvImName.setText(currentUpload.getImageName());
        holder.material.setText(currentUpload.getMaterial());
//       holder.Decsription.setText(currentUpload.getDescription());
        Glide.with(context).load(currentUpload.getImageUrl()).into(holder.imUploaded);
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }


    public class ImageHolder extends RecyclerView.ViewHolder{
        private TextView tvImName;
        private ImageView imUploaded;
//        private TextView Decsription;
        private TextView material;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            tvImName = itemView.findViewById(R.id.tv_im_name);
//            Decsription = itemView.findViewById(R.id.Description);
            imUploaded = itemView.findViewById(R.id.iv_image_uploaded);
            material = itemView.findViewById(R.id.material);
        }
    }
}
