package com.dance.chd.chddance.helper;

import android.util.SparseArray;

import com.dance.chd.chddance.view.MainActivity;
import com.dance.chd.chddance.view.fragment.DancerMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Andy on 11/5/2016.
 */
public class MapHelper {
    private List<MarkerOptions> markers = new ArrayList<>();
    private SparseArray<LatLng> sparseArray = new SparseArray<>();
    private static MapHelper ourInstance = new MapHelper();
    private Subscription subscription;
    private Marker ourMarker;
    private int frame = 7;

    public static MapHelper getInstance() {
        return ourInstance;
    }

    public void initInstance() {
        ourMarker = null;
        markers.clear();
        sparseArray.clear();
        frame = 7;

        markers.add(new MarkerOptions().position(new LatLng(49.246383, -123.116437)));
        markers.add(new MarkerOptions().position(new LatLng(49.246673, -123.116547)));
        markers.add(new MarkerOptions().position(new LatLng(49.246263, -123.116127)));
        markers.add(new MarkerOptions().position(new LatLng(49.246453, -123.116317)));

        // Add 10 frames
        sparseArray.put(0, new LatLng(49.286498902236985, -123.12401097267866));
        sparseArray.put(1, new LatLng(49.28578092816394, -123.12312215566635));
        sparseArray.put(2, new LatLng(49.284489948177985, -123.12105417251587));
        sparseArray.put(3, new LatLng(49.28327504456134, -123.1190850958228));
        sparseArray.put(4, new LatLng(49.28190591838482, -123.11704728752375));
        sparseArray.put(5, new LatLng(49.282048737955094, -123.11435870826244));
        sparseArray.put(6, new LatLng(49.28157740993204, -123.11084400862455));
        sparseArray.put(7, new LatLng(49.28234902951551, -123.10998033732177));
        sparseArray.put(8, new LatLng(49.28198793579963, -123.10972418636084));
        sparseArray.put(9, new LatLng(49.282479600002326, -123.1094827875495));
        sparseArray.put(10, new LatLng(49.28231272342258, -123.10907442122696));
        sparseArray.put(11, new LatLng(49.28224929705149, -123.10882430523634));
        sparseArray.put(12, new LatLng(49.282222395497065, -123.10859631747006));
        sparseArray.put(13, new LatLng(49.28216968590475, -123.10826741158962));
        sparseArray.put(14, new LatLng(49.28213053642005, -123.1080863624811));
        sparseArray.put(15, new LatLng(49.2820367087576, -123.10810714960097));
        sparseArray.put(16, new LatLng(49.28190241897211, -123.10817822813988));
    }

    private MapHelper() {
    }

    public void moveDancers(final GoogleMap googleMap, final SupportMapFragment dancerMap) {
        for (MarkerOptions markerOptions : markers) {
            if (ourMarker == null) {
                ourMarker = googleMap.addMarker(markerOptions);
            } else {
                googleMap.addMarker(markerOptions);
            }
        }

        subscription = Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                for (int i = 7; i < 17; i++) {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        MainActivity mainActivity = (MainActivity) dancerMap.getActivity();
                        mainActivity.onGoToPartyMode();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                        if (sparseArray.get(frame) != null) {
                            ourMarker.setPosition(sparseArray.get(frame));
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .zoom(17)
                                    .target(sparseArray.get(frame))      // Sets the center of the map to Mountain View
                                    .build();                   // Creates a CameraPosition from the builder
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            frame += 1;
                        } else {
                            subscription.unsubscribe();
                        }
                    }
                });
    }
}