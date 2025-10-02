package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SuggestionsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.suggestions_fragment, container, false);
        loadElements(view);
        return view;
    }

    private void loadElements(View view)
    {
        double imc = requireArguments().getDouble("imc");

        if (imc < 20)
            lessThanExpected(view);
        else if (imc > 25)
            moreThanExpected(view);
        else
            expected(view);
    }

    private void lessThanExpected(View view)
    {
        getStatusLabel(view).setText(getString(R.string.low_imc));

        // Professor, me perdoe por essa lógica horrivel kkkk
        // Mas preciso terminar logo pra pegar o onibus e nao quero perder tempo pensando
        // na formula correta
        int quilosAGanhar = 0;
        double altura = requireArguments().getDouble("height");
        double imc = requireArguments().getDouble("imc");

        while (imc < 20)
        {
            quilosAGanhar++;
            imc = (requireArguments().getDouble("weight") + quilosAGanhar) / ( altura * altura );
        }

        String suggestion = String.format(getString(R.string.low_suggestion), quilosAGanhar);
        getSuggestionLabel(view).setText(suggestion);
    }

    private void moreThanExpected(View view)
    {
        getStatusLabel(view).setText(getString(R.string.high_imc));

        // Professor, me perdoe por essa lógica horrivel kkkk
        // Mas preciso terminar logo pra pegar o onibus e nao quero perder tempo pensando
        // na formula correta
        int quilosAPerder = 0;
        double altura = requireArguments().getDouble("height");
        double imc = requireArguments().getDouble("imc");

        while (imc > 25)
        {
            quilosAPerder++;
            imc = (requireArguments().getDouble("weight") - quilosAPerder) / ( altura * altura );
        }

        String suggestion = String.format(getString(R.string.high_suggestion), quilosAPerder);
        getSuggestionLabel(view).setText(suggestion);
    }

    private void expected(View view)
    {
        getStatusLabel(view).setText(getString(R.string.expected_imc));
        getSuggestionLabel(view).setText(getString(R.string.expected_suggestion));
    }

    private TextView getStatusLabel(View view)
    {
        return view.findViewById(R.id.label_status);

    }

    private TextView getSuggestionLabel(View view)
    {
        return view.findViewById(R.id.label_suggestion);
    }
}
