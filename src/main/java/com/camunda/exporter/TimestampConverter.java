package com.camunda.exporter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimestampConverter {
    public static void main(String[] args) {
        long timestamp = 1727281413126L; // Your timestamp
        Date date = new Date(timestamp); // Convert to Date

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String formattedDate = sdf.format(date);

        System.out.println("Formatted Date: " + formattedDate);



        // Convert long to LocalDateTime

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        System.out.println("dateTime: " + dateTime);
    }
}
