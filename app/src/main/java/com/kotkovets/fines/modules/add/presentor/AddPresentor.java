package com.kotkovets.fines.modules.add.presentor;

import android.content.Context;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.BasePresenter;
import com.kotkovets.fines.modules.add.interactor.AddInteractorInput;
import com.kotkovets.fines.modules.add.interactor.AddInteractorOutput;
import com.kotkovets.fines.modules.add.router.AddRouterInput;
import com.kotkovets.fines.modules.add.view.AddViewInput;
import com.kotkovets.fines.modules.add.view.AddViewOutput;

/**
 * Created by igork on 2/7/16.
 */
public class AddPresentor extends BasePresenter<AddViewInput> implements AddViewOutput, AddInteractorOutput {
    AddInteractorInput interactor;
    AddRouterInput router;
    Context context;


    public void setRouter(AddRouterInput router) {
        this.router = router;
    }

    public void setInteractor(AddInteractorInput interactor) { this.interactor = interactor; }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
    ** AddModuleInterface
    */

    @Override
    public void didTriggerSaveAction(String name, String surname, String patronymic, String series, String number) {
        if (interactor.checkDriverName(name) == false) {
            this.getView().showInputError(context.getString(R.string.error_input_data), context.getString(R.string.error_invalid_name));
        }
        else if (interactor.checkDriverSurname(surname) == false) {
            this.getView().showInputError(context.getString(R.string.error_input_data), context.getString(R.string.error_invalid_surname));
        }
        else if (interactor.checkDriverPatronymic(patronymic) == false) {
            this.getView().showInputError(context.getString(R.string.error_input_data), context.getString(R.string.error_invalid_patronymic));
        }
        else if (interactor.checkDriverSeries(series) == false) {
            this.getView().showInputError(context.getString(R.string.error_input_data), context.getString(R.string.error_invalid_series));
        }
        else if (interactor.checkDriverNumber(number) == false) {
            this.getView().showInputError(context.getString(R.string.error_input_data), context.getString(R.string.error_invalid_number));
        }
        else {
            interactor.saveDriver(name, surname, patronymic, series, number);
        }
    }

    @Override
    public void didUpdateView() {

    }

    /**
     ** AddInteractorOutputInterface
     */

    @Override
    public void didSaveDriver() {
        router.openUsersList();
    }

    @Override
    public void onFailed(String error) {

    }
}
