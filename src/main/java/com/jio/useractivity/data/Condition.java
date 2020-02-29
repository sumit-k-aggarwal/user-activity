package com.jio.useractivity.data;

import org.springframework.context.annotation.Configuration;

/**
 * Condition class to carry expression and outcome of condition execution
 */
@Configuration
public class Condition {
    String expression;
    String outcome;
    String type;

    public Condition() {
    }

    public Condition(String expression, String type, String outcome) {
        this.expression = expression;
        this.outcome = outcome;
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}