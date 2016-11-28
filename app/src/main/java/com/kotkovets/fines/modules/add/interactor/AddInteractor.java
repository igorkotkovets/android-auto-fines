package com.kotkovets.fines.modules.add.interactor;

import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.services.store.DatabaseServiceInterface;

/**
 * Created by igork on 4/5/16.
 */
public class AddInteractor implements AddInteractorInput {
    DatabaseServiceInterface databaseStoreService;
    AddInteractorOutput output;

    public void setDatabaseStoreService(DatabaseServiceInterface databaseStoreService) {
        this.databaseStoreService = databaseStoreService;
    }
    public void setOutput(AddInteractorOutput output) {
        this.output = output;
    }

    @Override
    public void saveDriver(String name, String surname, String patronymic, String series, String number) {
        Driver driver = new Driver(name, surname, patronymic, series, number);
        databaseStoreService.saveUser(driver);

        if (output != null) {
            output.didSaveDriver();
        }
    }

    @Override
    public Boolean checkDriverName(String name) {
        return name.length()>0;
    }

    @Override
    public Boolean checkDriverSurname(String surname) {
        return surname.length()>0;
    }

    @Override
    public Boolean checkDriverPatronymic(String patronymic) {
        return patronymic.length()>0;
    }

    @Override
    public Boolean checkDriverSeries(String series) {
        return series.length()>0;
    }

    @Override
    public Boolean checkDriverNumber(String number) {
        return number.length()>0;
    }
}
