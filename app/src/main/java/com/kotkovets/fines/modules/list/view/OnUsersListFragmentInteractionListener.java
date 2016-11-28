package com.kotkovets.fines.modules.list.view;

/**
 * Created by igork on 4/24/16.
 */
public interface OnUsersListFragmentInteractionListener {
    void onListFragmentItemTap(int userUniqueId);
    void onListFragmentItemDelete(int userUniqueId);
}
