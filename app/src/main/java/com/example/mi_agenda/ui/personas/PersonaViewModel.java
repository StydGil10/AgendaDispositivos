package com.example.mi_agenda.ui.personas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PersonaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Persona fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}