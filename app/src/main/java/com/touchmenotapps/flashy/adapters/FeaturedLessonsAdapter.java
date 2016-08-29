package com.touchmenotapps.flashy.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.touchmenotapps.flashy.CheatSheetActivity;
import com.touchmenotapps.flashy.ViewLessonActivity;
import com.touchmenotapps.flashy.R;
import com.touchmenotapps.flashy.dao.LessonsDAO;
import com.touchmenotapps.flashy.dao.enums.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by school on 24/8/16.
 */
public class FeaturedLessonsAdapter extends RecyclerView.Adapter<FeaturedLessonsAdapter.MyViewHolder> {

    private List<LessonsDAO> lessonsDAOList = new ArrayList<>();
    private Activity activity;

    public FeaturedLessonsAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_featured_chapters,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    public void setData(List<LessonsDAO> data) {
        lessonsDAOList = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.subject.setText(lessonsDAOList.get(position).getSubjectName());
        holder.chapter.setText(lessonsDAOList.get(position).getChapterName());
        holder.completionTime.setText(lessonsDAOList.get(position).getRevisionTime());
        if(lessonsDAOList.get(position).getSubject() == Subject.BIOLOGY) {
            holder.subjectImage.setImageResource(R.drawable.biology);
        }
        if(lessonsDAOList.get(position).isComplete()) {
            holder.chapterComplete.setVisibility(View.VISIBLE);
        }

        holder.viewChapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewLessonActivity.class);
                intent.putExtra("anim_x", holder.viewChapterBtn.getLeft());
                intent.putExtra("anim_y", holder.viewChapterBtn.getTop());
                intent.putExtra("header", lessonsDAOList.get(position).getSubjectName());
                intent.putExtra("description", lessonsDAOList.get(position).getChapterName());
                activity.startActivity(intent);
            }
        });

        holder.chapterSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewLessonActivity.class);
                intent.putExtra("anim_x", holder.viewChapterBtn.getLeft());
                intent.putExtra("anim_y", holder.viewChapterBtn.getTop());
                intent.putExtra("header", lessonsDAOList.get(position).getSubjectName());
                intent.putExtra("description", lessonsDAOList.get(position).getChapterName());
                activity.startActivity(intent);
            }
        });

        holder.cheatSheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CheatSheetActivity.class);
                intent.putExtra("header", lessonsDAOList.get(position).getSubjectName());
                intent.putExtra("description", lessonsDAOList.get(position).getChapterName());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonsDAOList == null ? 0 : lessonsDAOList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subject, chapter, completionTime, chapterComplete;
        ImageView subjectImage;
        LinearLayout cheatSheetBtn;
        FloatingActionButton viewChapterBtn;
        LinearLayout chapterSelected;

        public MyViewHolder(View itemView) {
            super(itemView);
            chapterComplete = (TextView) itemView.findViewById(R.id.adapter_featured_chapters_complete);
            subject = (TextView) itemView.findViewById(R.id.adapter_featured_chapters_subject);
            chapter = (TextView) itemView.findViewById(R.id.adapter_featured_chapters_chapter);
            completionTime = (TextView) itemView.findViewById(R.id.adapter_featured_chapters_time);
            subjectImage = (ImageView) itemView.findViewById(R.id.adapter_featured_chapters_image);
            cheatSheetBtn = (LinearLayout) itemView.findViewById(R.id.adapter_featured_chapters_cheat_sheet_btn);
            viewChapterBtn = (FloatingActionButton) itemView.findViewById(R.id.adapter_featured_chapters_view_btn);
            chapterSelected = (LinearLayout) itemView.findViewById(R.id.chapter_content_layout);
        }
    }
}
