package com.touchmenotapps.flashy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheatSheetActivity extends AppCompatActivity {

    @BindView(R.id.cheat_sheet_chapter_name) TextView headerChapterName;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.cheat_sheet_header_image) ImageView headerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_sheet);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().getStringExtra("header").equalsIgnoreCase("biology")) {
            headerImage.setImageResource(R.drawable.biology);
        }
        headerChapterName.setText(getIntent().getStringExtra("description"));
    }

    @OnClick(R.id.cheat_sheet_revise_lesson_btn)
    void onReviseLessonClicked() {
        Intent intent = new Intent(this, ViewLessonActivity.class);
        intent.putExtra("header", getIntent().getStringExtra("header"));
        intent.putExtra("description", getIntent().getStringExtra("description"));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
