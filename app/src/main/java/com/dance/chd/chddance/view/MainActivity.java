package com.dance.chd.chddance.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.view.fragment.DancerMap;
import com.dance.chd.chddance.view.fragment.ExoticDancerList;
import com.dance.chd.chddance.view.fragment.ManWomanPagerFragment;
import com.dance.chd.chddance.view.fragment.PartyMode;

public class MainActivity extends AppCompatActivity implements
        ExoticDancerList.OnFragmentInteractionListener,
        PartyMode.OnFragmentInteractionListener,
        DancerMap.OnFragmentInteractionListener,
        ManWomanPagerFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main, ManWomanPagerFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment instanceof ManWomanPagerFragment) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        } else if (!(fragment instanceof PartyMode)) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    /**
     * on clear, we should clear our stuff
     */
    @Override
    public void onClear() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment instanceof ExoticDancerList) {
            ((ManWomanPagerFragment) fragment).onClear();
        }
    }
}
