package com.touchmenotapps.flashy.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.touchmenotapps.flashy.R;
import com.touchmenotapps.flashy.LessonsActivity;
import com.touchmenotapps.flashy.dao.SubjectsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by school on 26/8/16.
 */
public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.MyViewHolder> {

    private List<SubjectsDAO> subjectsDAOList = new ArrayList<>();
    private Activity activity;

    public SubjectsAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<SubjectsDAO> data) {
        subjectsDAOList = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_select_subject,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.subjectName.setText(subjectsDAOList.get(position).getSubjectName());
        holder.subjectImage.setImageResource(subjectsDAOList.get(position).getImage());
        if(position%2 == 0) {
            holder.subjectImage.setBackgroundResource(R.drawable.shape_oval_primary_dark);
        }

        holder.subjectContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LessonsActivity.class);
                intent.putExtra("subject", subjectsDAOList.get(position).getSubjectName());
                activity.startActivity(intent);
            }
        });
        /*Picasso.with(context)
                .load(subjectsDAOList.get(position).getImageURL())
                .placeholder(R.drawable.flask)
                .error(R.drawable.flask)
                .into(holder.subjectImage);*/
    }

    @Override
    public int getItemCount() {
        return subjectsDAOList == null ? 0 : subjectsDAOList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName;
        ImageView subjectImage;
        LinearLayout subjectContainer;

        public MyViewHolder(View itemView) {
            super(itemView);
            subjectContainer = (LinearLayout) itemView.findViewById(R.id.select_subject_container);
            subjectName = (TextView) itemView.findViewById(R.id.select_subject_text);
            subjectImage = (ImageView) itemView.findViewById(R.id.select_subject_icon);
        }
    }
}
