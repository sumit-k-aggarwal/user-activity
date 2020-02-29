package com.jio.useractivity.data;

import com.jio.useractivity.util.DateTimeUtil;
import org.nfunk.jep.JEP;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

/**
 * Rule Class with all possible conditions.
 */
@ConfigurationProperties(prefix = "user-activity-rule-conditions")
@Configuration
public class Rule {
    List<Condition> conditions;

    public String executeRule (Date inputDateTime) {
        Date now = new Date();
        if(inputDateTime.after(now)) {
            throw new RuntimeException("Date entered is in future");
        }
        JEP jepExpression = new JEP();
        jepExpression.setAllowUndeclared(true);
        jepExpression.addStandardConstants();
        jepExpression.addStandardFunctions();
        for(Condition cond: conditions) {
            String expressionInput = cond.getExpression();
            jepExpression.parseExpression(expressionInput);
            Long elapsed_duration = DateTimeUtil.getTimeDifference(now, inputDateTime, cond.getType());
            jepExpression.setVarValue("$elapsed_duration", elapsed_duration);
            Double executeOutput = (Double) jepExpression.getValueAsObject();
            //System.out.println("executeOutput: " + executeOutput + ", jepExpression:" + jepExpression.getErrorInfo());
            if(executeOutput == 1.0) {
                return cond.getOutcome().replace("$elapsed_duration", elapsed_duration+"");
            }
        }
        return null;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
