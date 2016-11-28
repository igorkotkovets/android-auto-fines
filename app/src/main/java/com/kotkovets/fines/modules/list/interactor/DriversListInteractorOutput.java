package com.kotkovets.fines.modules.list.interactor;

import com.kotkovets.fines.common.model.DriverInfo;

import java.util.List;

/**
 * Created by igork on 4/16/16.
 */
public interface DriversListInteractorOutput {
    void didLoadDrivers(List<DriverInfo> driverList);
    void didDeleteDriver(int identifier);
    void didVerifyDriver(int identifier, List<DriverInfo> driverList);
}
