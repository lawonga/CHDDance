package com.dance.chd.chddance.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.adapter.ManWomanPagerAdapter;
import com.dance.chd.chddance.helper.DancerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManWomanPagerFragment extends Fragment {
    @BindView(R.id.tab_pager) ViewPager manWomanPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.exotic_pager_buttonset) LinearLayout buttonSet;
    @BindView(R.id.exotic_pager_subtotal) TextView exoticSubtitle;
    @BindView(R.id.button_send) Button send;
    @BindView(R.id.button_clear) Button clear;
    private OnFragmentInteractionListener mListener;
    private ManWomanPagerAdapter manWomanPagerAdapterAdapter;

    public ManWomanPagerFragment() {
        // Required empty public constructor
    }

    public static ManWomanPagerFragment newInstance() {
        ManWomanPagerFragment fragment = new ManWomanPagerFragment();
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
        updateSubtotal();
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
        onClear();
    }

    /**
     * Clear
     */
    public void onClear() {
        DancerHelper.getInstance().clearDancerList();
        ExoticDancerList womanFragment = (ExoticDancerList) manWomanPagerAdapterAdapter.getRegisteredFragments().get(0);
        ExoticDancerList manFragment = (ExoticDancerList) manWomanPagerAdapterAdapter.getRegisteredFragments().get(1);
        womanFragment.notifyAdapterChanged();
        manFragment.notifyAdapterChanged();
        updateSubtotal();
    }

    public void updateSubtotal() {
        setTotalAmount(DancerHelper.getInstance().getFemaleDancerCount(),
                DancerHelper.getInstance().getMaleDancerCount());
    }

    /**
     * Sets the total amount of orders we have
     *
     * @param woman pass in woman
     * @param man   pass in man
     */
    public void setTotalAmount(int woman, int man) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SUBTOTAL: \n");
        if (woman > 0) {
            stringBuilder.append(String.valueOf(woman)).append(" WOMAN \n");
        }
        if (man > 0) {
            stringBuilder.append(String.valueOf(man)).append(" MAN");
        }
        exoticSubtitle.setText(stringBuilder.toString());
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
        void onSend();

        void onClear();
    }
}
