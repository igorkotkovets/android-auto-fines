package com.kotkovets.fines.modules.list.view;

/**
 * Created by igork on 4/16/16.
 */
public interface DriversListViewOutput {
    void didTriggerOnStartActivity();
    void didTriggerAddNewUserAction();
    void didTriggerDeleteUserAction(int unuqueUserId);
    void didTriggerVerifyUserAction(int userId);
    void didTriggerRefreshViewAction();
}
