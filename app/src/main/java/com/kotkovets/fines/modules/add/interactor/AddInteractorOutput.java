package com.kotkovets.fines.modules.add.interactor;

/**
 * Created by igork on 4/23/16.
 */
public interface AddInteractorOutput {
    void didSaveDriver();
    void onFailed(String error);
}
