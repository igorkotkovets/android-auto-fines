package com.kotkovets.fines.modules.add.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kotkovets.fines.R;
import com.kotkovets.fines.modules.add.assembly.AddModule;
import com.kotkovets.fines.modules.add.assembly.DaggerAddComponent;

import javax.inject.Inject;

public class AddUserFragment extends Fragment implements AddViewInput {
    EditText nameEditText;
    EditText surnameEditText;
    EditText patronymicEditText;
    EditText seriesEditText;
    EditText numberEditText;
    Context context;
    @Inject AddViewOutput output;

    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DaggerAddComponent.builder().addModule(new AddModule(getActivity(), this)).build().inject(this);

        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        nameEditText = (EditText)view.findViewById(R.id.nameEditText);
        surnameEditText = (EditText)view.findViewById(R.id.surnameEditText);
        patronymicEditText = (EditText)view.findViewById(R.id.patronymicEditText);
        seriesEditText = (EditText)view.findViewById(R.id.seriesEditText);
        numberEditText = (EditText)view.findViewById(R.id.numberEditText);

        registerTextFieldsForEditEvents(nameEditText, (TextView) view.findViewById(R.id.nameLabel));
        registerTextFieldsForEditEvents(surnameEditText, (TextView) view.findViewById(R.id.surnameLabel));
        registerTextFieldsForEditEvents(patronymicEditText, (TextView) view.findViewById(R.id.patronymicLabel));
        registerTextFieldsForEditEvents(seriesEditText, (TextView) view.findViewById(R.id.seriesLabel));
        registerTextFieldsForEditEvents(numberEditText, (TextView) view.findViewById(R.id.numberLabel));

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu_add_driver, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            output.didTriggerSaveAction(nameEditText.getText().toString(),
                    surnameEditText.getText().toString(),
                    patronymicEditText.getText().toString(),
                    seriesEditText.getText().toString(),
                    numberEditText.getText().toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();

        output.didUpdateView();
    }

    @Override
    public void setEntryName(String name) {
        this.nameEditText.setText(name);
    }

    @Override
    public void setEntrySurname(String surname) {
        this.surnameEditText.setText(surname);
    }

    @Override
    public void setEntryPatronymic(String patronymic) {
        this.patronymicEditText.setText(patronymic);
    }

    @Override
    public void setEntrySeries(String series) {
        this.seriesEditText.setText(series);
    }

    @Override
    public void setEntryNumber(String number) {
        this.numberEditText.setText(number);
    }

    @Override
    public void showInputError(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void registerTextFieldsForEditEvents(EditText editText, final TextView label) {
        updateLabelVisibility(label, editText.getText().length());

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                updateLabelVisibility(label, editable.length());
            }
        });

    }

    private void updateLabelVisibility(TextView label, int inputTextCount) {
        label.setVisibility(inputTextCount>0?View.VISIBLE:View.INVISIBLE);
    }
}
