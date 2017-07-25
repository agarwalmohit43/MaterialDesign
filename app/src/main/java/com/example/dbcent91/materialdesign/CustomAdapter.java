package com.example.dbcent91.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by dbcent91 on 19/7/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    List<Inforamtion> data = Collections.emptyList();

    private final LayoutInflater inflator;


     Context context;

    private onClickListener onClickListener;
    CustomAdapter(Context context, List<Inforamtion> data) {
        this.context=context;
        inflator = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.customrow, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Inforamtion current=data.get(position);
        holder.tv.setText(current.title);
        holder.iv.setImageResource(current.iconId);

      /* holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked "+position,Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void deleteData(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }



    public void setClickListener(onClickListener onClickListener){

        this.onClickListener = onClickListener;
    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv;
        ImageView iv;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.customrowTextView);
            iv = (ImageView) itemView.findViewById(R.id.customrowImageView);
            //linearLayout=(LinearLayout) itemView.findViewById(R.id.customrowLinearLayout);

            //tv.setOnClickListener(this);
            //iv.setOnClickListener(this);
           // linearLayout.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          //  Toast.makeText(context,"Clicked At pos -> "+getPosition(),Toast.LENGTH_SHORT).show();

          /* switch (getPosition()){
                case 0: context.startActivity(new Intent(context,SubActivity.class));
                    break;
               default:
                   Toast.makeText(context,"Clicked At pos -> "+getPosition(),Toast.LENGTH_SHORT).show();
            }*/
          //deleteData(getPosition() );


            if(onClickListener!=null){
                onClickListener.itemclicked(view,getPosition());
            }


        }


    }

    public interface onClickListener{
        public void itemclicked(View view,int position);
    }
}
