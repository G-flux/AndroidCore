package com.g_flux.androidcore.api;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public abstract class Api {

    protected String baseUri;
    private RequestQueue mRequestQueue;

    public Api() {
        baseUri = getBaseUri();
        mRequestQueue = getRequestQueue();
    }

    public ApiCall to(Endpoint endpoint) {
        return new ApiCall(this, endpoint);
    }

    protected abstract String getBaseUri();

    public abstract RequestQueue getRequestQueue();

    protected void callObject(ApiCall apiCall, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(apiCall.getEndpoint().getHttpMethod(), apiCall.getUrl(), apiCall.getRequestData(), responseListener, errorListener);
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }

    protected void callArray(ApiCall apiCall, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(apiCall.getEndpoint().getHttpMethod(), apiCall.getUrl(), null, responseListener, errorListener);
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }

    public static JSONObject parseErrorAsObject(VolleyError error) {
        if (error != null && error.networkResponse != null && error.networkResponse.data != null) {
            try {
                String dataString = new String(error.networkResponse.data, "UTF-8");
                return new JSONObject(dataString);
            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public String getUri() {
        return baseUri;
    }
}
