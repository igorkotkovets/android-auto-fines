package com.kotkovets.fines.modules.list.presenter;

import android.content.Context;

import com.kotkovets.fines.common.BasePresenter;
import com.kotkovets.fines.common.DriverInfoDisplaySerializer;
import com.kotkovets.fines.common.applicationlogic.AppConstants;
import com.kotkovets.fines.common.display.DisplayDriverInfo;
import com.kotkovets.fines.common.model.Driver;
import com.kotkovets.fines.common.model.DriverInfo;
import com.kotkovets.fines.modules.list.interactor.DriversListInteractorInput;
import com.kotkovets.fines.modules.list.interactor.DriversListInteractorOutput;
import com.kotkovets.fines.modules.list.router.ListRouterInput;
import com.kotkovets.fines.modules.list.view.DriversListViewInput;
import com.kotkovets.fines.modules.list.view.DriversListViewOutput;
import com.kotkovets.fines.modules.utils.settings.AppSettingsInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by igork on 4/16/16.
 */
public class DriversListPresenter extends BasePresenter<DriversListViewInput> implements DriversListViewOutput, DriversListInteractorOutput {
    private DriversListInteractorInput interactor;
    ListRouterInput router;
    Context context;
    private List<DriverInfo> drivers;
    private List<Integer> updatingDrivers = new ArrayList<>();
    AppSettingsInterface settings;

    public DriversListPresenter(Context  activity) {
        this.context = activity;
    }


    public DriversListInteractorInput getInteractor() {
        return interactor;
    }

    public void setInteractor(DriversListInteractorInput interactor) {
        this.interactor = interactor;
    }

    public ListRouterInput getRouter() {
        return router;
    }

    public void setRouter(ListRouterInput router) {
        this.router = router;
    }

    public void setSettings(AppSettingsInterface settings) {
        this.settings = settings;
    }

    /**
     * ListModuleInterface
     */

    @Override
    public void didTriggerOnStartActivity() {
        if (settings.needsShowGuide(AppConstants.GUIDE_VERSION)) {
            settings.setNeedsShowGuide(AppConstants.GUIDE_VERSION, false);
            this.router.openUserGuide();
        }
        this.interactor.loadDrivers();
    }

    @Override
    public void didTriggerAddNewUserAction() {
        router.openAddNewUser();
    }

    @Override
    public void didTriggerDeleteUserAction(int unuqueUserId) {
        interactor.deleteUser(unuqueUserId);
    }

    @Override
    public void didTriggerVerifyUserAction(int userId) {
        if (isDriverUpdating(userId) == false) {
            addDriverToUpdating(userId);
            interactor.verifyDriver(userId);
            getView().displayDrivers(createUserDisplayItems(drivers));
        }
    }

    @Override
    public void didTriggerRefreshViewAction() {
        interactor.verifyDrivers(addDriversToUpdatingIfNeeded(drivers));
        getView().displayDrivers(createUserDisplayItems(drivers));
    }

    /**
    ** ListInteractorOutputInterface
     * @param driverList
     */

    @Override
    public void didLoadDrivers(List<DriverInfo> driverList) {
        drivers = driverList;
        List<DisplayDriverInfo> displayInfoList = createUserDisplayItems(drivers);
        if (displayInfoList.size() > 0) {
            getView().displayDrivers(displayInfoList);
        }
        else {
            getView().showNoAutos();
        }

    }

    @Override
    public void didDeleteDriver(int identifier) {
        Driver driver = null;
        ListIterator<DriverInfo> iterator = drivers.listIterator();
        while (iterator.hasNext()) {
            Driver loopDriver = iterator.next().getDriver();
            if (loopDriver.getUniqueKey() == identifier) {
                driver = loopDriver;
            }
        }

        if (driver != null) {
            drivers.remove(driver);
            getView().showDriverWasDeleted(driver.getName(), driver.getSurname(), driver.getPatronymic());
        }
    }

    @Override
    public void didVerifyDriver(int identifier, List<DriverInfo> driverList) {
        removeDriverFromUpdating(identifier);
        drivers = driverList;
        getView().displayDrivers(createUserDisplayItems(drivers));
    }

    /**
     * private
     */

    List<DisplayDriverInfo> createUserDisplayItems(List<DriverInfo> drivers) {
        List<DisplayDriverInfo> displayList = new ArrayList();

        if (drivers == null) {
            return displayList;
        }

        DriverInfoDisplaySerializer serializer = new DriverInfoDisplaySerializer();

        for (DriverInfo driverInfo : drivers) {
            DisplayDriverInfo displayInfo = serializer.create(context, driverInfo);
            displayInfo.setProgressBarVisible(isDriverUpdating(driverInfo.getDriver().getUniqueKey()));
            displayList.add(displayInfo);
        }

        return displayList;
    }

    private Boolean isDriverUpdating(int driverIdentifier) {
        return updatingDrivers.contains(new Integer(driverIdentifier));
    }

    private void addDriverToUpdating(int driverIdentifier) {
        updatingDrivers.add(new Integer(driverIdentifier));
    }

    private int[] addDriversToUpdatingIfNeeded(List<DriverInfo> drivers) {
        int[] driverIdentifiers = new int[drivers.size()];
        int position = 0;
        for (DriverInfo driverInfo : drivers) {
            // if is not updating now
            int identifier = driverInfo.getDriver().getUniqueKey();
            if (isDriverUpdating(identifier) == false) {
                addDriverToUpdating(identifier);
                driverIdentifiers[position] = identifier;
                position++;
            }
        }

        return driverIdentifiers;
    }

    private void removeDriverFromUpdating(int driverIdentifier) {
        updatingDrivers.remove(new Integer(driverIdentifier));
    }
}
