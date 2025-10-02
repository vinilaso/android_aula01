package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;

public class DataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.data_fragment, container, false);

        loadElements(view);

        return view;
    }

    private void loadElements(View view)
    {
        TextView resultView = view.findViewById(R.id.imc_result_view);
        resultView.setText(getFormattedIMCValue());

        TextView userNameView = view.findViewById(R.id.label_username);
        userNameView.setText(getUserName());
    }

    private String getFormattedIMCValue()
    {
        Double imcValue = requireArguments().getDouble("imc");
        return NumberFormat.getInstance().format(imcValue);
    }

    private String getUserName()
    {
        return requireArguments().getString("user-name");
    }

}
