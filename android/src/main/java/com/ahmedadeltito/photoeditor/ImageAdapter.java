package com.ahmedadeltito.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import ui.photoeditor.R;

/**
 * Created by Ahmed Adel on 5/4/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> stickerUrls;
    private LayoutInflater inflater;
    private OnImageClickListener onImageClickListener;
    private Context context;

    public ImageAdapter(@NonNull Context context, @NonNull List<String> stickerUrls) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.stickerUrls = stickerUrls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_photo_edit_image_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(stickerUrls.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return stickerUrls.size();
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fragment_photo_edit_image_iv);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onImageClickListener != null)
                        onImageClickListener.onImageClickListener(((BitmapDrawable)imageView.getDrawable()).getBitmap());
                }
            });
        }
    }

    public interface OnImageClickListener {
        void onImageClickListener(Bitmap image);
    }
}
