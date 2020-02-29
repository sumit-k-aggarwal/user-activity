package com.jio.useractivity;

import com.jio.useractivity.svc.UserActivityService;
import com.jio.useractivity.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class UserActivityApplication implements CommandLineRunner {
	@Autowired
	UserActivityService activityService;
	String dateFormat = "yyyyMMddHHmm";

	public static void main(String[] args) {
		System.out.println("ENTERED");
		ConfigurableApplicationContext ctx = SpringApplication.run(UserActivityApplication.class, args);
		int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				// return the error code
				return 0;
			}
		});
		System.exit(exitCode);
	}

	@Override
	public void run(String... args) throws Exception {
		if(args.length > 0) {
			String dateTime = args[0];
			if(args.length > 1) {
				dateFormat = args[1];
			}
			System.out.println("InputDateFormat:" + dateFormat+ ", InputDateTime:" + dateTime);
			DateTimeUtil dateTimeUtil = new DateTimeUtil();
			dateTimeUtil.setInputDateFormat(dateFormat, TimeZone.getDefault());
			Date sourceDCTime = null;
			try {
				sourceDCTime = dateTimeUtil.stringToDate(dateTime);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			String lastActivity = activityService.getUserLastActivity(sourceDCTime);
			System.out.println("lastActivity:" + lastActivity);
		} else {
			System.err.println("Missing DateTime Input!");
		}
	}
}
