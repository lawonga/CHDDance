package com.dance.chd.chddance.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.adapter.ManWomanPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManWOmanPagerFragment extends Fragment {
    @BindView(R.id.tab_pager) ViewPager manWomanPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    private OnFragmentInteractionListener mListener;
    ManWomanPagerAdapter manWomanPagerAdapterAdapter;

    public ManWOmanPagerFragment() {
        // Required empty public constructor
    }

    public static ManWOmanPagerFragment newInstance() {
        ManWOmanPagerFragment fragment = new ManWOmanPagerFragment();
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
        View view = inflater.inflate(R.layout.fragment_manwomanpager, container, false);
        ButterKnife.bind(this, view);
        // Set our manWomanPager adapter here
        manWomanPagerAdapterAdapter = new ManWomanPagerAdapter(getChildFragmentManager());
        manWomanPager.setAdapter(manWomanPagerAdapterAdapter);
        tabLayout.setupWithViewPager(manWomanPager);
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
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
