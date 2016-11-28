package com.kotkovets.fines.modules.list.view;

import android.view.View;

import com.kotkovets.fines.common.display.DisplayDriverInfo;

import java.util.List;

/**
 * Created by igork on 4/16/16.
 */
public interface DriversListViewInput {
    void showNoAutos();
    void showDriverWasDeleted(String name, String surname, String patronymic);
    void displayDrivers(List<DisplayDriverInfo> users);
    void addNewUser(View view);
}

