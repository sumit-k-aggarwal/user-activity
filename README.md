# User Activity Service

A Service/Application to get "Last Seen" Time for applications like WhatsApp. Currently this is a run once Spring Boot application which expects first argument as Input Date Time a mandatory input and an optional data format.

Assumptions:
    If input dateformat is not specified, application assumes dateFormat as yyyyMMddHHmm
    Input datetime is expected to be in the format of defined date format.

Sample Command to Run application as:
java -jar user-activity-0.0.1-SNAPSHOT.jar 202001161920

Business Logic as followed:
Last Activity Time            Reported as
Greater than 1 Year           Last seen # years ago
>= 1 Month & < 12 Months      Last seen # months ago
>= 1 Day & < 31 Days          Last seen # days ago
>= 1 Hour & < 24 Hours        Last seen # hours ago
>= 1 Minute & < 59 Minutes    Last seen # minutes ago
< 1 Minute                    Active













  
