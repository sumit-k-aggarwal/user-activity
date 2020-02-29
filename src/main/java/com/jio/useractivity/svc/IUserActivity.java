package com.jio.useractivity.svc;

import java.util.Date;

public interface IUserActivity {
    /**
     * @param inputDateTime
     * @return lastUserActivity details
     */
    String getUserLastActivity(Date inputDateTime);
}
