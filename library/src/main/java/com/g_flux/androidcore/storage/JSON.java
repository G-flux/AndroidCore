package com.g_flux.androidcore.storage;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class JSON implements Parcelable {

    /**
     * Abstract methods
     */

    public abstract void update(JSONObject json);

    public abstract JSONObject toJson();

    public void fromApiResponse(JSONObject json) {
        update(json);
    }

    public JSONObject toApiRequest() {
        return toJson();
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    /**
     * CHECK
     */

    public static boolean check(JSONObject json, String key) {
        return key == null || (json.has(key) && !json.isNull(key));
    }

    /**
     * LOAD single value
     */

    public static int load(JSONObject json, String key, int defaultValue) {
        if (check(json, key)) {
            try {
                return json.getInt(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static long load(JSONObject json, String key, long defaultValue) {
        if (check(json, key)) {
            try {
                return json.getLong(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static float load(JSONObject json, String key, float defaultValue) {
        if (check(json, key)) {
            try {
                return (float) json.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static double load(JSONObject json, String key, double defaultValue) {
        if (check(json, key)) {
            try {
                return json.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static Double load(JSONObject json, String key, Double defaultValue) {
        if (key != null && json.has(key)) {
            if (json.isNull(key)) {
                return null;
            }
            try {
                return json.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static boolean load(JSONObject json, String key, boolean defaultValue) {
        if (check(json, key)) {
            try {
                return json.getBoolean(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static String load(JSONObject json, String key, String defaultValue) {
        if (check(json, key)) {
            try {
                return json.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static JSONObject load(JSONObject json, String key, JSONObject defaultValue) {
        if (check(json, key)) {
            try {
                return json.getJSONObject(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static JSONArray load(JSONObject json, String key, JSONArray defaultValue) {
        if (check(json, key)) {
            try {
                return json.getJSONArray(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    /**
     * LOAD special single value
     */

    public static <E extends JSON> E load(JSONObject json, E defaultValue, Class<E> cls) {
        return load(json, defaultValue, cls, false);
    }

    public static <E extends JSON> E load(JSONObject json, E defaultValue, Class<E> cls, boolean fromApiResponse) {
        try {
            E instance = cls.newInstance();
            if (fromApiResponse) {
                instance.fromApiResponse(json);
            } else {
                instance.update(json);
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static <E extends JSON> E load(JSONObject json, String key, E defaultValue, Class<E> cls) {
        return load(json, key, defaultValue, cls, false);
    }

    public static <E extends JSON> E load(JSONObject json, String key, E defaultValue, Class<E> cls, boolean fromApiResponse) {
        if (check(json, key)) {
            try {
                E instance = cls.newInstance();
                if (fromApiResponse) {
                    instance.fromApiResponse(key == null ? json : json.getJSONObject(key));
                } else {
                    instance.update(key == null ? json : json.getJSONObject(key));
                }
                return instance;
            } catch (JSONException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static <E extends Enum<E>> E load(JSONObject json, String key, E defaultValue, Class<E> enumClass) {
        if (check(json, key)) {
            try {
                return E.valueOf(enumClass, json.getString(key).toUpperCase());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public Calendar load(JSONObject json, String key, Calendar defaultValue) {
        if (check(json, key)) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(json.getLong(key));
                return calendar;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public UUID load(JSONObject json, String key, UUID defaultValue) {
        if (check(json, key)) {
            try {
                return UUID.fromString(json.getString(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return defaultValue;
    }

    /**
     * LOAD array of values
     */

    public static ArrayList<String> load(JSONObject json, String key, ArrayList<String> defaultValue) {
        if (check(json, key)) {
            try {
                JSONArray valuesArray = json.getJSONArray(key);
                ArrayList<String> values = new ArrayList<>();
                for (int i = 0; i < valuesArray.length(); i++) {
                    values.add(valuesArray.getString(i));
                }
                return values;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static ArrayList<Integer> loadIntegerList(JSONObject json, String key, ArrayList<Integer> defaultValues) {
        if (check(json, key)) {
            try {
                JSONArray valuesArray = json.getJSONArray(key);
                ArrayList<Integer> values = new ArrayList<>();
                for (int i = 0; i < valuesArray.length(); i++) {
                    values.add(valuesArray.getInt(i));
                }
                return values;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValues;
    }

    public static <E extends JSON> ArrayList<E> load(JSONObject json, String key, ArrayList<E> defaultValue, Class<E> cls) {
        return load(json, key, defaultValue, cls, false);
    }

    public static <E extends JSON> ArrayList<E> load(JSONObject json, String key, ArrayList<E> defaultValue, Class<E> cls, boolean fromApiResponse) {
        if (check(json, key)) {
            try {
                JSONArray valuesArray = json.getJSONArray(key);
                ArrayList<E> values = new ArrayList<>();
                for (int i = 0; i < valuesArray.length(); i++) {
                    E instance = cls.newInstance();
                    if (fromApiResponse) {
                        instance.fromApiResponse(valuesArray.getJSONObject(i));
                    } else {
                        instance.update(valuesArray.getJSONObject(i));
                    }
                    values.add(instance);
                }
                return values;
            } catch (JSONException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static <E extends JSON, L extends ArrayList<E>> L load(JSONObject json, String key, L defaultValue, Class<E> clsE, Class<L> clsL) {
        if (check(json, key)) {
            try {
                JSONArray valuesArray = json.getJSONArray(key);
                L values = clsL.newInstance();
                for (int i = 0; i < valuesArray.length(); i++) {
                    E instance = clsE.newInstance();
                    instance.update(valuesArray.getJSONObject(i));
                    values.add(instance);
                }
                return values;
            } catch (JSONException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static <E extends JSON> ArrayList<E> load(JSONArray json, ArrayList<E> defaultValue, Class<E> cls) {
        try {
            ArrayList<E> values = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                E instance = cls.newInstance();
                instance.update(json.getJSONObject(i));
                values.add(instance);
            }
            return values;
        } catch (JSONException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static HashMap<Integer, JSONObject> load(JSONObject json, String key, HashMap<Integer, JSONObject> defaultValue) {
        if (check(json, key)) {
            try {
                JSONArray valuesArray = json.getJSONArray(key);
                HashMap<Integer, JSONObject> hashMap = new HashMap<>();
                for (int i = 0; i < valuesArray.length(); i++) {
                    JSONObject item = valuesArray.getJSONObject(i);
                    if (check(item, "k") && check(item, "v")) {
                        int _key = item.getInt("k");
                        JSONObject _value = new JSONObject(item.getString("v"));
                        hashMap.put(_key, _value);
                    }
                }
                return hashMap;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    /**
     * Jsonify an array of values
     */
    public static <E extends JSON> JSONArray toJsonObjectArray(ArrayList<E> values) {
        JSONArray json = new JSONArray();
        if (values != null && !values.isEmpty()) {
            for (E value : values) {
                json.put(value.toJson());
            }
        }
        return json;
    }

    public static JSONArray toJsonStringArray(ArrayList<String> values) {
        JSONArray json = new JSONArray();
        if (values != null && !values.isEmpty()) {
            for (String value : values) {
                json.put(value);
            }
        }
        return json;
    }

    public static JSONArray toIntegerList(ArrayList<Integer> values) {
        JSONArray json = new JSONArray();
        if (values != null && !values.isEmpty()) {
            for (Integer value : values) {
                json.put(value);
            }
        }
        return json;
    }

    /**
     * Jsonify a JSON object
     */
    public static <E extends JSON> JSONObject toJson(E object) {
        if (object == null) {
            return null;
        } else {
            return object.toJson();
        }
    }

    public static <E extends Enum<E>> String toJson(E object) {
        if (object == null) {
            return null;
        }

        return object.toString();
    }

    public static String toJson(UUID id) {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    /**
     * Jsonify a hashmap
     */

    public static JSONArray toJson(HashMap<Integer, JSONObject> hashMap) {
        if (hashMap == null) {
            return null;
        } else {
            JSONArray json = new JSONArray();
            for (Integer key : hashMap.keySet()) {
                JSONObject item = new JSONObject();
                try {
                    item.put("k", key.intValue());
                    item.put("v", hashMap.get(key).toString());
                } catch (JSONException ignored) {
                }
                json.put(item);
            }
            return json;
        }
    }

    /**
     * Jsonify a Calendar
     */

    public static Long toJson(Calendar calendar) {
        if (calendar == null) {
            return null;
        }

        return calendar.getTimeInMillis();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toString());
    }

    public static <E extends JSON> E fromParcel(Parcel parcel, Class<E> cls) {
        E instance = null;
        try {
            instance = cls.newInstance();
            instance.update(new JSONObject(parcel.readString()));
        } catch (InstantiationException | IllegalAccessException | JSONException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
