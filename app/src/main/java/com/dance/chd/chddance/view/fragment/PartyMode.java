package com.dance.chd.chddance.view.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.enums.Key;

import java.util.Random;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PartyMode.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PartyMode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartyMode extends Fragment {
    @BindView(R.id.money_drag_image_layout) FrameLayout frameLayout;
    @BindView(R.id.current_money) TextView currentMoney;
    private static String TAG = PartyMode.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private LayoutInflater inflater;
    private Subscription subscription;
    private MediaPlayer player;
    private double totalMoney = 25.00d;

    public PartyMode() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PartyMode.
     */
    public static PartyMode newInstance() {
        PartyMode fragment = new PartyMode();
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
        View view = inflater.inflate(R.layout.fragment_party_mode, container, false);
        ButterKnife.bind(this, view);
        this.inflater = inflater;

        // Add money dragging thing
        addMoneyDraggingThing(inflater, Key.CHEAP);
        addMoneyDraggingThing(inflater, Key.CHEAP);
        addMoneyDraggingThing(inflater, Key.CHEAP);

        // Update current money
        updateCurrentMoney();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Start playing sound
        subscription = Single.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                player = MediaPlayer.create(getActivity(), R.raw.never_gonna_give_you_up);
                player.setLooping(true); // Set looping
                player.setVolume(100, 100);
                player.start();
                return null;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    /**
     * Add this when the previous one is removed from view
     *
     * @param inflater
     * @param keyEnum
     */
    private void addMoneyDraggingThing(final LayoutInflater inflater, final Key keyEnum) {
        final float[] oldX = {0};
        final float[] oldY = {0};
        LinearLayout newView = null;

        // Inflate our money dragging thing
        if (keyEnum.name().equals(Key.CHEAP.name())) {
            newView = (LinearLayout) inflater.inflate(R.layout.money_cheap, frameLayout, false); // CHEAP
        } else if (keyEnum.name().equals(Key.INTERMEDIATE.name())) {
            newView = (LinearLayout) inflater.inflate(R.layout.money_intermediate, frameLayout, false); // INTERMEDIATE
        } else {
            newView = (LinearLayout) inflater.inflate(R.layout.money_expensive, frameLayout, false); // EXPENSIVE
        }

        // Set listeners
        final LinearLayout finalNewView = newView;
        newView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX[0] = event.getRawX();
                        oldY[0] = event.getRawY();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (oldY[0] - event.getRawY() > 300) {
                            flingOffScreen(view, (float) (event.getRawY() - (view.getHeight() / 1.5)));
                            finalNewView.setOnTouchListener(null);
                            addMoneyDraggingThing(inflater, keyEnum);
                            if (keyEnum == Key.CHEAP) {
                                totalMoney -= 0.02d;
                            } else if (keyEnum == Key.INTERMEDIATE) {
                                totalMoney -= 0.05d;
                            } else if (keyEnum == Key.EXPENSIVE) {
                                totalMoney += 0.50d;
                            }
                            updateCurrentMoney();
                        }
                        break;
                }

                view.setX((float) (event.getRawX() - (view.getWidth() / 1.5)));
                view.setY((float) (event.getRawY() - (view.getHeight() / 1.5)));

                return true;
            }
        });
        frameLayout.addView(newView);
    }

    private void flingOffScreen(final View view, float fromY) {
        Random random = new Random();
        float finalNextFloat = random.nextFloat() * (2000 + 2000) - 2000;
        TranslateAnimation translateAnimation = new TranslateAnimation(view.getX(), view.getX() + finalNextFloat, fromY, fromY - 5000);
        translateAnimation.setDuration(500);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //before animation
                view.bringToFront();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //after animation
                if (view != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(translateAnimation);
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
        player.stop();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void updateCurrentMoney() {
        currentMoney.setText("$" + String.format("%.2f", totalMoney));

        // Go to pay more $$ screen
        if (totalMoney <= 0) {

        }
    }

    @OnClick(R.id.button_expensive)
    public void onExpensivePressed() {
        frameLayout.removeAllViews();
        addMoneyDraggingThing(inflater, Key.EXPENSIVE);
        addMoneyDraggingThing(inflater, Key.EXPENSIVE);
    }

    @OnClick(R.id.button_intermediate)
    public void onIntermediatePressed() {
        frameLayout.removeAllViews();
        addMoneyDraggingThing(inflater, Key.INTERMEDIATE);
        addMoneyDraggingThing(inflater, Key.INTERMEDIATE);
    }

    @OnClick(R.id.button_cheap)
    public void onCheapPressed() {
        frameLayout.removeAllViews();
        addMoneyDraggingThing(inflater, Key.CHEAP);
        addMoneyDraggingThing(inflater, Key.CHEAP);
    }

    /**
     * Communication with the Activity
     */
    public interface OnFragmentInteractionListener {
    }
}
