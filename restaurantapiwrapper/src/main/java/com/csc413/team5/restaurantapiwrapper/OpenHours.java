package com.csc413.team5.restaurantapiwrapper;

import java.util.HashMap;

/**
 * TODO Description
 * <p>
 * Created on 7/3/2015.
 *
 * @author Eric C. Black
 */
public class OpenHours<DayOfWeek, OpenHoursDay> extends HashMap {
    /**
     * Constructs open hours with all days of week set to null (closed).
     */
    public OpenHours() {
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.MONDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.TUESDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.WEDNESDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.THURSDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.FRIDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.SATURDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.SUNDAY, null);
    }

    public boolean hasOpenHours() {
        boolean hasOpenHours = false;
        for ( Object key : this.keySet() ) {
            if (key != null) {
                hasOpenHours = true;
                break;
            }
        }
        return hasOpenHours;
    }

    /**
     * Returns the number of elements in this map.
     *
     * @return the number of elements in this map.
     */
    @Override
    public int size() {
        return 7;
    }

    /**
     * Removes all mappings from this hash map, leaving it empty.
     *
     * @see #isEmpty
     * @see #size
     */
    @Override
    public void clear() {
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.MONDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.TUESDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.WEDNESDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.THURSDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.FRIDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.SATURDAY, null);
        put(com.csc413.team5.restaurantapiwrapper.DayOfWeek.SUNDAY, null);
    }

    /**
     * Removes the mapping with the specified day of week from this map.
     *
     * @param key the day of week to remove (set to null)
     * @return the value of the removed mapping or {@code null} if no mapping
     * for the specified key was found.
     */
    @Override
    public Object remove(Object key) {
        Object val = get(key);
        put(key, null);
        return val;
    }

    /**
     * Returns the {@link OpenHoursDay} value of specified {@link DayOfWeek}, which may be
     * null if the venue is not open on that day.
     *
     * @param key a {@link DayOfWeek}.
     * @return the value of the mapping with the specified key, or {@code null}
     *         if no mapping for the specified key is found.
     */
    @Override
    public Object get(Object key) {
        return super.get(key);
    }

    /**
     * Maps the specified {@link OpenHoursDay} to the specified {@link DayOfWeek}.
     *
     * @param key   a {@link DayOfWeek}
     * @param value an {@link OpenHoursDay}
     * @return the value of any previous mapping with the specified {@link DayOfWeek} or
     * {@code null} if there was no such mapping.
     */
    @Override
    public Object put(Object key, Object value) {
        return super.put(key, value);
    }
}

