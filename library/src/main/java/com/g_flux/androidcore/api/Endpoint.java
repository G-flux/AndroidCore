package com.g_flux.androidcore.api;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class Endpoint {

    private static Pattern parameterPattern = Pattern.compile("\\{[^\\}]+\\}");

    /**
     * The HTTP Method of the Endpoint.
     */
    private int httpMethod;

    /**
     * The path to the Endpoint.
     */
    private String path;

    /**
     * The ReturnType of the Endpoint.
     */
    private ReturnType returnType;

    public Endpoint(int httpMethod, String path, ReturnType returnType) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.returnType = returnType;
    }

    public int getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public String build(HashMap<String, Object> urlParameters, HashMap<String, Object> queryParameters) {
        String url = path;
        ArrayList<String> parameters = extractParameters(path);
        if (!parameters.isEmpty() && (urlParameters == null || urlParameters.isEmpty())) {
            return null;
        }

        for (String parameter : parameters) {
            if (!urlParameters.containsKey(parameter)) {
                return null;
            }

            url = url.replace("{" + parameter + "}", urlParameters.get(parameter).toString());
        }

        return url + getQueryString(queryParameters);
    }

    private ArrayList<String> extractParameters(String path) {
        ArrayList<String> parameters = new ArrayList<>();
        Matcher regexMatcher = parameterPattern.matcher(path);
        while (regexMatcher.find()) {
            parameters.add(regexMatcher.group());
        }
        return parameters;
    }

    private String getQueryString(HashMap<String, Object> queryParameters) {
        if (queryParameters == null || queryParameters.isEmpty()) {
            return "";
        }

        ArrayList<String> queryStringParts = new ArrayList<>();
        for (String key : queryParameters.keySet()) {
            queryStringParts.add(key + "=" + queryParameters.get(key).toString());
        }

        return "?" + TextUtils.join("&", queryStringParts);
    }
}
