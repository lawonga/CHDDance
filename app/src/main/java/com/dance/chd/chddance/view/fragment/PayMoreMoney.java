package com.dance.chd.chddance.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.helper.PayMoreMoneyHelper;
import com.dance.chd.chddance.view.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MOENY EXTORTION FRAGMENT
 */
public class PayMoreMoney extends Fragment {
    @BindView(R.id.reload_amount) EditText reloadEditText;

    public PayMoreMoney() {
        // Required empty public constructor
    }


    public static PayMoreMoney newInstance() {
        PayMoreMoney fragment = new PayMoreMoney();
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
        View view = inflater.inflate(R.layout.fragment_pay_more_money, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.reload_button)
    public void onSubmitReload() {
        if (reloadEditText.getText().toString() != "" && Double.parseDouble(reloadEditText.getText().toString()) > 0) {
            PayMoreMoneyHelper.getInstance().setMoneys(Double.parseDouble(reloadEditText.getText().toString()));
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.onMoneyPaid();
        } else {
            Toast.makeText(getContext(), "NOT ENOUGH MONEY!", Toast.LENGTH_SHORT).show();
        }
    }
}
