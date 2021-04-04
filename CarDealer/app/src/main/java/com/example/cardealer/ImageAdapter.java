package com.example.cardealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.List;
//public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
//    private Context mContext;
//    private List<Chat> mChats;
//    public ImageAdapter(Context context, List<Chat> Chats) {
//        mContext = context;
//        mChats = Chats;
//    }
//    @Override
//    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(mContext).inflate(R.layout.car_view, parent, false);
//        return new ImageViewHolder(v);
//    }
//    @Override
////    public void onBindViewHolder(ImageViewHolder holder, int position) {
////        Chat chatCurrent = mChats.get(position);
////        holder.textViewName.setText(chatCurrent.getName());
////        Picasso.with(mContext)
//////                .load(chatCurrent.getImageUrl())
////                .fit()
////                .centerCrop()
////                .into(holder.imageView);
//    }
//    @Override
//    public int getItemCount() {
//        return mChats.size();
//    }
//    public class ImageViewHolder extends RecyclerView.ViewHolder {
//        public TextView textViewName;
//        public ImageView imageView;
//        public ImageViewHolder(View itemView) {
//            super(itemView);
//            textViewName = itemView.findViewById(R.id.carname_card);
//            imageView = itemView.findViewById(R.id.img_card);
//        }
//    }
//}
