package com.dance.chd.chddance.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.adapter.ExoticListAdapter;
import com.dance.chd.chddance.helper.MapHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DancerMap.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DancerMap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DancerMap extends Fragment implements OnMapReadyCallback {
    private static String TAG = DancerMap.class.getSimpleName();
    private ExoticListAdapter exoticListAdapter;
    private OnFragmentInteractionListener mListener;
    private SupportMapFragment mapFragment;

    public DancerMap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExoticDancerList.
     */
    public static DancerMap newInstance() {
        DancerMap fragment = new DancerMap();
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
        View view = null;
        // Get our map
        if (mapFragment == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_map, container, false);
            ButterKnife.bind(this, view);

            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

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

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(49.28234902951551, -123.10998033732177))      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MapHelper.getInstance().initInstance();

        Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(2000);
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        MapHelper.getInstance().moveDancers(googleMap, mapFragment);
                    }
                });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e(TAG, "onMapClick: " + "lat: " + latLng.latitude + " lon: " + latLng.longitude);
            }
        });
    }

    /**
     * Communication with the Activity
     */
    public interface OnFragmentInteractionListener {

    }
}
