package com.kotkovets.fines.modules.add.view;

/**
 * Created by igork on 2/7/16.
 */
public interface AddViewInput {
    void setEntryName(String name);
    void setEntrySurname(String surname);
    void setEntryPatronymic(String patronymic);
    void setEntrySeries(String series);
    void setEntryNumber(String number);
    void showInputError(String title, String message);
}
