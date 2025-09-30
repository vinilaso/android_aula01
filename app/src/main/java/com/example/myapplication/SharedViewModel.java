package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Double> _firstNumber = new MutableLiveData<>();
    private final MutableLiveData<Double> _secondNumber = new MutableLiveData<>();

    public void setData(Double firstNumber, Double secondNumber) {
        _firstNumber.setValue(firstNumber);
        _secondNumber.setValue(secondNumber);
    }

    public LiveData<Double> getFirstNumber() {
        return _firstNumber;
    }

    public LiveData<Double> getSecondNumber() {
        return _secondNumber;
    }
}
