package com.g_flux.androidcore.api;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class ApiCall {

    private Api api;
    private Endpoint endpoint;
    private HashMap<String, Object> urlParameters;
    private HashMap<String, Object> queryParameters;
    private HashMap<String, Object> parameters;

    public ApiCall(Api api, Endpoint endpoint) {
        this.api = api;
        this.endpoint = endpoint;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public HashMap<String, Object> getUrlParameters() {
        return urlParameters;
    }

    public ApiCall setUrlParameters(HashMap<String, Object> urlParameters) {
        this.urlParameters = urlParameters;

        return this;
    }

    public HashMap<String, Object> getQueryParameters() {
        return queryParameters;
    }

    public ApiCall setQueryParameters(HashMap<String, Object> queryParameters) {
        this.queryParameters = queryParameters;

        return this;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public ApiCall setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;

        return this;
    }

    public String getUrl() {
        return api.baseUri + endpoint.build(urlParameters, queryParameters);
    }

    public JSONObject getRequestData() {
        if (parameters == null || parameters.isEmpty()) {
            return null;
        }
        JSONObject requestData = new JSONObject();
        for (String key : parameters.keySet()) {
            try {
                requestData.put(key, parameters.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return requestData;
    }

    public <E> void call(Response.Listener<E> responseListener) {
        call(responseListener, null);
    }

    public <E> void call(Response.Listener<E> responseListener, Response.ErrorListener errorListener) {
        switch (endpoint.getReturnType()) {
            case JSON_OBJECT:
                api.callObject(this, (Response.Listener<JSONObject>) responseListener, errorListener);
                break;
            case JSON_ARRAY:
                api.callArray(this, (Response.Listener<JSONArray>) responseListener, errorListener);
                break;
        }
    }
}
