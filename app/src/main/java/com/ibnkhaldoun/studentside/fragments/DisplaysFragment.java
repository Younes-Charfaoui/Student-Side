/*------------------------------------------------------------------------------
 - Copyright (c) 2018. This code was created by Younes Charfaoui in the process of Graduation Project for the year of  2018 , which is about creating a platform  for students and professors to help them in the communication and the get known of the university information and so on.
 -----------------------------------------------------------------------------*/

package com.ibnkhaldoun.studentside.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ibnkhaldoun.studentside.R;
import com.ibnkhaldoun.studentside.adapters.DisplaysAdapter;
import com.ibnkhaldoun.studentside.interfaces.IDataFragment;
import com.ibnkhaldoun.studentside.models.Display;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.ibnkhaldoun.studentside.activities.StudentMainActivity.DISPLAY_TYPE;


public class DisplaysFragment extends BaseMainFragment<Display> implements SwipeRefreshLayout.OnRefreshListener {

    public static final int PROFESSOR = 1;
    public static final String KEY = "key";
    private ProgressBar mLoadingProgressBar;
    private LinearLayout mEmptyLayout;
    private RecyclerView mDisplaysRecyclerView;
    private DisplaysAdapter mAdapter;
    private IDataFragment mInterface;
    private SwipeRefreshLayout mDisplaySwipe;

    public static DisplaysFragment professorInstance() {

        Bundle args = new Bundle();
        args.putInt(KEY, PROFESSOR);
        DisplaysFragment fragment = new DisplaysFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (IDataFragment) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_displays, container, false);
        mLoadingProgressBar = view.findViewById(R.id.display_progress_bar);
        mDisplaysRecyclerView = view.findViewById(R.id.displays_recycler_view);
        mEmptyLayout = view.findViewById(R.id.display_empty_view);
        mDisplaySwipe = view.findViewById(R.id.display_swipe);

        mInterface.onNeedData(this);

        mDisplaySwipe.setOnRefreshListener(this);
        mEmptyLayout.setOnClickListener(v -> mInterface.onNeedData(this));

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        if (getArguments() != null) {
            if (getArguments().getInt(KEY, -1) == PROFESSOR)
                mAdapter = new DisplaysAdapter(getContext(), getFragmentManager(), PROFESSOR);
            else mAdapter = new DisplaysAdapter(getContext(), getFragmentManager());
        } else {
            mAdapter = new DisplaysAdapter(getContext(), getFragmentManager());
        }
        mDisplaysRecyclerView.setLayoutManager(manager);
        mDisplaysRecyclerView.setAdapter(mAdapter);
        mDisplaysRecyclerView.setHasFixedSize(true);
        mInterface.onAttach(this);
        return view;
    }

    @Override
    public void onNetworkLoadedSucceed(List<Display> list) {
        if (mDisplaySwipe.isRefreshing()) {
            mDisplaySwipe.setRefreshing(false);
        }
        mLoadingProgressBar.setVisibility(GONE);
        if (list.size() != 0) {
            mAdapter.swapList(list);
            mDisplaysRecyclerView.setVisibility(VISIBLE);
        } else {
            mEmptyLayout.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onNetworkStartLoading() {
        mLoadingProgressBar.setVisibility(VISIBLE);
        mDisplaysRecyclerView.setVisibility(GONE);
        mEmptyLayout.setVisibility(GONE);
    }

    @Override
    public void onNetworkLoadingFailed(int errorType) {
        if (mDisplaySwipe.isRefreshing()) {
            mDisplaySwipe.setRefreshing(false);
        }
        switch (errorType) {
            case INTERNET_ERROR:
                mEmptyLayout.setVisibility(VISIBLE);
                mLoadingProgressBar.setVisibility(GONE);
                mDisplaysRecyclerView.setVisibility(GONE);
                Toast.makeText(getContext(),
                        R.string.no_internet_connection_string,
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDatabaseLoadingFinished(Cursor cursor) {

    }

    @Override
    public void onDatabaseStartLoading() {
        mLoadingProgressBar.setVisibility(VISIBLE);
        mDisplaysRecyclerView.setVisibility(GONE);
        mEmptyLayout.setVisibility(GONE);
    }

    @Override
    public int getBaseType() {
        return DISPLAY_TYPE;
    }

    @Override
    public void onRefresh() {
        mInterface.onNeedData(this);
    }
}
