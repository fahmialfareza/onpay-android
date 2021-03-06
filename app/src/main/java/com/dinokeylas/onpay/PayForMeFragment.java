package com.dinokeylas.onpay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dinokeylas.onpay.util.Preferences;

import java.util.Objects;

public class PayForMeFragment extends Fragment implements View.OnClickListener{

    private int nominal;

    public PayForMeFragment() {
        // Required empty public constructor
    }

    public static PayForMeFragment newInstance(String param1, String param2) {
        return new PayForMeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_for_me, container, false);

        TextView tvNominal = view.findViewById(R.id.tv_nominal);
        Button btnPay = view.findViewById(R.id.btn_pay_for_me);

        Preferences preferences = new Preferences();
        nominal = preferences.getNominalPreferences(getContext());
        tvNominal.setText(String.valueOf(nominal));

        btnPay.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_pay_for_me) {
            buildMessage(nominal);
        }
    }

    private void buildMessage(int nominal){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alertDialogBuilder.setTitle("Detail");
        alertDialogBuilder
                .setMessage("Total Payment: Rp " + nominal + "\nDikirim ke Gilang Widianto")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Pay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        navigateToHomeActivity();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void navigateToHomeActivity(){
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }
}
