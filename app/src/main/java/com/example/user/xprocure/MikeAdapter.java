package com.example.user.xprocure;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 24/01/2017.
 */

public class MikeAdapter extends RecyclerView.Adapter<MikeAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
   private  Context context;
    private ClickListener clickListener;

    public MikeAdapter(Context context, List<Information> data) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    public void delete (int position){
        data.remove(position);
notifyItemRemoved(position);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.customrow, parent, false);
        Log.d("MIKE","onCreateViewHolder called");
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        Log.d("MIKE","onBindViewHolder"+position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.itemId);
    }

    public void setClickListener (ClickListener clickListener){
        this.clickListener=clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.ic_launcher);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          context.startActivity(new Intent(context,SubActivity.class));
            if(clickListener != null){
                clickListener.itemClicked(v,getPosition());
            }

        }

    }
    public interface ClickListener{
       void itemClicked (View view,int position);

        }

}
