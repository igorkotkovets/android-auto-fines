package com.kotkovets.fines.modules.list.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kotkovets.fines.R;
import com.kotkovets.fines.common.display.DisplayDriverInfo;
import com.kotkovets.fines.modules.list.assembly.DaggerListComponent;
import com.kotkovets.fines.modules.list.assembly.ListModule;

import java.util.List;

import javax.inject.Inject;

public class DriversListActivity extends AppCompatActivity implements OnUsersListFragmentInteractionListener, DriversListViewInput {
    @Inject
    DriversListViewOutput output;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView emptyListTextView;


    final String TAG = "DriversListActivity";
    private static final int VERTICAL_ITEM_SPACE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerListComponent.builder().listModule(new ListModule(this)).build().inject(this);

        setContentView(R.layout.activity_users_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        emptyListTextView = (TextView) findViewById(R.id.empty_list_text_view);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new RecyclerViewItemSeparatorDecorator(this, R.drawable.recycler_view_item_separator));
            recyclerView.addItemDecoration(new RecyclerViewItemVerticalSpaceDecorator(VERTICAL_ITEM_SPACE));
        }

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshItems();
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        output.didTriggerOnStartActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_users_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            output.didTriggerAddNewUserAction();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ListViewInterface
     */

    @Override
    public void showNoAutos() {
        emptyListTextView.setVisibility(View.VISIBLE);
        refreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void displayDrivers(List<DisplayDriverInfo> items) {
        emptyListTextView.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new UsersListRecyclerViewAdapter(items, this));
    }

    @Override
    public void addNewUser(View view) {
        output.didTriggerAddNewUserAction();
    }

    @Override
    public void showDriverWasDeleted(String name, String surname, String patronymic) {
        String str = "The driver was deleted " + name + surname + patronymic;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     ** OnUsersListFragmentInteractionListener
     */

    @Override
    public void onListFragmentItemTap(int userUniqueId) {
        output.didTriggerVerifyUserAction(userUniqueId);
        output.didTriggerOnStartActivity();
    }

    @Override
    public void onListFragmentItemDelete(int userUniqueId) {
        output.didTriggerDeleteUserAction(userUniqueId);
        output.didTriggerOnStartActivity();
    }

    /**
     * Private
     */

    void refreshItems() {
        output.didTriggerRefreshViewAction();
        refreshLayout.setRefreshing(false);
    }
}
