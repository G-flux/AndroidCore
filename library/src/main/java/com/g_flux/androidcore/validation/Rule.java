package com.g_flux.androidcore.validation;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class Rule {

    public String name;
    private String rule;
    private String[] errorData;

    public Rule(String name, String rule, String[] errorData) {
        this.name = name;
        this.rule = rule;
        this.errorData = errorData;
    }
    public Rule(String rule, String[] errorData) {
        this(rule, rule, errorData);
    }

    public Rule(String rule) {
        this(rule, new String[0]);
    }

    public Rule(String name, String rule) {
        this(name, rule, new String[0]);
    }

    public String getRule() {
        return rule;
    }

    public String[] getErrorData() {
        return errorData;
    }
}
