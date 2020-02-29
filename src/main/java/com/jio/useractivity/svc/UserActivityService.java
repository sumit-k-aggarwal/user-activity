package com.jio.useractivity.svc;

import com.jio.useractivity.data.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service class for different user activities.
 */
@Service
public class UserActivityService implements IUserActivity {
    @Autowired
    Rule ruleDefinition;

    public String getUserLastActivity(Date inputDateTime) {
        return ruleDefinition.executeRule(inputDateTime);
    }
}
