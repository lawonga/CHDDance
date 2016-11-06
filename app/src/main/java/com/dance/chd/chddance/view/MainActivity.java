package com.dance.chd.chddance.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.adapter.ExoticListAdapter;
import com.dance.chd.chddance.view.fragment.DancerMap;
import com.dance.chd.chddance.view.fragment.ExoticDancerList;
import com.dance.chd.chddance.view.fragment.PartyMode;
import com.google.android.gms.maps.MapFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements
        ExoticDancerList.OnFragmentInteractionListener,
        PartyMode.OnFragmentInteractionListener,
DancerMap.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main, ExoticDancerList.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }

    /**
     * Go to party mode
     */
    public void onGoToPartyMode() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main, PartyMode.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .addToBackStack(null)
                .commit();
    }

    /**
     * On send, we should go to the map
     */
    @Override
    public void onSend() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main, DancerMap.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .addToBackStack(null)
                .commit();
    }

    /**
     * on clear, we should clear our stuff
     */
    @Override
    public void onClear() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment instanceof ExoticDancerList) {
            ((ExoticDancerList) fragment).onClear();
        }
    }
}
