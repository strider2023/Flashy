package com.touchmenotapps.flashy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.touchmenotapps.flashy.adapters.LessonsAdapter;
import com.touchmenotapps.flashy.dao.LessonsDAO;
import com.touchmenotapps.flashy.dao.enums.LoaderID;
import com.touchmenotapps.flashy.threads.loaders.LessonsLoaderTask;
import com.touchmenotapps.flashy.views.CustomScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class LessonsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<LessonsDAO>>{

    @BindView(R.id.subject_lessons_list)
    CustomScrollListView customScrollListView;

    private LessonsAdapter lessonsAdapter;
    private ProgressDialog progressDialog;
    private Bundle queryData;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(getIntent().getStringExtra("subject"));

        lessonsAdapter = new LessonsAdapter(this);
        customScrollListView.setAdapter(lessonsAdapter);

        queryData = new Bundle();
        queryData.putString("query", "");
        getSupportLoaderManager()
                .initLoader(LoaderID.LESSONS.getValue(), queryData, this).forceLoad();

        customScrollListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LessonsDAO lessonsDAO = lessonsAdapter.getItem(i);
                Intent intent = new Intent(LessonsActivity.this, ViewLessonActivity.class);
                intent.putExtra("header", lessonsDAO.getSubjectName());
                intent.putExtra("description", lessonsDAO.getChapterName());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lessons, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search Lessons");
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public Loader<List<LessonsDAO>> onCreateLoader(int id, Bundle args) {
        return new LessonsLoaderTask(this, null);
    }

    @Override
    public void onLoadFinished(Loader<List<LessonsDAO>> loader, List<LessonsDAO> data) {
        if(data != null) {
            lessonsAdapter.setData(data);
        } else {
            lessonsAdapter.setData(new ArrayList<LessonsDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LessonsDAO>> loader) {

    }
}
