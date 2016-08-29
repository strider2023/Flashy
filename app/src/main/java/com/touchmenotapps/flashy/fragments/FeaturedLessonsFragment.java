package com.touchmenotapps.flashy.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.touchmenotapps.flashy.R;
import com.touchmenotapps.flashy.adapters.FeaturedLessonsAdapter;
import com.touchmenotapps.flashy.dao.LessonsDAO;
import com.touchmenotapps.flashy.dao.enums.LoaderID;
import com.touchmenotapps.flashy.threads.loaders.FeaturedLessonsLoaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by school on 23/8/16.
 */
public class FeaturedLessonsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<LessonsDAO>> {

    private View mViewHolder;
    private RecyclerView listView;
    private FeaturedLessonsAdapter featuredLessonsAdapter;
    private ProgressDialog progressDialog;
    private Bundle queryData;

    public static FeaturedLessonsFragment newInstance() {
        FeaturedLessonsFragment fragment = new FeaturedLessonsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_featured_lessons, container, false);
        listView = (RecyclerView) mViewHolder.findViewById(R.id.featured_lessons_list);

        featuredLessonsAdapter = new FeaturedLessonsAdapter(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Content...");

        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(featuredLessonsAdapter);

        queryData = new Bundle();
        queryData.putString("query", "");
        getActivity().getSupportLoaderManager()
                .initLoader(LoaderID.FEATURED_LESSOSN.getValue(), queryData, this).forceLoad();

        return mViewHolder;
    }

    @Override
    public Loader<List<LessonsDAO>> onCreateLoader(int id, Bundle args) {
        return new FeaturedLessonsLoaderTask(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<List<LessonsDAO>> loader, List<LessonsDAO> data) {
        if(data != null) {
            featuredLessonsAdapter.setData(data);
        } else {
            featuredLessonsAdapter.setData(new ArrayList<LessonsDAO>());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LessonsDAO>> loader) {

    }
}
