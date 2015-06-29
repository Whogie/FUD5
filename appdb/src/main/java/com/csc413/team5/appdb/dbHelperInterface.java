package com.csc413.team5.appdb;

import android.content.Context;

import com.csc413.team5.restaurantapiwrapper.Restaurant;

import java.util.List;

public interface dbHelperInterface {

    /**********
    Core methods
    **********/

    /* db_create code */
    /**
     * Inserts a restaurant record to a designated list.
     * and migrates a restaurant record from another list if found somewhere else.
     *
     * @param my_restaurant  a restaurant to insert to a list
     * @param listClass a list to insert an item to
     * <ol>
     *  <li>Inserts to green list.</li>
     *  <li>Inserts to yellow list.</li>
     *  <li>Inserts to red list.</li>
     * </ol>
     * @see Restaurant
     */
    void rawInsertRestaurantToList(Restaurant my_restaurant, int listClass);
    /* end db_create code */

    /* db_read code */
    /**
     * Returns a list of restaurant names from a designated list
     *
     * @param listClass a list to insert an item to
     * <ol>
     *  <li>Inserts to green list.</li>
     *  <li>Inserts to yellow list.</li>
     *  <li>Inserts to red list.</li>
     * </ol>
     * @return a list of restaurant names from a designated list
     * @see Restaurant
     */
    List<String> getRestaurantNamesFromList(int listClass);
    /* end db_read code */

    /* db_update code */
    /**
     * Migrates a restaurant record from another list if found somewhere else.
     *
     * @param my_restaurant  a restaurant to migrate to another list
     * @param fromList list item origin
     * @param toList list item destination
     * <ol>
     *  <li>The green list.</li>
     *  <li>The yellow list.</li>
     *  <li>The red list.</li>
     * </ol>
     * @return if migration was successful, true
     * @see Restaurant
     */
    boolean migrateRestaurantListItem(Restaurant my_restaurant, int fromList, int toList);
    /* end db_update code */

    /* db_delete code */
    /**
     * Deletes a restaurant from the green list
     * @param my_restaurant a restaurant to delete from a list
     * @return if deletion was successful, true
     * @see Restaurant
     */
    boolean deleteRestaurantFromGreenList(Restaurant my_restaurant);
    /**
     * Deletes a restaurant from the yellow list
     * @param my_restaurant  a restaurant to delete from a list
     * @return if deletion was successful, true
     * @see Restaurant
     */
    boolean deleteRestaurantFromYellowList(Restaurant my_restaurant);
    /**
     * Deletes a restaurant from the red list
     * @param my_restaurant  a restaurant to delete from a list
     * @return if deletion was successful, true
     * @see Restaurant
     */
    boolean deleteRestaurantFromRedList(Restaurant my_restaurant);
    /* end db_delete code*/

    /****************
     Helper methods
     ***************/

    /**
     * Gets the list color where a restaurant belongs to
     * @param my_restaurant a restaurant
     * @return a number which corresponds to a list. "-1" if record is found in more than 1 place or not found.
     * <ol>
     *  <li>The green list.</li>
     *  <li>The yellow list.</li>
     *  <li>The red list.</li>
     * </ol>
     * @see Restaurant
     */
    int getRestaurantListColor(Restaurant my_restaurant);
    /**
     * Checks whether a restaurant belongs to the green list
     * @param my_restaurant a restaurant
     * @return if the restaurant is in the green list, true
     * @see Restaurant
     */
    boolean isRestaurantInGreenList(Restaurant my_restaurant);
    /**
     * Checks whether a restaurant belongs to the yellow list
     * @param my_restaurant  a restaurant
     * @return if the restaurant is in the yellow list, true
     * @see Restaurant
     */
    boolean isRestaurantInYellowList(Restaurant my_restaurant);
    /**
     * Checks whether a restaurant belongs to the green list
     * @param my_restaurant  a restaurant
     * @return if the restaurant is in the red list, true
     * @see Restaurant
     */
    boolean isRestaurantInRedList(Restaurant my_restaurant);

    /**
     * Checks if the database exists
     * @param context the current context that the end-user is in
     * @return if the database exists, true
     * @see Context
     */
    boolean isDbExist(Context context);
    /**
     * @return The database name
     */
    String getDbName();
    /**
     * @param context the current context that the end-user is in
     * @return The database path
     */
    String getDBPath(Context context);
}
