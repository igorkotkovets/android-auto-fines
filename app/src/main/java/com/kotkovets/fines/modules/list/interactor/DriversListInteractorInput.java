package com.kotkovets.fines.modules.list.interactor;

/**
 * Created by igork on 4/16/16.
 */
public interface DriversListInteractorInput {
    void deleteUser(int uniqueUserId);
    void loadDrivers();
    void verifyDrivers(int[] driverIdentifiers);
    void verifyDriver(int userId);
}
