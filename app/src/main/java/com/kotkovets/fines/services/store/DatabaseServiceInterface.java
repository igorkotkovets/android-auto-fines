package com.kotkovets.fines.services.store;

import com.kotkovets.fines.common.model.Fine;
import com.kotkovets.fines.common.model.Driver;

import java.util.List;

/**
 * Created by igork on 4/3/16.
 */
public interface DatabaseServiceInterface {
    boolean saveUser(Driver driverStoreObject);
    List<Driver> loadAllDrivers();
    Driver loadDriver(int userId);
    Driver deleteDriverIfExists(int uniqueUserId);

    boolean saveFine(Fine fineStoreObject, int userUniqueKey);
    Fine loadFine(int userUniqueKey);
}
