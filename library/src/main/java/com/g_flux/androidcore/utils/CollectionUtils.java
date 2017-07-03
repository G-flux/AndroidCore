package com.g_flux.androidcore.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.g_flux.androidcore.interfaces.Function;
import com.g_flux.androidcore.interfaces.Transformer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class CollectionUtils {

    /**
     * Filter an input using a {@link Function}.
     *
     * @param input  {@link ArrayList<E>} The input {@link ArrayList<E>}
     * @param filter The filter {@link Function}
     * @param <E>    Object
     * @return {@link ArrayList<E>} The filtered {@link ArrayList<E>}
     */
    public static <E> ArrayList<E> filter(ArrayList<E> input, Function<E, Boolean> filter) {
        ArrayList<E> filteredInput = new ArrayList<>();
        for (E item : input) {
            if (filter.apply(item)) {
                filteredInput.add(item);
            }
        }
        return filteredInput;
    }

    /**
     * Find an item in a list using a comparator {@link Function}.
     *
     * @param list       {@link ArrayList<E>} The input {@link ArrayList<E>}
     * @param comparator The comparator {@link Function}
     * @param <E>        Object
     * @return {@link E} The first item that meets the comparator or null
     */
    public static <E> E find(ArrayList<E> list, Function<E, Boolean> comparator) {
        if (list == null || list.isEmpty() || comparator == null) {
            return null;
        }

        for (E item : list) {
            if (comparator.apply(item)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Get the first index of an item that meets the comparator.
     *
     * @param list       {@link ArrayList<E>} The input {@link ArrayList<E>}
     * @param comparator The comparator {@link Function}
     * @param <E>        Object
     * @return {@link E} The index of the first item that meets the comparator or -1
     */
    public static <E> int indexOf(List<E> list, Function<E, Boolean> comparator) {
        if (list == null || list.isEmpty() || comparator == null) {
            return -1;
        }

        for (int index = 0; index < list.size(); index++) {
            if (comparator.apply(list.get(index))) {
                return index;
            }
        }

        return -1;
    }

    /**
     * Update a list with an item. If an item already exists in the list (meets the comparator),
     * then it will be replaced, else it will be added to the end.
     *
     * @param list       {@link ArrayList<E>} The input {@link ArrayList<E>}
     * @param item       The item
     * @param comparator The comparator {@link Function}
     * @param <E>        Object
     */
    public static <E> void update(List<E> list, E item, Function<E, Boolean> comparator) {
        if (list == null || item == null || comparator == null) {
            return;
        }

        int index = indexOf(list, comparator);
        if (index == -1) {
            list.add(item);
        } else {
            list.set(index, item);
        }
    }


    /**
     * Update a list with an item. If an item already exists in the list (meets the comparator),
     * then it will be replaced, else it will be added to the end.
     *
     * @param list       {@link ArrayList<E>} The input {@link ArrayList<E>}
     * @param item       The item
     * @param comparator The comparator {@link Function}
     * @param onAdd      {@link Function} to execute if the item was added
     * @param onUpdate   {@link Function} to execute if the item was updated
     * @param <E>        Object
     */
    public static <E> void update(List<E> list, E item, Function<E, Boolean> comparator, Function<E, Void> onAdd, Function<E, Void> onUpdate) {
        if (list == null || item == null || comparator == null) {
            return;
        }

        int index = indexOf(list, comparator);
        if (index == -1) {
            list.add(item);
            if (onAdd != null) {
                onAdd.apply(item);
            }
        } else {
            list.set(index, item);
            if (onUpdate != null) {
                onUpdate.apply(item);
            }
        }
    }

    public static <L extends List<String>> String implode(L items, String separator) {
        return implode(items, separator, new IdentityTransformer<String>());
    }

    public static <O, L extends List<O>> String implode(L items, String separator, Transformer<O, String> toStringTransformer) {
        if (items == null || items.isEmpty()) {
            return "";
        }
        String result = "";
        for (int i = 0; i < items.size(); i++) {
            if (i != 0) {
                result += separator;
            }
            result += toStringTransformer.transform(items.get(i));
        }
        return result;
    }

    public static <T, R> R[] map(T[] items, Function<T, R> function, Class<R> cls) {
        @SuppressWarnings("unchecked")
        R[] results = (R[]) Array.newInstance(cls, items.length);
        for (int i = 0; i < items.length; i++) {
            results[i] = function.apply(items[i]);
        }
        return results;
    }

    public static <T, R> R[] map(ArrayList<T> items, Function<T, R> function, Class<R> cls) {
        return map((T[]) items.toArray(), function, cls);
    }

    public static <R, E> R reduce(E[] items, Function<Pair<R, E>, R> function, R initialValue) {
        R result = initialValue;
        for (E item : items) {
            result = function.apply(new Pair<>(result, item));
        }
        return result;
    }

    /**
     * Get the first element of a List.
     *
     * @param items The items
     * @param <O>   The class of the items
     * @return The first item
     */
    public static <O> O first(List<O> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }

        return items.get(0);
    }

    /**
     * Get the last element of a List.
     *
     * @param items The items
     * @param <O>   The class of the items
     * @return The first item
     */
    public static <O> O last(List<O> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }

        return items.get(items.size() - 1);
    }

    /**
     * Get every element of a List except the last element.
     *
     * @param items The items
     * @param <O>   The class of the items
     * @return The first item
     */
    public static <O> List<O> head(List<O> items) {
        if (items == null || items.size() < 2) {
            return null;
        }

        return items.subList(0, items.size() - 1);
    }

    /**
     * Get every element of a List except the first element.
     *
     * @param items The items
     * @param <O>   The class of the items
     * @return The first item
     */
    public static <O> List<O> tail(List<O> items) {
        if (items == null || items.size() < 2) {
            return null;
        }

        return items.subList(1, items.size());
    }

    @SafeVarargs
    public static <E> boolean contains(@NonNull E needle, E... items) {
        for (E item : items) {
            if (needle.equals(item)) {
                return true;
            }
        }

        return false;
    }
}
