package com.touchmenotapps.flashy.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.touchmenotapps.flashy.CheatSheetActivity;
import com.touchmenotapps.flashy.R;
import com.touchmenotapps.flashy.dao.LessonsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by school on 29/8/16.
 */
public class LessonsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<LessonsDAO> lessonsDAOList = new ArrayList<>();

    public LessonsAdapter (Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<LessonsDAO> data) {
        lessonsDAOList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lessonsDAOList.size();
    }

    @Override
    public LessonsDAO getItem(int position) {
        return lessonsDAOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_lessons, parent, false);
            holder.lessonName = (TextView) convertView.findViewById(R.id.adapter_lesson_name);
            holder.sessionTime = (TextView) convertView.findViewById(R.id.adapter_lesson_time);
            holder.cheatSheetBtn = (ImageView) convertView.findViewById(R.id.adapter_lesson_cheat_sheet_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lessonName.setText(lessonsDAOList.get(position).getChapterName());
        holder.sessionTime.setText(lessonsDAOList.get(position).getRevisionTime());
        holder.cheatSheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CheatSheetActivity.class);
                intent.putExtra("header", lessonsDAOList.get(position).getSubjectName());
                intent.putExtra("description", lessonsDAOList.get(position).getChapterName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView lessonName, sessionTime;
        ImageView cheatSheetBtn;
    }
}
