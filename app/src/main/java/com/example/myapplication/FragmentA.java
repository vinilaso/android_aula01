package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentA extends Fragment {

    private SharedViewModel _sharedViewModel;
    private EditText _editTextNumber1;
    private EditText _editTextNumber2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setDataToViewModel(view);
            }
        };

        _editTextNumber1 = view.findViewById(R.id.editTextNumber1);
        _editTextNumber1.addTextChangedListener(watcher);

        _editTextNumber2 = view.findViewById(R.id.editTextNumber2);
        _editTextNumber2.addTextChangedListener(watcher);

        return view;
    }

    private void setDataToViewModel(View content) {
        try {
            String firstValueStr = _editTextNumber1.getText().toString();
            Double firstValue = firstValueStr.isEmpty() ? 0 : Double.parseDouble(firstValueStr);

            String secondValueStr = _editTextNumber2.getText().toString();
            Double secondValue = secondValueStr.isEmpty() ? 0 : Double.parseDouble(secondValueStr);

            _sharedViewModel.setData(firstValue, secondValue);

        } catch (Exception e) {
            Toast.makeText(content.getContext(), e.getMessage(), 1).show();
        }
    }

    public SharedViewModel getSharedViewModel() {
        return _sharedViewModel;
    }
}
