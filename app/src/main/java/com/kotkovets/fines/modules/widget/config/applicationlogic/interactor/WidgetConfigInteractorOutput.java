package com.kotkovets.fines.modules.widget.config.applicationlogic.interactor;

import com.kotkovets.fines.common.model.Driver;

import java.util.List;

/**
 * Created by igork on 4/24/16.
 */
public interface WidgetConfigInteractorOutput {
    void onLoadUsers(List<Driver> driverList);
}
