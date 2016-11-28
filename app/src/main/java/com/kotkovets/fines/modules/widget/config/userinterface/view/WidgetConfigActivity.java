package com.kotkovets.fines.modules.widget.config.userinterface.view;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.display.DriverDisplayItem;
import com.kotkovets.fines.modules.list.view.OnUsersListFragmentInteractionListener;
import com.kotkovets.fines.modules.widget.config.moduleinterface.WidgetConfigModuleInterface;
import com.kotkovets.fines.modules.widget.config.userinterface.presentor.WidgetConfigPresentor;

import java.util.List;

public class WidgetConfigActivity extends AppCompatActivity implements WidgetConfigViewInterface, OnUsersListFragmentInteractionListener {
    private final static String TAG = "WidgetConfigActivity";
    WidgetConfigModuleInterface presentor;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // a tip if user cancel config activity
        setResult(RESULT_CANCELED);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Log.i(TAG, "onCreate with widget id: " + Integer.toString(mAppWidgetId));

        WidgetConfigPresentor presentor = new WidgetConfigPresentor(this, mAppWidgetId);
        presentor.setView(this);
        this.presentor = presentor;

        setContentView(R.layout.activity_widget_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        presentor.updateView();
    }

    /**
     * WidgetConfigViewInterface
     */

    @Override
    public void showUsers(List<DriverDisplayItem> users) {
        recyclerView.setAdapter(new WidgetConfigRecyclerViewAdapter(users, this));
    }

    /**
     ** OnUsersListFragmentInteractionListener
     */

    @Override
    public void onListFragmentItemTap(int userUniqueId) {
        presentor.selectAction(userUniqueId);
    }

    @Override
    public void onListFragmentItemDelete(int userUniqueId) {
    }
}
