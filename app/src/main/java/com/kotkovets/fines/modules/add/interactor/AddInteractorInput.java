package com.kotkovets.fines.modules.add.interactor;

/**
 * Created by igork on 4/5/16.
 */
public interface AddInteractorInput {
    Boolean checkDriverName(String name);
    Boolean checkDriverSurname(String surname);
    Boolean checkDriverPatronymic(String patronymic);
    Boolean checkDriverSeries(String series);
    Boolean checkDriverNumber(String number);
    void saveDriver(String name, String surname, String patronymic, String series, String number);
}
