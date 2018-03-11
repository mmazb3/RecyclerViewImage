package me.abetayev.recyclerviewimage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Recycle View Adapter
 */

class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{

    private ArrayList<String> imageUrls;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        MyViewHolder(View view) {
            super(view);
            this.image = view.findViewById(R.id.image);
        }
    }

    RVAdapter(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String urlSmall = imageUrls.get(position);
        String urlBig = urlSmall.replaceFirst("_m.", "_b.");
        Picasso.get().load(urlBig).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}
