package com.touchmenotapps.flashy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.touchmenotapps.flashy.AssessmentsActivity;
import com.touchmenotapps.flashy.R;
import com.touchmenotapps.flashy.RegisterActivity;
import com.touchmenotapps.flashy.dao.FlashCardDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by school on 19/8/16.
 */
public class SwipeStackAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<FlashCardDAO> flashCardDAOList = new ArrayList<>();

    public SwipeStackAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<FlashCardDAO> data) {
        flashCardDAOList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return flashCardDAOList.size();
    }

    @Override
    public FlashCardDAO getItem(int position) {
        return flashCardDAOList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.adapter_flash_card, parent, false);
            holder.header = (TextView) convertView.findViewById(R.id.adapter_cards_header);
            holder.body = (TextView) convertView.findViewById(R.id.adapter_cards_body);
            holder.sessionTime = (TextView) convertView.findViewById(R.id.adapter_cards_session_time);
            holder.activityCount = (TextView) convertView.findViewById(R.id.adapter_cards_activity_count);
            holder.headerImage = (ImageView) convertView.findViewById(R.id.adapter_cards_image);
            holder.bookmarkFooter = (AppCompatCheckBox) convertView.findViewById(R.id.adapter_cards_bookmark_footer);
            holder.activityFooter = (RelativeLayout) convertView.findViewById(R.id.adapter_card_activity_footer);
            holder.coverFooter = (LinearLayout) convertView.findViewById(R.id.adapter_cards_cover_footer);
            holder.assessmentsBtn = (FloatingActionButton) convertView.findViewById(R.id.adapter_cards_footer_question_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (flashCardDAOList.get(position).getFlashCardType()) {
            case INTRO:
                holder.coverFooter.setVisibility(View.VISIBLE);
                break;
            case CONTENT:
                holder.bookmarkFooter.setVisibility(View.VISIBLE);
                break;
            case CONTENT_ACTIVITY:
                holder.activityFooter.setVisibility(View.VISIBLE);
                break;
        }
        if(flashCardDAOList.get(position).getHeader() != null) {
            holder.header.setText(flashCardDAOList.get(position).getHeader());
        } else {
            holder.header.setVisibility(View.GONE);
        }
        holder.body.setText(flashCardDAOList.get(position).getBody());
        holder.assessmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AssessmentsActivity.class));
            }
        });
        Picasso.with(context)
                .load(flashCardDAOList.get(position).getImageURL())
                .placeholder(R.drawable.flask)
                .error(R.drawable.flask)
                .into(holder.headerImage);
        return convertView;
    }

    static class ViewHolder {
        TextView header, body, sessionTime, activityCount;
        ImageView headerImage;
        AppCompatCheckBox bookmarkFooter;
        RelativeLayout activityFooter;
        LinearLayout coverFooter;
        FloatingActionButton assessmentsBtn;
    }
}
