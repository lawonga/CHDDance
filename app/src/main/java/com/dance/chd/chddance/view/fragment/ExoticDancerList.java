package com.dance.chd.chddance.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.adapter.ExoticListAdapter;
import com.dance.chd.chddance.enums.Key;
import com.dance.chd.chddance.helper.DancerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExoticDancerList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExoticDancerList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExoticDancerList extends Fragment {
    @BindView(R.id.exotic_list_recycler) RecyclerView recyclerView;
    private boolean gender;
    private ExoticListAdapter exoticListAdapter;
    private OnFragmentInteractionListener mListener;

    public ExoticDancerList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param b
     * @return A new instance of fragment ExoticDancerList.
     */
    public static ExoticDancerList newInstance(boolean b) {
        ExoticDancerList fragment = new ExoticDancerList();
        Bundle args = new Bundle();
        args.putBoolean(Key.GENDER.name(), b);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.gender = getArguments().getBoolean(Key.GENDER.name());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exotic_dancer_list, container, false);
        ButterKnife.bind(this, view);

        // Adapter
        if (gender) {
            exoticListAdapter = new ExoticListAdapter(DancerHelper.getInstance().getFemaleDancerList(), this);
        } else {
            exoticListAdapter = new ExoticListAdapter(DancerHelper.getInstance().getMaleDancerList(), this);
        }

        // Recycler & layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(exoticListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Notify data set changed
     */
    public void notifyAdapterChanged() {
        if (recyclerView != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    /**
     * Gets our pager to update the stats
     */
    public void updateSubtotal() {
        ManWomanPagerFragment manWomanPagerFragment = (ManWomanPagerFragment) getParentFragment();
        manWomanPagerFragment.updateSubtotal();
    }

    /**
     * Communication with the Activity
     */
    public interface OnFragmentInteractionListener {

    }
}
