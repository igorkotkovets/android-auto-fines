package com.kotkovets.fines.modules.list.interactor;

import android.content.Context;

import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.common.model.DriverInfo;
import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.modules.utils.settings.AppSettingsInterface;
import com.kotkovets.fines.services.network.FineRequestRunnable;
import com.kotkovets.fines.services.network.FinesRequestParametersBuilder;
import com.kotkovets.fines.services.network.RequestRunnableExecutor;
import com.kotkovets.fines.services.network.core.Response;
import com.kotkovets.fines.services.store.DatabaseServiceInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by igork on 4/16/16.
 */
public class DriversListInteractor implements DriversListInteractorInput, FineRequestRunnable.Output {
    Context context;
    DatabaseServiceInterface database;
    private DriversListInteractorOutput output;
    private RequestRunnableExecutor executor;
    AppSettingsInterface appSettings;


    public void setDatabase(DatabaseServiceInterface database) {
        this.database = database;
    }
    public DriversListInteractor() {
        executor = new RequestRunnableExecutor();
    }
    public void setOutput(DriversListInteractorOutput output) {
        this.output = output;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AppSettingsInterface getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(AppSettingsInterface appSettings) {
        this.appSettings = appSettings;
    }

    /**
     * ListInteractorInputInterface
     */

    @Override
    public void loadDrivers() {
        if (output != null) {
            List<Driver> drivers = database.loadAllDrivers();
            List<DriverInfo> outputDrivers = new ArrayList<>();
            Iterator<Driver> iterator = drivers.iterator();
            while (iterator.hasNext()) {
                Driver driver = iterator.next();
                Fine fine = database.loadFine(driver.getUniqueKey());
                DriverInfo driverInfo = new DriverInfo(driver, fine);
                outputDrivers.add(driverInfo);
            }
            output.didLoadDrivers(outputDrivers);
        }
    }

    @Override
    public void verifyDrivers(int[] driverIdentifiers) {
        for (int i=0; i<driverIdentifiers.length; i++) {
            verifyDriver(driverIdentifiers[i]);
        }
    }

    @Override
    public void deleteUser(int uniqueUserId) {
        database.deleteDriverIfExists(uniqueUserId);
        if (output != null) {
            output.didDeleteDriver(uniqueUserId);
        }
    }

    @Override
    public void verifyDriver(int userId) {
        Driver driver = database.loadDriver(userId);
        if (driver != null) {
            FinesRequestParametersBuilder builder = new FinesRequestParametersBuilder();
            builder.setName(driver.getName());
            builder.setSurname(driver.getSurname());
            builder.setPatronymic(driver.getPatronymic());
            builder.setSeries(driver.getSeries());
            builder.setNumber(driver.getNumber());
            builder.setTag(userId);

            FineRequestRunnable runnable = new FineRequestRunnable(context, builder.build());
            runnable.setOutput(this);
            executor.add(runnable);
        }
    }

    /**
     * FineRequestRunnable.Output
     */

    @Override
    public void didStartRequest(int tag) {

    }

    @Override
    public void didFinishRequest(int tag, Response response) {
        if (response.getCode() == 0) {
            database.saveFine((Fine) response.getData(), tag);
        }
        else {
            database.saveFine(new Fine(response.getError(), new Date()), tag);
        }

        if (output != null) {
            List<Driver> drivers = database.loadAllDrivers();
            List<DriverInfo> outputDrivers = new ArrayList<>();
            Iterator<Driver> iterator = drivers.iterator();
            while (iterator.hasNext()) {
                Driver driver = iterator.next();
                Fine fine = database.loadFine(driver.getUniqueKey());
                DriverInfo driverInfo = new DriverInfo(driver, fine);
                outputDrivers.add(driverInfo);
            }

            output.didVerifyDriver(tag, outputDrivers);
        }
    }
}
