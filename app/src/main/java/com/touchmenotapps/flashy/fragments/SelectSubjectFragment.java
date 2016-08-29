package com.touchmenotapps.flashy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.touchmenotapps.flashy.R;
import com.touchmenotapps.flashy.adapters.SubjectsAdapter;
import com.touchmenotapps.flashy.dao.SubjectsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by school on 26/8/16.
 */
public class SelectSubjectFragment extends Fragment {

    private View mViewHolder;
    private RecyclerView subjectsRecyclerView;
    private SubjectsAdapter subjectsAdapter;
    private List<SubjectsDAO> subjectsDAOList = new ArrayList<>();

    public static SelectSubjectFragment newInstance() {
        SelectSubjectFragment fragment = new SelectSubjectFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewHolder = inflater.inflate(R.layout.fragment_select_subject, container, false);
        subjectsRecyclerView = (RecyclerView) mViewHolder.findViewById(R.id.subjects_list);

        subjectsAdapter = new SubjectsAdapter(getActivity());
        subjectsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        subjectsRecyclerView.setAdapter(subjectsAdapter);

        SubjectsDAO subjectsDAO = new SubjectsDAO(getActivity());
        subjectsDAO.setImage(R.drawable.sub_biology);
        subjectsDAO.setSubjectName("Biology");
        subjectsDAOList.add(subjectsDAO);

        subjectsDAO = new SubjectsDAO(getActivity());
        subjectsDAO.setImage(R.drawable.sub_chemistry);
        subjectsDAO.setSubjectName("Chemistry");
        subjectsDAOList.add(subjectsDAO);

        subjectsDAO = new SubjectsDAO(getActivity());
        subjectsDAO.setImage(R.drawable.sub_maths);
        subjectsDAO.setSubjectName("Maths");
        subjectsDAOList.add(subjectsDAO);

        subjectsDAO = new SubjectsDAO(getActivity());
        subjectsDAO.setImage(R.drawable.sub_computer);
        subjectsDAO.setSubjectName("Computer");
        subjectsDAOList.add(subjectsDAO);

        subjectsAdapter.setData(subjectsDAOList);

        return mViewHolder;
    }
}
