package com.example.operationx;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameOptionsViewModel extends ViewModel {

    private MutableLiveData<Integer> currentLives;
    private MutableLiveData<Integer> currentLevel;
    private MutableLiveData<Boolean> mapPressed;
    private MutableLiveData<Boolean> pausePressed;

    public MutableLiveData<Integer> getCurrentLives() {
        if(currentLives == null) {
            currentLives = new MutableLiveData<>();
        }
        return currentLives;
    }

    public MutableLiveData<Integer> getCurrentLevel() {
        if(currentLevel == null) {
            currentLevel = new MutableLiveData<>();
        }
        return currentLevel;
    }

    public MutableLiveData<Boolean> getIsMapPressed() {
        if(mapPressed == null) {
            mapPressed = new MutableLiveData<>();
        }
        return mapPressed;
    }

    public MutableLiveData<Boolean> getIsPausePressed() {
        if(pausePressed == null) {
            pausePressed = new MutableLiveData<>();
        }
        return pausePressed;
    }



}
