package com.jay.moappstest.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.moappstest.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> appImagesList;
    private ArrayList<String> appNamesList;
    private ArrayList<Date> datePaidList;
    private ArrayList<Boolean> isCompleteList;

    private OnItemClickListener callback;

    private String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

    public AppsListAdapter(Context context, ArrayList<String> appImagesList, ArrayList<String> appNamesList,
                           ArrayList<Date> datePaidList, ArrayList<Boolean> isCompleteList,
                           OnItemClickListener callback) {
        this.context = context;
        this.appImagesList = appImagesList;
        this.appNamesList = appNamesList;
        this.datePaidList = datePaidList;
        this.isCompleteList = isCompleteList;
        this.callback = callback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_apps_list,
                parent, false);
        return new AppsListAdapter.ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.appName.setText(appNamesList.get(position));

        Picasso.get().load(appImagesList.get(position)).into(holder.appIcon);

        if (isCompleteList.get(position)){

            holder.complete.setText(context.getResources().getString(R.string.complete));
        } else {
            holder.complete.setText(context.getResources().getString(R.string.incomplete));
        }

        if (datePaidList.get(position) != null){

            holder.isCompleteImage.setImageResource(R.mipmap.icon_complete);
            holder.paid.setText(context.getResources().getString(R.string.paid_to) + date);
        } else {
            holder.isCompleteImage.setImageResource(R.mipmap.icon_not_completed);
            holder.paid.setText(context.getResources().getString(R.string.not_paid));
        }
    }



    @Override
    public int getItemCount() {
        return appNamesList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView appName;
        ImageView appIcon;
        ImageView isCompleteImage;
        TextView paid;
        TextView complete;

        ViewHolder(View itemView) {
            super(itemView);

            appName = itemView.findViewById(R.id.app_name);
            appIcon = itemView.findViewById(R.id.app_image);
            isCompleteImage = itemView.findViewById(R.id.image_is_complete);
            paid = itemView.findViewById(R.id.is_paid);
            complete = itemView.findViewById(R.id.is_complete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
