package com.example.mi_agenda.ui.citas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CitaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Citas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}