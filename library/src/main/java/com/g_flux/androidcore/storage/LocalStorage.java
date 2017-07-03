package com.g_flux.androidcore.storage;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class LocalStorage {

    private static String mPreferencesName;

    private SharedPreferences mStorage;

    public LocalStorage(Context context) {
        mPreferencesName = getPreferencesName();
        mStorage = context.getSharedPreferences(mPreferencesName, Context.MODE_PRIVATE);
    }

    public abstract String getPreferencesName();

    /**
     * Get a {@link String} from the local storage.
     * Use null as default value.
     *
     * @param key String The key
     * @return String
     */
    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * Get a {@link String} from the local storage.
     *
     * @param key          String The key
     * @param defaultValue String The default value if the key doesn't exist
     * @return String
     */
    public String getString(String key, String defaultValue) {
        return mStorage.getString(key, defaultValue);
    }

    /**
     * Get a {@link JSON} model from the local storage.
     * Use null as default value.
     *
     * @param key String The key
     * @param cls Class The JSON subclass
     * @return E
     */
    public <E extends JSON> E getObject(String key, Class<E> cls) {
        return getObject(key, null, cls);
    }

    /**
     * Get a {@link JSON} model from the local storage.
     *
     * @param key          String The key
     * @param defaultValue E The default value if the key doesn't exist
     * @param cls          Class The JSON subclass
     * @return E
     */
    public <E extends JSON> E getObject(String key, E defaultValue, Class<E> cls) {
        if (!mStorage.contains(key)) {
            return defaultValue;
        }
        try {
            JSONObject json = new JSONObject(mStorage.getString(key, null));
            return JSON.load(json, null, defaultValue, cls);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get an {@link ArrayList} of {@link JSON} models from the local storage.
     * Use an empty {@link ArrayList} as default value.
     *
     * @param key String The key
     * @param cls Class The JSON subclass
     * @return E
     */
    public <E extends JSON> ArrayList<E> getArray(String key, Class<E> cls) {
        return getArray(key, new ArrayList<E>(), cls);
    }

    /**
     * Get an {@link ArrayList} of {@link JSON} models from the local storage.
     *
     * @param key          String The key
     * @param defaultValue ArrayList<E> The default value if the key doesn't exist
     * @param cls          Class The JSON subclass
     * @return E
     */
    public <E extends JSON> ArrayList<E> getArray(String key, ArrayList<E> defaultValue, Class<E> cls) {
        if (!mStorage.contains(key)) {
            return defaultValue;
        }
        try {
            JSONArray json = new JSONArray(mStorage.getString(key, null));
            return JSON.load(json, new ArrayList<E>(), cls);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Put a {@link String} in the local storage.
     *
     * @param key   {@link String} The key
     * @param value {@link String} The value
     */
    public void putString(String key, String value) {
        mStorage.edit()
                .putString(key, value)
                .apply();
    }

    /**
     * Put an {@link ArrayList} of {@link JSON} objects in the local storage.
     *
     * @param key   {@link String} The key
     * @param items {@link ArrayList} The items
     */
    public <E extends JSON> void putArray(String key, ArrayList<E> items) {
        mStorage.edit()
                .putString(key, JSON.toJsonObjectArray(items).toString())
                .apply();
    }

    /**
     * Put a {@link JSON} object in the local storage.
     *
     * @param key    {@link String} The key
     * @param object {@link JSON} The object
     */
    public <E extends JSON> void putObject(String key, E object) {
        mStorage.edit()
                .putString(key, object == null ? null : object.toString())
                .apply();
    }

    /**
     * Remove a key from the local storage.
     *
     * @param key The key
     */
    public void remove(String key) {
        mStorage.edit()
                .remove(key)
                .apply();
    }
}
