package com.dance.chd.chddance.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.adapter.ExoticListAdapter;
import com.dance.chd.chddance.helper.DancerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.exotic_list_buttonset) LinearLayout buttonSet;
    @BindView(R.id.button_send) Button send;
    @BindView(R.id.button_clear) Button clear;
    private ExoticListAdapter exoticListAdapter;
    private OnFragmentInteractionListener mListener;

    public ExoticDancerList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExoticDancerList.
     */
    public static ExoticDancerList newInstance() {
        ExoticDancerList fragment = new ExoticDancerList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exotic_dancer_list, container, false);
        ButterKnife.bind(this, view);

        // Adapter
        exoticListAdapter = new ExoticListAdapter(DancerHelper.getInstance().getDancerList());

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
     * Send
     */
    @OnClick(R.id.button_send)
    public void send() {
        mListener.onSend();
    }

    /**
     * Clear
     */
    @OnClick(R.id.button_clear)
    public void clear() {
        mListener.onClear();
    }

    /**
     * Clear
     */
    public void onClear() {
        DancerHelper.getInstance().clearDancerList();
        exoticListAdapter.notifyDataSetChanged();
    }

    /**
     * Communication with the Activity
     */
    public interface OnFragmentInteractionListener {

        void onSend();

        void onClear();

    }
}
