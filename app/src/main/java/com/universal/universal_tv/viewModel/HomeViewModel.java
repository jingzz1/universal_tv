package com.universal.universal_tv.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;



public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> showIndex = new MutableLiveData<>();
    private int index = -1;

    public void showFragment(int index){
        this.index = index;
        showIndex.postValue(index);
    }

    public LiveData<Integer> getShowIndex() {
        return showIndex;
    }

    public int getIndex() {
        return index;
    }
}
